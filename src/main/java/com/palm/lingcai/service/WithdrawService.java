package com.palm.lingcai.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.cache.memcached.SpyMemcachedClient;
import org.springside.modules.utils.DateProvider;

import com.palm.lingcai.entity.Bank;
import com.palm.lingcai.entity.Bill;
import com.palm.lingcai.entity.Marketplan;
import com.palm.lingcai.entity.User;
import com.palm.lingcai.entity.Withdraw;
import com.palm.lingcai.repository.BankDao;
import com.palm.lingcai.repository.BillDao;
import com.palm.lingcai.repository.LotteryDao;
import com.palm.lingcai.repository.UserDao;
import com.palm.lingcai.repository.UserLotteryDao;
import com.palm.lingcai.repository.UserPrizeDao;
import com.palm.lingcai.repository.WithdrawDao;
import com.palm.lingcai.statuscode.BillChannelEnum;
import com.palm.lingcai.statuscode.BillIndecrEnum;
import com.palm.lingcai.statuscode.BillMethodEnum;
import com.palm.lingcai.statuscode.BillReturnCodeEnum;
import com.palm.lingcai.statuscode.Common;
import com.palm.lingcai.statuscode.SaltUtils;
import com.palm.lingcai.statuscode.TrueFalseEnum;
import com.palm.lingcai.statuscode.WithdrawEnum;
import com.palm.lingcai.util.JsonUtils;
import com.palm.lingcai.util.UUIDUtils;
import com.palm.lingcai.util.withdraw.AlipayWithdraw;

/**
 * 
 * @Title: WithdrawService.java
 * @Description:(用一句话描述该文件做什么)
 * @author kelylmall
 * @email ming.li@lingcaibao.com
 * @date 2014年10月14日 下午2:43:49
 * @version V1.0
 */
@Service
@Transactional
public class WithdrawService {
	private static Logger logger = LoggerFactory.getLogger(WithdrawService.class);
	@Autowired
	private LotteryDao lotteryDao;
	@Autowired
	private SpyMemcachedClient memcachedClient;
	@Autowired
	private UserLotteryDao userLotteryDao;
	@Autowired
	private UserPrizeDao userPrizeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BankDao bankDao;
	@Autowired
	private BillDao billDao;
	@Autowired
	private WithdrawDao withdrawDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BalanceService balanceService;
    private DateProvider dateProvider = DateProvider.DEFAULT;
    private Lock lock = new ReentrantLock();  
    
	/**
	 * 查询用户所有提现信息
	 * 
	 * @param username
	 * @return
	 */
    public List<Withdraw> findByUserName(String username) {
		return withdrawDao.findByUserName(username);
	}
	
    public void updateWithdrawByIds(String ids, Integer status, String mesg) {
		withdrawDao.updateWithdrawByIds(ids, status, mesg);
	}
	public void updateWithdrawByIds(String ids, Integer status) {
		withdrawDao.updateWithdrawByIds(ids, status, null);
	}
	
	/**
	 * 提现状态
	 * @param params
	 * @return
	 */
    public List<Withdraw> findWithdrawStatus(Map<String, Object> params){
    	return withdrawDao.findWithdrawStatus(params);
    }
    
    /**
	 * 根据状态查询
	 * @param status
	 * @param limitNum
	 * @return
	 */
    public List<Withdraw> findWithdraws(String status,Integer limitNum){
    	return withdrawDao.findWithdraws(status, limitNum);
    }
	
	/**
     * 奖金提现，根据金额提现
     * @param marketplan
     * @param bank
     * @throws Exception
     */
	public void withdrawByMoney(HttpServletRequest request,
			Marketplan marketplan, Bank bank, String cardNo,
			String mobile, String money,String serialNo) throws Exception {
		
		User user = accountService.findUserByLoginName(mobile);
		if(user ==null){
			throw new Exception("奖金提现失败：手机号有误");
		}
		BigDecimal amount = new BigDecimal(money);
		if (user.getPrize().compareTo(amount) < 0) {
			throw new Exception("奖金提现失败：奖金余额不足");
		}
		if (amount.compareTo(BigDecimal.ONE) <= 0) {
			throw new Exception("奖金提现失败：提现金额必须大于1元");
		}
		
		List<Withdraw> listw = withdrawDao.findBySerialNo(marketplan.getId(),serialNo);
		if (listw.size() > 0) {
			throw new Exception("奖金提现失败：流水号重复提现");
		}

		// 更新用户信息:mobile cardNo
		if (!cardNo.equals(user.getCardNo())) {
			user.setCardType(1);
			user.setCardNo(cardNo);
		}
		if (!mobile.equals(user.getMobile())) {
			user.setMobile(mobile);
		}
		userDao.update(user);
		
		//保存银行信息
		Bank bankDB = bankDao.findMinShengBank(user.getId(), bank.getCardno());
		if (bankDB == null) {
			bank.setUserid(user.getId());
			bank.setCardtype(1);
			bank.setDeleteFlag(0);
			bankDao.insert(bank);
			bankDB = bank;
		} else {
			// 更新银行卡信息
			bankDB.setAccountname(bank.getAccountname());
			bankDB.setBankname(bank.getBankname());
			bankDao.update(bankDB);
		}
		
		//提现申请单
		Withdraw withdraw = new Withdraw();
		withdraw.setUserid(user.getId());
		withdraw.setBankid(bankDB.getId());
		withdraw.setAmount(amount);
		withdraw.setStatus(0);// 提交申请
		withdraw.setCreatetime(dateProvider.getDate());
		withdraw.setOrderid(UUIDUtils.getSerialID());
		withdraw.setSerialNo(serialNo);
		withdraw.setWxserverid(marketplan.getId());
		withdraw.setSource(marketplan.getMarketName());
        withdrawDao.insert(withdraw);
        
        //更改用户返奖余额
		updatePrizeBalance(user, amount);
        
        //记录流水
        BigDecimal afterBalance = user.getPrize().subtract(withdraw.getAmount());
        this.saveBill(withdraw, user.getPrize(), afterBalance, request);
	}
	
	private void updatePrizeBalance(User user, BigDecimal amount)
			throws Exception {
		lock.lock();
		try {
			User userDB = userDao.get(user.getId());
			if (userDB.getPrize().compareTo(amount) < 0) {
				throw new Exception("奖金提现失败：奖金余额不足");
			}
			userDB.setPrize(amount.negate());
			userDao.updateRewardBalance(userDB);
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 插入交易记录表
	 * @param withdraw
	 * @param preBalance
	 * @param afterBalance
	 * @param request
	 */
	private void saveBill(Withdraw withdraw,BigDecimal preBalance,BigDecimal afterBalance,HttpServletRequest request){
        Bill bill = new Bill();
        bill.setUserid(withdraw.getUserid());
        bill.setAmount(withdraw.getAmount().negate());//减
        bill.setBilltime(System.currentTimeMillis());
        bill.setIndecr(BillIndecrEnum.SUBTRACT.ordinal());//减
        bill.setOrderid(withdraw.getOrderid());
        bill.setPrebalance(preBalance);
        bill.setSerialno(UUIDUtils.getSerialID());
        bill.setBillchannel(BillChannelEnum.WITHDRAW.ordinal());//代表提现
        bill.setBillret(BillReturnCodeEnum.NORMAL.ordinal());//未处理
        bill.setLocked(TrueFalseEnum.FALSE.ordinal());//锁定，此提现金额未审核前处于资金锁定状态
        bill.setIpaddr(Common.getIpAddr(request));
        bill.setMethod(BillMethodEnum.PRIZE_BALANCE.ordinal());//从奖金余额提现
        bill.setCreatetime(System.currentTimeMillis());
        bill.setSubject("奖金提现");
        bill.setAfterbalance(afterBalance);
        SaltUtils.buildBillSalt(bill);
        billDao.insert(bill);	
	}
	
	
	
	public void auditAlipayWithdraw(List<AlipayWithdraw> withdrawList) {
		logger.info("withdrawList:{}", withdrawList.size());
		for (AlipayWithdraw alipayWithdraw : withdrawList) {
			String orderId = alipayWithdraw.getOrderId();
			String status = alipayWithdraw.getStatus();
			String remark = alipayWithdraw.getRemark();
			String tradeNo = alipayWithdraw.getTradeNo();
			Withdraw withdraw = withdrawDao.findWithdrawByOrderId(orderId);
			withdraw.setTradeNo(tradeNo);
			Long userid = withdraw.getUserid();
			Long wxserverid = withdraw.getWxserverid();
			if (status.equals("F")) {
				withdraw.setStatus(WithdrawEnum.FAIL.code);
				withdraw.setRemark("转账失败："+remark);
			} else if (status.equals("S")) {
				withdraw.setStatus(WithdrawEnum.SUCCESS.code);
				withdraw.setRemark("转账成功");
			}
			logger.info("withdraw:{}", JsonUtils.objectToJsonString(withdraw));
			auditServerId(withdraw, userid, tradeNo, 0, wxserverid);
		}
	}

	/**
	 * 解析支付结果文件，(支付失败金额回滚要更新user.prize)
	 */
	public void auditServerId(Withdraw withdraw, Long userid, String tradeno,int billret,Long serverId) {
		if (withdraw.getStatus() == WithdrawEnum.FAIL.code) {
			// 审核不通过则把申请提现的金额返还到用户账户奖金余额中
			User user = userDao.get(userid);
			Bill bill = billDao.getByOrderid(withdraw.getOrderid());
            
			Bill newBill = new Bill();
            newBill.setOrderid(bill.getOrderid());
            newBill.setUserid(bill.getUserid());
            newBill.setBillchannel(6);
            newBill.setBilltime(System.currentTimeMillis());
            newBill.setBillret(0);//失败
            newBill.setPrebalance(user.getPrize());
            newBill.setAmount(withdraw.getAmount().plus());
            newBill.setIndecr(1);//加
            newBill.setSerialno(UUIDUtils.getSerialID());
            newBill.setCreatetime(newBill.getBilltime());
            newBill.setInformation("提现转账失败");
            newBill.setAfterbalance(withdraw.getAmount().add(user.getPrize()));
            newBill.setLocked(0);//取消锁定，资金正常
            newBill.setBalanceid(bill.getBalanceid());
            newBill.setSubject(bill.getSubject());
            SaltUtils.buildBillSalt(newBill);
            billDao.insert(newBill);
            
            //更新申请提现记录
            bill.setLocked(0);//取消锁定，资金正常
            bill.setModifytime(System.currentTimeMillis());
            billDao.update(bill);
            
            //更改用户余额
            user.setPrize(withdraw.getAmount());//返还提现的金额到用户奖金余额
            userDao.updateRewardBalance(user);
        } else {
        	//审核通过-更新申请提现记录
			Bill bill = billDao.getByOrderid(withdraw.getOrderid());
            bill.setLocked(0);//取消锁定，资金正常
            bill.setBillret(1);//交易成功
            bill.setTradeno(tradeno);
            bill.setInformation("提现转账成功");
            bill.setModifytime(System.currentTimeMillis());
            billDao.update(bill);
        }

        withdraw.setAudittime(dateProvider.getDate());
        withdraw.setProcesstime(dateProvider.getDate());
        withdrawDao.update(withdraw);
	}
	
}


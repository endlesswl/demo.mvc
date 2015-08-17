package com.palm.lingcai.statuscode;

import com.palm.lingcai.entity.Bill;
import com.palm.lingcai.entity.Deposit;
import com.palm.lingcai.entity.Payment;
import com.palm.lingcai.entity.Recharge;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

/**
 * 效验标示
 * @author kelylmall
 *
 */
public class SaltUtils {
	
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static String key="salt";
	private static final int SALT_SIZE = 8;
	/**
	 * 获取订单标效验标示
	 * @param recharge
	 * @return
	 */
	public static void buildRechargeSalt(Recharge recharge){
		StringBuffer rStr=new StringBuffer();
		rStr.append("userId=").append(recharge.getUserId()).append("&");
		rStr.append("money=").append(recharge.getMoney()).append("&");
		rStr.append("channel=").append(recharge.getChannel()).append("&");
		rStr.append("actualMoney=").append(recharge.getActualMoney()).append("&");
		rStr.append("billstatus").append(recharge.getBillstatus()).append("&");
		rStr.append("prebalance").append(recharge.getPrebalance()).append("");
		rStr.append("orderType").append(recharge.getOrderType()).append("&");
		rStr.append("marketplanId").append(recharge.getMarketplanId()).append("&").append(key);
		byte[] saltByte = Digests.generateSalt(SALT_SIZE);
	    byte[] rechargeSalt = Digests.sha1(rStr.toString().getBytes(), saltByte,
	                HASH_INTERATIONS);
		recharge.setSalt(Encodes.encodeHex(rechargeSalt));
	}
	/**
	 * 获取订单标效验标示
	 * @param recharge
	 * @return
	 */
	public static void buildDepositSalt(Deposit deposit){
		StringBuffer rStr=new StringBuffer();
		rStr.append("userId=").append(deposit.getUserid()).append("&");
		rStr.append("money=").append(deposit.getMoney()).append("&");
		rStr.append("method=").append(deposit.getMethod()).append("&");
		rStr.append("actualMoney=").append(deposit.getActualMoney()).append("&");
		rStr.append("status").append(deposit.getStatus()).append("&");
		rStr.append("prebalance").append(deposit.getPrebalance()).append("&").append(key);
		byte[] saltByte = Digests.generateSalt(SALT_SIZE);
	    byte[] rechargeSalt = Digests.sha1(rStr.toString().getBytes(), saltByte,
	                HASH_INTERATIONS);
	    deposit.setSalt(Encodes.encodeHex(rechargeSalt));
	}
	
	/**
	 * 验证订单效验标示
	 * @param recharge
	 * @return
	 */
	public static boolean verifyRechargeSalt(Recharge recharge){
		if(recharge==null){
			return false;
		}
		StringBuffer rStr=new StringBuffer();
		rStr.append("userId=").append(recharge.getUserId()).append("&");
		rStr.append("money=").append(recharge.getMoney()).append("&");
		rStr.append("channel=").append(recharge.getChannel()).append("&");
		rStr.append("actualMoney=").append(recharge.getActualMoney()).append("&");
		rStr.append("billstatus").append(recharge.getBillstatus()).append("&");
		rStr.append("prebalance").append(recharge.getPrebalance()).append("");
		rStr.append("orderType").append(recharge.getOrderType()).append("&");
		rStr.append("marketplanId").append(recharge.getMarketplanId()).append("&").append(key);
		byte[] saltByte = Digests.generateSalt(SALT_SIZE);
	    byte[] rechargeSalt = Digests.sha1(rStr.toString().getBytes(), saltByte,
	                HASH_INTERATIONS);
		if(recharge.getSalt().equals(Encodes.encodeHex(rechargeSalt))){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取交易流水标的效验标示
	 * 
	 * @param bill
	 * @return
	 */
	public static void buildBillSalt(Bill bill){
		if(bill!=null){
			StringBuffer bStr=new StringBuffer();
			bStr.append("amount=").append(bill.getAmount()).append("&");
			bStr.append("userId=").append(bill.getUserid()).append("&");
			bStr.append("indecr=").append(bill.getIndecr()).append("&");
			bStr.append("billchannel=").append(bill.getBillchannel()).append("&");
			bStr.append("billret=").append(bill.getBillret()).append("&");
			bStr.append("prebalance=").append(bill.getPrebalance()).append("&").append(key);
			
			byte[] saltByte = Digests.generateSalt(SALT_SIZE);
		    byte[] billSalt = Digests.sha1(bStr.toString().getBytes(), saltByte,
		                HASH_INTERATIONS);
		    bill.setSalt(Encodes.encodeHex(billSalt));
		}
	}

	/**
	 * 验证交易的效验标示
	 * @param bill
	 * @return
	 */
	public static boolean verifyBillSalt(Bill bill){
		if(bill==null){
			return false;
		}
		StringBuffer bStr=new StringBuffer();
		bStr.append("amount=").append(bill.getAmount()).append("&");
		bStr.append("userId=").append(bill.getUserid()).append("&");
		bStr.append("indecr=").append(bill.getIndecr()).append("&");
		bStr.append("billchannel=").append(bill.getBillchannel()).append("&");
		bStr.append("billret=").append(bill.getBillret()).append("&");
		bStr.append("prebalance=").append(bill.getPrebalance()).append("&").append(key);
		
		byte[] saltByte = Digests.generateSalt(SALT_SIZE);
	    byte[] billSalt = Digests.sha1(bStr.toString().getBytes(), saltByte,
	                HASH_INTERATIONS);
		
	    String encodeHex = Encodes.encodeHex(billSalt);
	    String salt = bill.getSalt();
	    if(encodeHex.equals(salt)){
	    	return true;
	    }
		return false;
	}
	/**
	 * 设置支付对象标示
	 */
	public static void buildPaymentSalt(Payment payment) {
		if(payment!=null){
			StringBuffer pStr=new StringBuffer();
			pStr.append("amount=").append(payment.getAmount()).append("&");
			pStr.append("userId=").append(payment.getUserid()).append("&");
			pStr.append("actualAmount=").append(payment.getActualAmount()).append("&");
			pStr.append("method=").append(payment.getMethod()).append("&");
			pStr.append("marketid=").append(payment.getMarketid()).append("&");
			pStr.append("status=").append(payment.getStatus()).append("&");
			pStr.append("prebalance=").append(payment.getPrebalance()).append("&").append(key);
			
			byte[] saltByte = Digests.generateSalt(SALT_SIZE);
		    byte[] paymentSalt = Digests.sha1(pStr.toString().getBytes(), saltByte,
		                HASH_INTERATIONS);
		    payment.setSalt(Encodes.encodeHex(paymentSalt));
		}
	}
}

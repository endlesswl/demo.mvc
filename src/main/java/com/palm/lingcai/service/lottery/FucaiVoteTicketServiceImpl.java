package com.palm.lingcai.service.lottery;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.nutz.http.Http;
import org.nutz.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.palm.commom.exception.ExceptionCode;
import com.palm.commom.exception.LotteryException;
import com.palm.commom.uitl.CommonStatus;
import com.palm.commom.uitl.DateFormatUtil;
import com.palm.commom.uitl.FileDownloadUtil;
import com.palm.commom.uitl.MD5;
import com.palm.commom.uitl.RandomBallsUtils;
import com.palm.commom.uitl.UUIDUtils;
import com.palm.lingcai.entity.Lottery;
import com.palm.lingcai.entity.Prize;
import com.palm.lingcai.util.ProPertiesUtils;

/**
 * 福彩投注实现 Created by jianhe on 13-12-16. 
 * 
 */
@Service("fucaiVoteTicketService")
public class FucaiVoteTicketServiceImpl implements VoteTicketService {
	 
    private static Logger logger = LoggerFactory.getLogger(FucaiVoteTicketServiceImpl.class);
    /**
     * 服务器接入地址
     */
//	 public final static String SERVER_URL = "http://210.14.139.195:8046/greatwallweb/main";
    public final static String SERVER_URL = ProPertiesUtils.getFucaiServer();
    /**
     * 签名代理名 （alias)
     */
    public final static String ALIAS = "lingcaibao";
    /**
     * 签名密码 （keystorepass)
     */
//	 public final static String KEY_STORE_PASS = "lcbtest";
    public final static String KEY_STORE_PASS =ProPertiesUtils.getFucaiKey();
    /**
     * 接入渠道的id
     */
//	 public final static String PARTNER_ID = "008622";
    public final static String PARTNER_ID=ProPertiesUtils.getFucaiPartnerid();
    /**
     * 传输编码
     */
    public final static String CONTENT_CHARSET = "UTF-8";

    /**
     * 时间格式
     */
    public final static String DATE_FORMAT = "yyyyMMddHHmmss";
    /**
     * 协义版本
     */
    public final static String DEFAULT_VERSION = "1.0";

    /**
     * 购彩人信息
     */
    public final static String USERNAME = "徐洪启";
    public final static String IDCARD = "371122198310203714";
    public final static String PHONE = "13683277123";

    public final static BigDecimal TAX_lEVEL=new BigDecimal(10000);
    /**
     * 获取当前期
     *
     * @param gameid 彩种
     * @return
     */
    @Override
    public Lottery getIssueNo(String gameid) throws LotteryException {
        String transcode = "101";
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode='" + transcode + "' partnerid='" + PARTNER_ID
                + "' version='1.0' time='" + currentTime + "'/><body>" + "<queryIssue gameid='" + gameid
                + "' issueno='' province=''/></body></msg>";
        Lottery lottery = this.receivedIssue(send(msg, transcode), gameid);
        return lottery;
    }

    /**
     * 投注
     *
     * @param lottery
     * @return
     */
    @Override
    public void voteTicket(Lottery lottery) throws LotteryException {
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String transcode = "104";
        // 非预售则置定单ID及投注号码
        if (lottery.getSucessFlag() != 6) {
            if (StringUtils.isEmpty(lottery.getBall())) {
                lottery.setOrderid(UUIDUtils.getSerialID() + "");
                if ("SSQ".equals(lottery.getGameid())) {
                    lottery.setBall(RandomBallsUtils.randomSSQ());
                }
                if ("QLC".equals(lottery.getGameid())) {
                    lottery.setBall(RandomBallsUtils.randomQLC());
                }
            }
        }
        String msg = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + "<msg><head transcode ='" + transcode + "'  partnerid='" + PARTNER_ID
                + "'  version='1.0' time='" + currentTime + "'/>" + " <body><ticketorder gameid='" + lottery.getGameid()
                + "' ticketsnum='1' totalmoney='" + lottery.getMoney() + "' province=''><user userid='' realname='" + USERNAME + "' "
                + "idcard='" + IDCARD + "' phone='" + PHONE + "' /><tickets>" + "<ticket id='" + lottery.getOrderid()
                + "' multiple='1' issue='" + lottery.getIssueNo() + "' playtype='0' money='" + lottery.getMoney() + "'>" + "<ball>"
                + lottery.getBall() + "</ball>" + "</ticket></tickets></ticketorder></body></msg>";
        receivedVoteTicket(send(msg, transcode), lottery);
    }

    /**
     * 查询投注结果
     *
     * @param lottery
     * @return
     */
    @Override
    public void getResult(Lottery lottery) throws LotteryException {
        String transcode = "105";
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<msg>" + "    <head transcode='" + transcode + "' partnerid='"
                + PARTNER_ID + "' version='1.0'" + "          time='" + currentTime + "'/>" + "    <body>" + "        <queryticket id='"
                + lottery.getOrderid() + "'" + "         gameid='" + lottery.getGameid() + "' issue='" + lottery.getIssueNo() + "'/>"
                + "    </body>" + "</msg>";
        receivedResult(send(msg, transcode), lottery);
    }

    /**
     * 开奖查询
     * <p/>
     * 福彩的开奖查询可以用查询新期接口返回开奖号码
     *
     * @param lottery
     */
    @Override
    public void prize(Prize prize) throws LotteryException {
        String transcode = "101";
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode='" + transcode + "' partnerid='" + PARTNER_ID
                + "' version='1.0' time='" + currentTime + "'/><body>" + "<queryIssue gameid='" + prize.getGameid() + "' issueno='"
                + prize.getIssueNo() + "' province=''/></body></msg>";
        String response = send(msg, transcode);
        if (StringUtils.isEmpty(response)) {
            throw new LotteryException("网络太慢了，等一会儿再试吧！", ExceptionCode.NORMAL);
        }
        String[] strs = response.split("</msg>");
        msg = strs[0] + "</msg>";// 返回的XML
        // 验签
        String versign = MD5.Md5For16("701" + msg.trim() + KEY_STORE_PASS);
        if (versign.equals(strs[1].trim())) {
            Pattern pattern = Pattern.compile("prizeball=\"(.*)\"");
            logger.debug("--------fucai-----prize------------------");
            Matcher matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String parizeball = matcher.group(1);// 取得开奖号码
                logger.debug("--------fucai-----prize--------parizeball----------{}", parizeball);
                if (StringUtils.isNotEmpty(parizeball)) {
                    prize.setPrizeball(parizeball);
                }
            }
        }
    }

    /**
     * 解析开奖文件
     *
     * @param prize
     * @return
     */
    @Override
    public List<Prize> prizeFile(Prize prize) throws LotteryException {
        // 查询开奖号码
        String transcode = "122";
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode='" + transcode + "' partnerid='" + PARTNER_ID
                + "' version='1.0' time='" + currentTime + "'/><body>" + "<queryurl gameid='" + prize.getGameid() + "' issue='"
                + prize.getIssueNo() + "' ordertype='partnerOrder' filetype='awards'/></body></msg>";
        String response = send(msg, transcode);
        logger.debug("{}", response);
        if (StringUtils.isEmpty(response)) {
            throw new LotteryException("网络太慢了，等一会儿再试吧！", ExceptionCode.NORMAL);
        }
        String[] strs = response.split("</msg>");
        msg = strs[0] + "</msg>";// 返回的XML
        List<Prize> prizeList = Lists.newArrayList();
        // 验签
        String versign = MD5.Md5For16("722" + msg.trim() + KEY_STORE_PASS);
        if (versign.equals(strs[1].trim())) {
            SAXReader reader = new SAXReader();
            try {
                InputStream is = new ByteArrayInputStream(msg.getBytes());
                Document doc = reader.read(is);
                Element rootElement = doc.getRootElement();
                Element orderElement = rootElement.element("body").element("fileurl").element("url");
                String fileUrl = orderElement.getText();// 取得下载文件地址
                // String path = (String)
                // ApplicationPropertyPlaceholderConfigurer.getContextProperty("prizepath");
                // 设置下载文件保存路径，默认保存在WEB-INF下的prizefile目录下，如：prizefile/DLT/2013/13150.txt
                String rootpath = ProPertiesUtils.getPrizepath();
                String apath = prize.getGameid() + "/" + DateFormatUtil.getYear(new Date()) + "/" + prize.getIssueNo() + ".txt";
                rootpath = rootpath + apath;
                // 下载
                boolean isDowned = FileDownloadUtil.fileDownload(fileUrl, rootpath);
                if (!isDowned) {
                    throw new LotteryException("文件下载失败", ExceptionCode.NORMAL);
                }
                // 置中奖文件地址
                prize.setPrizefile(apath);

                // 读取下载后的中奖文件，并解析
                List<String> prizes = FileDownloadUtil.readFileLines(rootpath);
                if (prizes.size() > 2) {
                    for (String prizeLine : prizes) {
                        String[] line = prizeLine.split("\\t");
                        if (line.length == 10) {
                            Prize newPrize = new Prize();
                            newPrize.setOrderid(line[0]);
                            newPrize.setPalmid(line[1]);
                            newPrize.setPrizeMoney(new BigDecimal(line[8]));// 中奖金额
                            newPrize.setPrizeLevel(line[9]); // 1小奖，2大奖
                            newPrize.setPrizetime(prize.getPrizetime());
                            // 如果是小奖，则不扣税
                            if (newPrize.getPrizeLevel().equals("1")) {
                                newPrize.setPrizeMoneyAfterTax(newPrize.getPrizeMoney());
                                newPrize.setTax(new BigDecimal("0.00"));
                            }else if (newPrize.getPrizeLevel().equals("2")) {
								//大奖
								newPrize.setTax(new BigDecimal("0.2"));//20%个税？
								BigDecimal afterTaxMoney = newPrize.getPrizeMoney().multiply(newPrize.getTax());
								newPrize.setPrizeMoneyAfterTax(afterTaxMoney);
							}
                            newPrize.setPrizeStatus(CommonStatus.PRIZE_STATUS_PRIZED);
                            newPrize.setGameid(prize.getGameid());
                            newPrize.setGametype(prize.getGametype());
                            newPrize.setIssueNo(prize.getIssueNo());
                            newPrize.setPrizefile(apath);
                            newPrize.setPrizeball(prize.getPrizeball());
                            prizeList.add(newPrize);
                        }
                    }
                } else {
                    prizeList.add(prize);
                }
            } catch (DocumentException e) {
                logger.error(e.getMessage());
                throw new LotteryException("当前期不可投注！");
            }
        } else {
            throw new LotteryException("文件不存在", ExceptionCode.NORMAL);
        }
        return prizeList;
    }

    /**
     * 发送请求
     *
     * @param msg
     * @param transcode
     * @return
     */
    public String send(String msg, String transcode) {
        String response = "";
        logger.debug("------fucai send-------{}", msg);
        String key = MD5.Md5For16(transcode + msg + KEY_STORE_PASS);
        Map<String, Object> params = Maps.newHashMap();
        params.put("version", "1.0");
        params.put("partnerid", PARTNER_ID);
        params.put("msg", msg);
        params.put("key", key);
        params.put("transcode", transcode);
        int timeout = 60 * 1000; // 单位毫秒
        try {
            response = Http.post(SERVER_URL, params, timeout);
        } catch (HttpException e) {
            // 重试一次
            response = Http.post(SERVER_URL, params, timeout);
        }
        logger.debug("------fucai recevied-------{}", response);
        return response;
    }

    /**
     * 解析期号查询结果
     *
     * @param response
     * @return
     */
    public Lottery receivedIssue(String response, String gameid) throws LotteryException {
        if (StringUtils.isEmpty(response)) {
            throw new LotteryException("网络太慢了，等一会儿再试吧！", ExceptionCode.NORMAL);
        }
        String[] strs = response.split("</msg>");
        String msg = strs[0] + "</msg>";// 返回的XML
        // logger.debug("------------------{}", msg);
        Lottery lottery = new Lottery();
        String versign = MD5.Md5For16("701" + msg.trim() + KEY_STORE_PASS);
        // logger.debug("{}", versign);
        // 验签
        if (versign.equals(strs[1].trim())) {
            SAXReader reader = new SAXReader();
            try {
                InputStream is = new ByteArrayInputStream(msg.getBytes());
                Document doc = reader.read(is);
                Element rootElement = doc.getRootElement();
                Element orderElement = rootElement.element("body").element("issueinfo");
                String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
                if (null != orderElement) {
                    String beginDate = orderElement.attributeValue("starttime");// 期次开始时间
                    beginDate = DateFormatUtil.dateToString(DateFormatUtil.toDate(beginDate, "yyyy-MM-dd HH:mm:ss"), DATE_FORMAT);
                    String status = orderElement.attributeValue("status");// 期状态信息
                    // 0为预销售
                    // 1为销售中
                    // 2为已结期
                    // 3为已获得开奖公告
                    // 4为已经兑奖完毕
                    String palmtime = orderElement.attributeValue("palmtime");
                    palmtime = DateFormatUtil.dateToString(DateFormatUtil.toDate(palmtime, "yyyy-MM-dd HH:mm:ss"), DATE_FORMAT);
                    // 判断当前时间处理当前期之内，代表可投注，当返回多个可投注期号的时候选择当前期投注
                    if (currentTime.compareTo(beginDate) >= 0 && "1".equals(status) && currentTime.compareTo(palmtime) <= 0) {
//                        logger.debug("----------fucai-------receivedIssue-----------------");
                        lottery.setGameid(orderElement.attributeValue("gameid"));// 彩种
                        lottery.setStarttime(beginDate);// 期次开始时间
                        String endtime = DateFormatUtil.dateToString(
                                DateFormatUtil.toDate(orderElement.attributeValue("endtime"), "yyyy-MM-dd HH:mm:ss"), DATE_FORMAT);
                        lottery.setEndtime(endtime);// 期次结束时间
//                        logger.debug("----------xml-------prizetime-----------------" + orderElement.attributeValue("prizetime"));
                        String prizetime = DateFormatUtil.dateToString(
                                DateFormatUtil.toDate(orderElement.attributeValue("prizetime"), "yyyy-MM-dd HH:mm:ss"), DATE_FORMAT);
//                        logger.debug("----------fucai-------prizetime-----------------" + prizetime);
                        lottery.setPrizetime(prizetime);// 开奖时间
                        lottery.setPalmtime(palmtime);// 平台接收订单截止时间
                        lottery.setIssueNo(orderElement.attributeValue("issue"));// 当前期号
                        if (StringUtils.isEmpty(lottery.getIssueNo())) {
                            throw new LotteryException("当前期不可投注！",ExceptionCode.NORMAL);
                        }
                        String prizeball = orderElement.attributeValue("prizeball");// 开奖号码
                        if (StringUtils.isNotEmpty(prizeball)) {
                            lottery.setPrizeball(prizeball);
                        }
                        lottery.setMoney(2);// 设置投注金额

                        // 设置福彩双色球可投注状态为：可投注
                        // spyMemcachedClient.set(MemcacheKeyConstants.FUCAI_VOTE_STATUS
                        // + gameid, 60 * 60 * 1, "0");
                    } else {
                        throw new LotteryException("当前期投注截止，请开奖后再尝试！", ExceptionCode.NORMAL);
                    }
                } else {
                    // 如果没有正确解析内容，平台返回的是错误消息
                    throw new LotteryException("当前期不可投注！", ExceptionCode.NORMAL);
                }
            } catch (DocumentException e) {
                throw new LotteryException("当前期不可投注！", ExceptionCode.NORMAL);
            }
        } else {// 验签不通过，则返回错误消息体
            logger.error(msg);
            Pattern pattern = Pattern.compile("errorCode=\"(.*)\"");
            Matcher matcher = pattern.matcher(msg);
            if (matcher.find()) {
                if (matcher.group(1).equals("9012")) {// 不存在的期次
                    // 设置福彩双色球可投注状态为：不可投注
                    // spyMemcachedClient.set(MemcacheKeyConstants.FUCAI_VOTE_STATUS
                    // + gameid, 60 * 60 * 1, "1");
                }
            }
            throw new LotteryException("正在开奖中，当前期不可投注！", ExceptionCode.NORMAL);
        }

        return lottery;
    }

    /**
     * 解析投注结果
     * <p/>
     * -<?xml version="1.0" encoding="UTF-8"?> <msg><head transcode="704"
     * partnerid="008622" version="1.0"
     * time="20140114200451"/><body><ticketorder gameid="SSQ" totalmoney="2"
     * ticketsnum="1"><tickets><ticket id="2014011420045166718"
     * palmid="140114200451100003" statuscode="0000" msg="等待交易" multiple="1"
     * issue="2014003" playtype="0"
     * money="2"/></tickets></ticketorder></body></msg> 81FFE9D1B4256E4C
     *
     * @param response
     * @return
     */
    public void receivedVoteTicket(String response, Lottery lottery) throws LotteryException {
        if (StringUtils.isEmpty(response)) {
        	//TODO如果没有接受到response，则将该彩票放入失败队列.
            throw new LotteryException("网络太慢了，等一会儿再试吧！", ExceptionCode.NORMAL);
        }
        String[] strs = response.split("</msg>");
        String msg = strs[0] + "</msg>";// 返回的XML
        String versign = MD5.Md5For16("704" + msg.trim() + KEY_STORE_PASS);
        // 验签
        if (versign.equals(strs[1].trim())) {
            // 将返回的报文原文入库
            lottery.setPalmMsg(msg);
            InputStream is = new ByteArrayInputStream(msg.getBytes());
            SAXReader reader = new SAXReader();
            try {
                Document doc = reader.read(is);
                Element orderElement = doc.getRootElement().element("body").element("ticketorder").element("tickets").element("ticket");
                if (null != orderElement) {
                    String palmid = orderElement.attributeValue("palmid");// 返回平台生成的订单ID
                    String orderid = orderElement.attributeValue("id");// 返回平台生成的订单ID
                    String statuscode = orderElement.attributeValue("statuscode");// 返回状态码
                    if (!CommonStatus.PALM_STATUSCODE_FAIL.equals(statuscode)) {//TODO 如果订单重复提交，则将lottery的状态更新为1
                        lottery.setOrderid(orderid);
                        lottery.setPalmid(palmid);// 平台返回唯一ID
                        lottery.setStatusCode(statuscode);
                        lottery.setSucessFlag(1);// 投注下单成功
                        logger.info("---{}", lottery.getPalmid());
                    } else {
                    	//TODO 投注失败往失败队列添加
                        lottery.setSucessFlag(2);// 投注下单失败
                    }
                }
            } catch (DocumentException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 解析投注查询结果
     *
     * @param response
     * @return
     */
    public void receivedResult(String response, Lottery lottery) throws LotteryException {
        if (StringUtils.isEmpty(response)) {
            throw new LotteryException("网络太慢了，等一会儿再试吧！", ExceptionCode.NORMAL);
        }
        String[] strs = response.split("</msg>");
        String msg = strs[0] + "</msg>";// 返回的XML
        String versign = MD5.Md5For16("605" + msg.trim() + KEY_STORE_PASS);
        // 验签
        if (versign.equals(strs[1].trim())) {
            SAXReader reader = new SAXReader();
            try {
                InputStream is = new ByteArrayInputStream(msg.getBytes());
                Document doc = reader.read(is);
                Element orderElement = doc.getRootElement().element("body").element("ticketresult");
                if (null != orderElement) {
                    String statusCode = orderElement.attributeValue("statuscode");
                    logger.debug("--------resultcode-----------{}", statusCode);
                    lottery.setStatusCode(statusCode);
                    if ("0002".equals(statusCode)) {
                        // 出票成功
                    	lottery.setRemark(response);
                        lottery.setSucessFlag(3);
                    }
                }
            } catch (DocumentException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        FucaiVoteTicketServiceImpl voteTicketServiceFucai = new FucaiVoteTicketServiceImpl();
        Lottery lottery = null;
        try {
            // lottery = voteTicketServiceFucai.getIssueNo("SSQ");
            // voteTicketServiceFucai.voteTicket(lottery);
            // voteTicketServiceFucai.getResult(lottery);
            Prize prize = new Prize();
            prize.setGameid("SSQ");
            prize.setIssueNo("2013141");
            voteTicketServiceFucai.prizeFile(prize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public <T> T prize(Lottery lottery) {
		// TODO Auto-generated method stub
		return null;
	}
}

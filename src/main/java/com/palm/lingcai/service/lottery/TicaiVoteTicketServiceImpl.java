package com.palm.lingcai.service.lottery;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.palm.commom.uitl.CommonStatus;
import com.palm.commom.uitl.DateFormatUtil;
import com.palm.commom.uitl.FileDownloadUtil;
import com.palm.commom.uitl.MD5;
import com.palm.commom.uitl.RandomBallsUtils;
import com.palm.lingcai.entity.Lottery;
import com.palm.lingcai.entity.Prize;
import com.palm.lingcai.exception.ExceptionCode;
import com.palm.lingcai.exception.LotteryException;
import com.palm.lingcai.util.*;

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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 体彩投注实现
 * <p/>
 * Created by jianhe on 13-12-16.
 */
@Service("ticaiVoteTicketService")
public class TicaiVoteTicketServiceImpl implements VoteTicketService {
    private static Logger logger = LoggerFactory
            .getLogger(TicaiVoteTicketServiceImpl.class);

    /**
     * 服务器接入地址
     */
    public final static String SERVER_URL = ProPertiesUtils.getTicaiServer();
    /**
     * 签名代理名 （alias)
     */
    public final static String ALIAS = "lingcaibao";
    /**
     * 签名密码 （keystorepass)
     */
    public final static String KEY_STORE_PASS =ProPertiesUtils.getTicaiKey();
    /**
     * 接入渠道的id
     */
    public final static String PARTNER_ID=ProPertiesUtils.getTicaiPartnerid();
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
    public final static String DEFAULT_VERSION = "2.0";

    /**
     * 购彩人信息
     */
    public final static String USERNAME = "徐洪启";
    public final static String IDCARD = "371122198310203714";
    public final static String PHONE = "13683277123";


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
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode='" + transcode
                + "' partnerid='" + PARTNER_ID
                + "' version='" + DEFAULT_VERSION + "' time='" + currentTime + "'/><body>"
                + "<queryIssue gameid='" + gameid + "' issueno='' province=''/></body></msg>";
        Lottery lottery = receivedIssue(send(msg, transcode));
        return lottery;
    }

    /**
     * 投注
     * 大乐透号码格式：[01,02,03,04,05]/[06,07]，从小到大排序
     * 七星彩号码格式：[0]/[6]/[9]/[2]/[1]/[7]/[4]
     *
     * @param lottery
     * @return
     */
    @Override
    public void voteTicket(Lottery lottery) throws LotteryException {
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String transcode = "104";
        //非预售则置定单ID及投注号码
        if (lottery.getSucessFlag() != 6) {
            lottery.setOrderid(UUIDUtils.getSerialID() + "");
            if ("DLT".equals(lottery.getGameid()) && StringUtils.isEmpty(lottery.getBall())) {
                lottery.setBall(RandomBallsUtils.randomDLT());
            }
            if ("QXC".equals(lottery.getGameid()) && StringUtils.isEmpty(lottery.getBall())) {
                lottery.setBall(RandomBallsUtils.randomQXC());
            }
            logger.debug("----number----{}", lottery.getBall());
        }
        String msg = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"
                + "<msg><head transcode ='" + transcode + "' partnerid='"
                + PARTNER_ID
                + "'  version='1.0' time='" + currentTime + "'/>"
                + " <body>  <ticketorder gameid='" + lottery.getGameid() + "' ticketsnum='1' totalmoney='" + lottery.getMoney() + "' province=''> <user userid='109923' realname='" + USERNAME + "' " +
                "idcard='" + IDCARD + "' phone='" + PHONE + "' /><tickets>"
                + "<ticket id='"
                + lottery.getOrderid()
                + "' multiple='1' issue='"
                + lottery.getIssueNo()
                + "' playtype='1' money='" + lottery.getMoney() + "' addflag='0'>"
                + "<ball>" + lottery.getBall() + "</ball></ticket> </tickets>  </ticketorder> </body></msg>";
        logger.debug("-------voteTicket--------{}", msg);
        receivedVoteTicket(send(msg, transcode), lottery);
    }

    /**
     * 查询投注结果,确认出票成功
     *
     * @param lottery
     * @return
     */
    @Override
    public void getResult(Lottery lottery) throws LotteryException {
        String transcode = "105";
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode='" + transcode
                + "' partnerid='" + PARTNER_ID
                + "' version='" + DEFAULT_VERSION + "' time='" + currentTime + "'/><body>"
                + "<queryticket gameid='" + lottery.getGameid() + "' issue='" + lottery.getIssueNo() + "' id='" + lottery.getOrderid()
                + "'/></body></msg>";
        //发送请求
        String response = send(msg, transcode);
        //解析返回的报文
        Map<String, String> paramsMap = parseTicaiResultParams(response);
        String returnMsg = paramsMap.get("msg");
        String versign = MD5.Md5ForUtf8(paramsMap.get("transcode") + returnMsg.trim() + KEY_STORE_PASS);
        //验签
        if (versign.equals(paramsMap.get("key"))) {
            Pattern pattern = Pattern.compile("statuscode=\"([\\d]+)\"");
            Matcher matcher = pattern.matcher(returnMsg);
            String statusCode = "";
            if (matcher.find()) {
                statusCode = matcher.group(1);//交易代码，0000:交易中，0002:交易成功，0003:交易失败，0004:订单不存在
                logger.debug("--------statusCode-----------{}", statusCode);
            }
            lottery.setStatusCode(statusCode);
            if ("0002".equals(statusCode)) {
                // 出票成功
            	lottery.setRemark(response);
                lottery.setSucessFlag(3);
            }
        }
    }

    /**
     * 单注彩票开奖查询
     * 分两步查询，112接口查开奖号码，107查开奖公告，含中奖金额，中奖等级
     *
     * @param lottery
     */
    @Override
    public void prize(Prize prize) throws LotteryException {
        //查询开奖号码
        String transcode = "112";
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode='" + transcode
                + "' partnerid='" + PARTNER_ID
                + "' version='" + DEFAULT_VERSION + "' time='" + currentTime + "'/><body>"
                + "<queryprizenotice gameid='" + prize.getGameid() + "' issue='" + prize.getIssueNo() + "' province=''/></body></msg>";
        String response = send(msg, transcode);
        Map<String, String> paramsMap = parseTicaiResultParams(response);
        msg = paramsMap.get("msg");
        String versign = MD5.Md5ForUtf8(paramsMap.get("transcode") + msg.trim() + KEY_STORE_PASS);
        //验签
        if (versign.equals(paramsMap.get("key"))) {
            //正则取开奖号码
            //Pattern pattern = Pattern.compile("prizeball=\"([\\d,]+)\"");
            Pattern pattern = Pattern.compile("prizeball=\"(.*?)\"");
            Matcher matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String parizeball = matcher.group(1);//取得开奖号码
                if (StringUtils.isNotEmpty(parizeball)) {
                    prize.setPrizeball(parizeball);
                }
            }
        }
        //取中奖金额
//        prizeReport(prize);
    }

    /**
     * 查询开奖公告
     *
     * @param lottery
     */
    public void prizeReport(Prize prize) throws LotteryException {
        String transcode = "107";
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode='" + transcode
                + "' partnerid='" + PARTNER_ID
                + "' version='" + DEFAULT_VERSION + "' time='" + currentTime + "'/><body>"
                + "<queryprizes gameid='" + prize.getGameid() + "' issue='" + prize.getIssueNo() + "'>" +
                "<queryprize id='" + prize.getOrderid() + "' /></queryprizes></body></msg>";
        String response = send(msg, transcode);
        Map<String, String> paramsMap = parseTicaiResultParams(response);
        msg = paramsMap.get("msg");
        String versign = MD5.Md5ForUtf8(paramsMap.get("transcode") + msg.trim() + KEY_STORE_PASS);
        //验签
        if (versign.equals(paramsMap.get("key"))) {
            //正则
            Pattern pattern = Pattern.compile("prizemoney=\"(.*)\"\\s+tax=\"(.*)\"\\s+wintype=\"(.*)\"");
            Matcher matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String prizemoney = matcher.group(1);//税后中奖金额
                String tax = matcher.group(2);//税金
                String wintype = matcher.group(3);//中奖等级 N: 未中奖 B : 大奖 S : 小奖 W：未派奖 D：订单不存在
                if (StringUtils.isNotEmpty(prizemoney)) {
                    //税后奖金+税金＝中奖金额
                    prize.setPrizeMoney(new BigDecimal(prizemoney).add(new BigDecimal(tax)));
                    //税后奖金
                    prize.setPrizeMoneyAfterTax(new BigDecimal(prizemoney));
                    //税金
                    prize.setTax(new BigDecimal(tax));
                }
                prize.setPrizeLevel(wintype);
                if (StringUtils.equalsIgnoreCase("N", wintype)) {
                    prize.setPrizeStatus(1);//未中奖
                } else if (StringUtils.equalsIgnoreCase("B", wintype) || StringUtils.equalsIgnoreCase("S", wintype)) {
                    prize.setPrizeStatus(2);//中奖
                }
            }
        }
    }


    /**
     * 使用开奖文件更新中奖信息
     *
     * @param lottery
     */
    @Override
    public List<Prize> prizeFile(Prize prize) throws LotteryException {
        //查询开奖号码
        String transcode = "122";
        String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head transcode='" + transcode
                + "' partnerid='" + PARTNER_ID
                + "' version='2.0' time='" + currentTime + "'/><body>"
                + "<queryFile gameid='" + prize.getGameid() + "' issue='" + prize.getIssueNo() + "' played='' day='' filetype='prizeinfo'/></body></msg>";
        String response = send(msg, transcode);
        Map<String, String> paramsMap = parseTicaiResultParams(response);
        msg = paramsMap.get("msg");
        String versign = MD5.Md5ForUtf8(paramsMap.get("transcode") + msg.trim() + KEY_STORE_PASS);
        List<Prize> prizeList = Lists.newArrayList();
        //验签
        if (versign.equals(paramsMap.get("key"))) {
            //正则取开奖号码
            Pattern pattern = Pattern.compile("url=\"(.*)\"");
            Matcher matcher = pattern.matcher(msg);
            if (matcher.find()) {
                String fileUrl = matcher.group(1);//取得下载文件地址
                //设置下载文件保存路径，规则：保存在WEB-INF下的prizefile目前下，如：prizefile/DLT/2013/13150.txt
                String rootpath = ProPertiesUtils.getPrizepath();
                String apath = prize.getGameid() + "/" + DateFormatUtil.getYear(new Date()) + "/" + prize.getIssueNo() + ".txt";
                rootpath = rootpath + apath;
                
                logger.debug("{}", rootpath);
                //下载
                boolean isDowned = FileDownloadUtil.fileDownload(fileUrl, rootpath);
                if (!isDowned) {
                    throw new LotteryException("文件下载失败", ExceptionCode.NORMAL);
                }
                //读取下载后的中奖文件，并解析
                List<String> prizes = FileDownloadUtil.readFileLines(rootpath);
                if (prizes.size() > 4) {
                    for (String prizeLine : prizes) {
                        String[] line = prizeLine.split("\\t");
                        if (line.length == 6) {
                            Prize newPrize = new Prize();
                            newPrize.setOrderid(line[0]);
                            newPrize.setPalmid(line[1]);
                            newPrize.setSerialno(line[2]);
                            newPrize.setPrizeMoney(new BigDecimal(line[4]));// 中奖金额
                            newPrize.setPrizeLevel(line[3]); // 1小奖，2大奖
                            newPrize.setPrizetime(prize.getPrizetime());
                            // 如果是小奖，则不扣税
							if (newPrize.getPrizeLevel().equals("1")) {
								//小奖
								newPrize.setPrizeMoneyAfterTax(newPrize.getPrizeMoney());
								newPrize.setTax(new BigDecimal("0.00"));
							} else if (newPrize.getPrizeLevel().equals("2")) {
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
                }
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
        String key = MD5.Md5ForUtf8(transcode + msg + KEY_STORE_PASS);
        Map<String, Object> params = Maps.newHashMap();
        params.put("version", DEFAULT_VERSION);
        params.put("partnerid", PARTNER_ID);
        params.put("msg", msg);
        params.put("key", key);
        params.put("transcode", transcode);
        int timeout = 60 * 1000; // 单位毫秒
        try {
            response = Http.post(SERVER_URL, params, timeout);
        } catch (HttpException e) {
            //重试一次
            response = Http.post(SERVER_URL, params, timeout);
        }
        logger.debug("----------send-----------{}", response);
        return response;
    }


    /**
     * 解析期号查询结果
     *
     * @param response
     * @return
     */
    public Lottery receivedIssue(String response) throws LotteryException {

        Map<String, String> paramsMap = parseTicaiResultParams(response);

        //如果返回状态码为000，则代表出错或有异常，直接抛出
        if ("000".equals(paramsMap.get("transcode"))) {
            throw new LotteryException("当前时间没有开期，不能投注！", ExceptionCode.NORMAL);
        }

        logger.debug("-----receivedIssue--------{}", paramsMap.get("msg"));
        String versign = MD5.Md5ForUtf8(paramsMap.get("transcode") + paramsMap.get("msg").trim() + KEY_STORE_PASS);
        Lottery lottery = new Lottery();
        //验签
        if (versign.equals(paramsMap.get("key"))) {
            InputStream is = new ByteArrayInputStream(paramsMap.get("msg").getBytes());
            SAXReader reader = new SAXReader();
            try {
                Document doc = reader.read(is);
                Element rootElement = doc.getRootElement();
                List<Element> issueinfoElement = rootElement.element("body").element("issueinfos").elements("issueinfo");
                String currentTime = DateFormatUtils.format(new Date(), DATE_FORMAT);
                for (Element element : issueinfoElement) {
                    String beginDate = element.attributeValue("starttime");
                    String status = element.attributeValue("status");//期状态信息 1销售中，2已结期，4已兑奖
                    String palmtime = element.attributeValue("palmtime");
                    //判断当前时间处理当前期之内，代表可投注，当返回多个可投注期号的时候选择当前期投注
                    if (currentTime.compareTo(beginDate) >= 0 && "1".equals(status) && currentTime.compareTo(palmtime) <= 0) {
                        lottery.setGameid(element.attributeValue("gameid"));//彩种
                        lottery.setGametype(1);//体彩
                        lottery.setStarttime(element.attributeValue("starttime"));//期次开始时间
                        lottery.setEndtime(element.attributeValue("endtime"));//期次结束时间
                        lottery.setPrizetime(element.attributeValue("prizetime"));//开奖时间
                        lottery.setPalmtime(palmtime);//平台接收订单截止时间
                        //String prizeball = element.attributeValue("prizeball");//开奖号码
                        String issueNo = element.attributeValue("issue");//当前期号
                        lottery.setIssueNo(issueNo);
                        if (StringUtils.isEmpty(issueNo)) {
                            throw new LotteryException("当前时间没有开期，不能投注！", ExceptionCode.NORMAL);
                        }
                        lottery.setMoney(2);//设置投注金额
                        lottery.setUnitprice("200");
                    } else {
                        throw new LotteryException("当前期投注截止，请开奖后再尝试！", ExceptionCode.NORMAL);
                    }
                }
            } catch (DocumentException e) {
                throw new LotteryException("当前时间没有开期，不能投注！", ExceptionCode.NORMAL);
            }
        }
        return lottery;
    }

    /**
     * 解析投注结果
     *
     * @param response
     * @return
     */
    public void receivedVoteTicket(String response, Lottery lottery) throws LotteryException {
        Map<String, String> paramsMap = parseTicaiResultParams(response);

        //如果返回状态码为000，则代表出错或有异常，直接抛出
        if ("000".equals(paramsMap.get("transcode"))) {
            throw new LotteryException("当前期不可投注！");
        }

        String msg = paramsMap.get("msg");
        logger.debug("-----receivedVoteTicket--------{}", msg);
        String versign = MD5.Md5ForUtf8(paramsMap.get("transcode") + msg.trim() + KEY_STORE_PASS);
        //验签
        if (versign.equals(paramsMap.get("key"))) {
            //将投注原文报文入库
            lottery.setPalmMsg(msg);
            Pattern pattern = Pattern.compile("palmid=\"([\\d]+)\"\\s+statuscode=\"([\\d]+)\"");
            Matcher matcher = pattern.matcher(msg);
            String statusCode = "";
            String palmid = "";
            if (matcher.find()) {
                palmid = matcher.group(1);//平台生成唯一订单ID
                statusCode = matcher.group(2);//返回状态码
            }
            if (statusCode.equals("0000")) {
                lottery.setPalmid(palmid);//平台生成唯一订单ID
                lottery.setStatusCode(statusCode);
                lottery.setSucessFlag(1);//投注下单成功
            } else {
                lottery.setSucessFlag(2);//投注下单失败
            }
        }
    }

    /**
     * 解析体彩全国平台返回主体MSG字符串
     *
     * @param response
     * @return map 参数map
     */
    public static Map<String, String> parseTicaiResultParams(String response) throws LotteryException {
        if (StringUtils.isEmpty(response)) {
            throw new LotteryException("网络太慢了，等一会儿再试吧！", ExceptionCode.NORMAL);
        }
        String[] arrSplit = response.split("[&]");
        Map<String, String> map = Maps.newHashMap();
        for (String strSplit : arrSplit) {
            if (strSplit.indexOf("msg=") == 0) {
                //只替换首个MSG，返回的报文中有其他msg=的内容
                map.put("msg", strSplit.replaceFirst("msg=", ""));
            }
            if (strSplit.indexOf("key=") == 0) {
                map.put("key", strSplit.replace("key=", ""));
            }
            if (strSplit.indexOf("transcode=") == 0) {
                map.put("transcode", strSplit.replace("transcode=", ""));
            }
        }
        return map;
    }

    public static void main(String[] args) {
        TicaiVoteTicketServiceImpl voteTicketService = new TicaiVoteTicketServiceImpl();
        Lottery lottery = new Lottery();
        lottery.setGameid("DLT");
        lottery.setIssueNo("13149");
        lottery.setOrderid("1387438685808");
//        voteTicketService.prizeReport(lottery);
        try {
//            lottery = voteTicketService.getIssueNo("DLT");
            Prize prize = new Prize();
            prize.setGameid("DLT");
            prize.setIssueNo("13150");
            BigDecimal multiple = new BigDecimal("2.00").divide(new BigDecimal("0.10"), BigDecimal.ROUND_DOWN);
            logger.info("{}", multiple);
            logger.info("{}", new BigDecimal("2342123.23").divide(multiple, BigDecimal.ROUND_HALF_EVEN));
//            voteTicketService.prizeFile(prize);
//            voteTicketService.sendByHttps("", "");
//            lottery = voteTicketService.voteTicket(lottery);
//            voteTicketService.getResult(lottery);
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

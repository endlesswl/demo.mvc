package com.palm.lingcai.util.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 短信发送服务类
 * Created by jianhe on 14-1-26.
 */
@Service
@Transactional
public class SmsSendService {
    private static Logger logger = LoggerFactory
            .getLogger(SmsSendService.class);

    /**
     * 漫道科技短信网关序列号
     */
    private static final String SN = "SDK-BBX-010-19744";
    /**
     * 漫道科技短信网关密码
     */
    private static final String PWD = "d-063_-4";

    /**
     * 批量发送短信
     *
     * @param mobile
     * @param Content
     */
    public static void sendBatch(List<String> mobileList, String content) {
        SmsClient client = null;
        try {
            client = new SmsClient(SN, PWD);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int i = 1;
        StringBuffer strf = new StringBuffer();
        for (String mobile : mobileList) {
            if (RegexValidator.isMobile(mobile)) {
                strf.append(mobile).append(",");
            }
            if (i % 1000 == 0) {
                String result_mt = client.mt(strf.substring(0, strf.length() - 1), content.trim() + "【零彩宝】", "", "", "");
                logger.debug("{}", strf);
                strf.delete(0, strf.length());
                if (result_mt.startsWith("-") || result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
                {
                    logger.debug("--------批量短信下发失败---------{}", result_mt);
                }
                //输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
                else {
                    logger.debug("--------批量短信下发成功---------{}", result_mt);
                }
            }
            i++;
        }
        logger.debug("--1--{}", strf);
        String result_mt = client.mt(strf.substring(0, strf.length() - 1), content.trim() + "【零彩宝】", "", "", "");
        if (result_mt.startsWith("-") || result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
        {
            logger.debug("--------批量短信下发失败---------{}", result_mt);
        }
        //输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
        else {
            logger.debug("--------单条短信下发成功---------{}", result_mt);
        }
    }

    /**
     * 发送单条短信
     *
     * @param mobile
     * @param content
     */
    public static void sendSingle(String mobile, String content) {
        try {
            SmsClient client = new SmsClient(SN, PWD);
            String result_mt = client.mt(mobile, content + "【零彩宝】", "", "", "");
            if (result_mt.startsWith("-") || result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
            {
                logger.debug("--------单条短信下发失败---------{}", result_mt);
            }
            //输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
            else {
                logger.debug("--------单条短信下发成功---------{}", result_mt);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //输入软件序列号和密码

//        String mobiles = "18911244763,15801469625";
        String mobiles = "13426297024";
        String content = "亲爱的零彩宝用户，您的密码已重置为：823423，零彩宝不会以任何理由向您索要密码，请妥善保管好您的密码，祝您好运连连！手机可访问：http://m.lingcaibao.com 官网查看我的【零彩宝】";
        SmsClient client = new SmsClient(SN, PWD);
        String result_mt = client.mt(mobiles, content, "", "", "");
        if (result_mt.startsWith("-") || result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
        {
            System.out.print("发送失败！返回值为：" + result_mt + "请查看webservice返回值对照表");
        }
        //输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
        else {
            System.out.print("发送成功，返回值为：" + result_mt);
        }
    }


}

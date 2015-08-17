package com.palm.lingcai.util.sms;

import com.google.common.collect.Maps;
import org.nutz.http.Http;
import org.nutz.http.Response;

import java.util.Map;

public class SmsContentBean {
    /**
     * 短信网关请求服务器地址
     */
    public static final String SMS_SERVER_URL = "http://dx.sp1065.cn/sendsms";

    /**
     * 短信网关连接超时设置,单位毫秒
     */
    public static final Integer SMS_SERVER_TIMEOUT = 60 * 1000;

    /**
     * 用户名
     */
    private String username = "ygct";
    /**
     * 密码
     */
    private String password = "432561";
    /**
     * 命令
     */
    private String order = "sendsms";
    /**
     * 短信内容
     */
    private String spnumber = "221";
    /**
     * 短信接收手机号
     */
    private String phone;
    /**
     * 短信内容
     */
    private String msgcont;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSpnumber() {
        return spnumber;
    }

    public void setSpnumber(String spnumber) {
        this.spnumber = spnumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsgcont() {
        return msgcont;
    }

    public void setMsgcont(String msgcont) {
        this.msgcont = msgcont;
    }

    public String toUrlPostfix() {
        // 短信提交接口，发送请求的参数串
        String parameters = SMS_SERVER_URL + "?username="
                + this.username
                + "&password="
                + this.password
                + "&order="
                + this.order
                + "&phone="
                + this.phone
                + "&spnumber="
                + this.spnumber
                + "&msgcont="
                + this.msgcont + "%20【零彩宝】";
        return parameters;
    }

    public Map<String, Object> buildParams() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("username", this.username);
        params.put("password", this.password);
        params.put("order", this.order);
        params.put("phone", this.phone);
        params.put("spnumber", this.spnumber);
        params.put("msgcont", this.msgcont + "【山西福彩】");
        return params;
    }

    /**
     * 默认的构造函数
     */
    public SmsContentBean() {

    }

    public static void main(String[] args) {
//        SmsContentBean smsContentBean = new SmsContentBean();
//        smsContentBean.setPhone("13522505810");
//        smsContentBean.setMsgcont("为您私人定制了新年祝福，并赠送您双色球零彩。点击链接收祝福，领零彩！马上变土豪！http://m.lingcaibao.com/wish/asdf3U87");
//        int timeout = 60 * 1000; // 单位毫秒
//        System.err.println(smsContentBean.toUrlPostfix());
//        Response response = Http.get(smsContentBean.toUrlPostfix(), timeout);

        SmsContentBean smsContentBean = new SmsContentBean();
        smsContentBean.setPhone("13426297024");
        smsContentBean.setMsgcont("尊敬的用户，您的验证码是：999999，请输入完成操作。温馨提示：请勿泄露于他人。");
        Response response = Http.get(smsContentBean.toUrlPostfix(), SmsContentBean.SMS_SERVER_TIMEOUT);

        System.out.println(response.getContent());
    }

}

package com.palm.lingcai.statuscode;

/**
 * 交易来源枚举
 * Created by jianhe on 14-2-19.
 */
public enum BillChannelEnum {
    DEPOSIT("充值"),//充值
    PAYMENT("支付"),//直接支付
    CALLBACK("回滚"),//回滚
    CASHUP("结算"),//结算
    SYSTEM("系统操作"),//系统操作
    FIXEDPRIZE("兑奖"),//兑奖
    WITHDRAW("提现"),//提现
    H5DPRIZE("H5兑奖");//H5兑奖,不计入

    private String name;

    private BillChannelEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 根据编码获取中文名称
     * @param value
     * @return
     */
    public static String getName(int value) {
        for (BillChannelEnum channelEnum : BillChannelEnum.values()) {
            if (channelEnum.ordinal() == value) {
                return channelEnum.name;
            }
        }
        return "";
    }
}

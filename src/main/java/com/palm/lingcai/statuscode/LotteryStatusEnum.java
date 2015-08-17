package com.palm.lingcai.statuscode;

/**
 * 彩票状态码
 * <p/>
 * 彩票状态码定义
 * Created by wangxf on 14-2-16.
 */
public enum LotteryStatusEnum {
    PROCESSING("处理中", 0),//处理中
    WAIT_CHECKOUT("待出票", 1),//待出票
    SUCCESS_CHECKOUT("出票成功", 2),//出票成功
    FAILURE_CHECKOUT("出票失败", 3);//出票失败
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private LotteryStatusEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(LotteryStatusEnum.WAIT_CHECKOUT.getValue());
    }
}

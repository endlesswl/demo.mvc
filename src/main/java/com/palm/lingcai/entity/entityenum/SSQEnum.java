package com.palm.lingcai.entity.entityenum;

/**
 * 双色球彩种枚举
 * Created by jianhe on 14-5-1.
 */
public enum SSQEnum {

    PRIZEDAY("2,4,7"),//开奖日
    LINGCAITIME("193000"),//零彩宝结期时间
    PALMTIME("195700"),//平台截止收单时间
    PALMNEWISSUETIME("235959");//平台开新期时间


    private String name;

    private SSQEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 根据编码获取中文名称
     *
     * @param value
     * @return
     */
    public static String getName(int value) {
        for (SSQEnum ssqEnum : SSQEnum.values()) {
            if (ssqEnum.ordinal() == value) {
                return ssqEnum.name;
            }
        }
        return "";
    }
}

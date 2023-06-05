package com.xiaoxiong.learning.enums;

/**
 * @Author 六月
 * @Date 2023/6/4 17:16
 * @Version 1.0
 */
public class EnumCase {

    public static void main(String[] args) {
        for (FragmentsEnum value : FragmentsEnum.values()) {
            System.out.println(value.name());
        }
    }


    /** 碎片道具集合 */
    public enum FragmentsEnum {
        SZZSP1_GIFT, SZZSP2_GIFT, SZZSP3_GIFT, SZZSP4_GIFT, SZZSP5_GIFT, SZZSP6_GIFT;

        public static FragmentsEnum get(String name) {
            for (FragmentsEnum gift : values()) {
                if (gift.name().equals(name)) {
                    return gift;
                }
            }
            return null;
        }
    }

}

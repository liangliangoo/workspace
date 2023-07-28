package com.xiaoxiong.algorithm.leetcode.work2023.july;

/**
 * @Author 六月
 * @Date 2023/7/26 13:45
 * @Version 1.0
 */
public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null) {
            return false;
        }
        for (int i = 0; i < flowerbed.length && n > 0; ) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                n--;
                i += 2;
            } else {
                i += 3;
            }
        }
        return n <= 0;
    }

}

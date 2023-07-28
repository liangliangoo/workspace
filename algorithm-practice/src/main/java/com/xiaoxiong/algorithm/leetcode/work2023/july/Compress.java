package com.xiaoxiong.algorithm.leetcode.work2023.july;

/**
 * <a href="https://leetcode.cn/problems/string-compression/solution/ya-suo-zi-fu-chuan-by-leetcode-solution-kbuc/">压缩字符串</a>
 * @Author 六月
 * @Date 2023/7/26 13:57
 * @Version 1.0
 */
public class Compress {

    public int compress(char[] chars) {
        int length = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < length; read++) {
            if (read == length - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                int num = read - left + 1;
                if (num > 1) {
                    int anchor = write;
                }
            }
        }
        return -1;
    }
}

package com.xiaoxiong.algorithm.leetcode.work2023.july;

import java.util.HashSet;
import java.util.Set;

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
                    while (num > 0) {
                        chars[write++] = (char) (num % 10 + '0');
                        num /= 10;
                    }
                    reverse(chars, anchor, write - 1);
                }
                left = read + 1;
            }
        }
        return write;
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    private int compressToSet(char[] chars) {
        Set<Object> set = new HashSet<>();
        int length = chars.length;
        int left = 0;
        for (int read = 0; read < length; read++) {
            if (read == length - 1 || chars[read] != chars[read + 1]) {
                char chr = chars[read];
                int num = read - left + 1;
                if (num > 1) {
                    set.add(chr);
                    set.add(num);
                }
            }
        }
        return set.size();
    }
}

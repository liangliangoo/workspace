package com.xiaoxiong.learning.currentLimiting;

import java.util.Arrays;

/**
 * @Author 六月
 * @Date 2023/7/25 15:38
 * @Version 1.0
 */
public class SlidingWindowLimiter {

    private final int size;
    private final int period;
    private int[] window;
    private int current;

    public SlidingWindowLimiter(int size, int period) {
        this.size = size;
        this.period = period;
        this.window = new int[size];
        this.current = 0;
    }

    public static SlidingWindowLimiter create(int size, int period) {
        return new SlidingWindowLimiter(size, period);
    }

    public boolean acquire() {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        int timeSlot = currentTime / period;

        if (timeSlot > current) {
            int offset = timeSlot - current;
            if (offset >= size) {
                window = new int[size];
            } else {
                System.arraycopy(window, offset, window, 0, size - offset);
                Arrays.fill(window, size - offset, size, 0);
            }
            current = timeSlot;
        }

        window[size - 1]++;
        return Arrays.stream(window).sum() <= size;
    }

}

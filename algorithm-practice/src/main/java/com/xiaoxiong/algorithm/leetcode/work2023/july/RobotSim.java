package com.xiaoxiong.algorithm.leetcode.work2023.july;

import java.util.HashMap;

/**
 * <a href="https://leetcode.cn/problems/walking-robot-simulation/submissions/"></>
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2023/7/19  23:03
 */
public class RobotSim {

    public int robotSim(int[] commands, int[][] obstacles) {
        int[] dx = {1, 0, -1, 0};   // 向东、南、西、北移动是x坐标的增量
        int[] dy = {0, -1, 0, 1};   // 向东、南、西、北移动是y坐标的增量
        int res = 0;
        int x = 0, y = 0;
        HashMap<Integer,Integer> record = new HashMap();
        for (int i = 0; i < obstacles.length; i++) {
            for (int j = 0; j < obstacles[i].length; j++) {

            }
        }
        return 0;
    }

}

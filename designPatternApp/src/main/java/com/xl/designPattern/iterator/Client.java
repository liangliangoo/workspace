package com.xl.designPattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangliang
 * @date 2021/3/7 12:18
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        List<College> colleges = new ArrayList<>();
        colleges.add(new ComputerCollege());
        colleges.add(new InfoCollege());
        OutPutImpl outPut = new OutPutImpl(colleges);
        outPut.printCollege();
    }
}

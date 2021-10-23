package com.xl.designPattern.facade;

/**
 * @author liangliang
 * @date 2021/2/15 20:21
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();
        homeTheaterFacade.end();
        System.out.println();
    }

}

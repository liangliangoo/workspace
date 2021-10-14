package com.xiaoxiong.reactordemo.reactor1;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/10/12  21:46
 */
public class ReactorMain {
    public static void main(String[] args) {
        Reactor reactor = new Reactor(8080);
        reactor.run();
    }
}

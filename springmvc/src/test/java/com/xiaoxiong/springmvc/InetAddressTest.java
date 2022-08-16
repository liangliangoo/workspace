package com.xiaoxiong.springmvc;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author liuyue
 * @Date 2022/7/9 17:14
 * @Version 1.0
 */
public class InetAddressTest {

    public static void main(String[] args) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Local HostAddress: "+addr.getHostAddress());
                String hostname = addr.getHostName();
        System.out.println("Local host name: "+hostname);
    }
}

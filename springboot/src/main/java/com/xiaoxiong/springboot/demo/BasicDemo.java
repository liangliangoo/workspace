package com.xiaoxiong.springboot.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/23 9:32
 */
@Component
public class BasicDemo {

    @Autowired
    private TestDemo testDemo;

    public String process(String data) {
        return Data.process(data);
    }

    private String show() {
        return testDemo.showCase();
    }

    String test() {
        return testDemo.testCase();
    }

    public enum Data {
        SHOW("show") {
            @Override
            String doProcess() {
                return SpringUtil.getBean(BasicDemo.class).show();
            }
        },
        CASE("test") {
            @Override
            String doProcess() {
                return SpringUtil.getBean(BasicDemo.class).test();
            }
        };

        private String data;

        Data(String data) {
            this.data = data;
        }

        abstract String doProcess();

        static String process(String data) {
            for (Data d: values()) {
                if (d.data.equalsIgnoreCase(data)) {
                    return d.doProcess();
                }
            }
            return null;
        }
    }

}

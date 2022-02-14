package com.xiaoxiong.mockdata.enity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/2/14 12:02
 */
@Component
public class C {

    @Autowired
    private B b;
    public void needExec(){
    }

}

package com.xiaoxiong.springmvc.component.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @Author liuyue
 * @Date 2022/7/7 22:50
 * @Version 1.0
 */
@Component
public class I18nUtils {

    @Resource
    private LocaleMessage localeMessage;

    /**
     * 获取key
     *
     * @param key
     * @return
     */
    public  String getKey(String key) {
        String name = localeMessage.getMessage(key);
        return name;
    }

    /**
     * 获取指定哪个配置文件下的key
     *
     * @param key
     * @param local
     * @return
     */
    public  String getKey(String key, Locale local) {
        String name = localeMessage.getMessage(key, local);
        return name;
    }

}

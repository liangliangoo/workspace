package com.xiaoxiong.redisdemo.case3.hyperloglog;

import com.xiaoxiong.redisdemo.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/22 13:37
 */
@Slf4j
@RestController
@RequestMapping("/case3/test")
public class IpAddressController {

    @GetMapping("/ip")
    public String getIpAddress(HttpServletRequest request) {
        return IpUtil.getIpAddress(request);
    }

}

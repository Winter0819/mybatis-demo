package com.dachen.demo.mybatis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author : robert
 * @desc : 业务属性
 * @date : 2017/7/26 10:53
 */
@Component
@ConfigurationProperties(prefix = "service.winter")
public class ServiceProperties {

    private Long workerId;
    

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

}

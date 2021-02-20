package com.gl.springbootlearn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "project-url")
public class ProjectUrlConfig {

    public String wechatMpAuthorize;

    public String wechatOpenAuthorize;

    private String sell;
}

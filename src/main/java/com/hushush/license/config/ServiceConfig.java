package com.hushush.license.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * ServiceConfig
 */
@Configuration
@ConfigurationProperties(prefix="example")
@Getter
@Setter
public class ServiceConfig {

    private String property;
}
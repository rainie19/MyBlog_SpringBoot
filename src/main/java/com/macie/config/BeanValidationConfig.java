package com.macie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 为其他组件配置数据校验
 * @author Macie
 * @date 2021/7/5 -14:43
 */
@ComponentScan(basePackages = "com.macie.service")
@Configuration
public class BeanValidationConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}

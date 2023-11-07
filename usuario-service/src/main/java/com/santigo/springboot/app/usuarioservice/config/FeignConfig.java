package com.santigo.springboot.app.usuarioservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients

public class FeignConfig {
    
    @Bean
    public SpringMvcContract feignContract() {
        return new SpringMvcContract();
    }
}

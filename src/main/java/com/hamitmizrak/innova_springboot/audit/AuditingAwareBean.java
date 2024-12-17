package com.hamitmizrak.innova_springboot.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditingAwareBean {

    @Bean
    public AuditorAware<String> auditorAwareBeanMethod() {
        return new AuditorAwareImpl();
    }
}

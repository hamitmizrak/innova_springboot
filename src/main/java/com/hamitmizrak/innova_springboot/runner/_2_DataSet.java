package com.hamitmizrak.innova_springboot.runner;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

// LOMBOK
@Log4j2

@Configuration // for Bean Configuration
@Order(2) // 2. class CommandLineRunner olarak çalışsın
public class _2_DataSet {


    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Project Data set -2 ");
            log.info("Project Data set -2 ");
        };
    } // end Bean
}

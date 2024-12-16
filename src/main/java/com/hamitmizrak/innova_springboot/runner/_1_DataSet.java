package com.hamitmizrak.innova_springboot.runner;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

// LOMBOK
//@Log4j2

@Order(1) // İlk bu class CommandLineRunner olarak çalışsın
@Configuration
public class _1_DataSet {

    private void address(){
        System.out.println("Address -1");
        //log.info("Address -1");
    }

    @Bean
    public CommandLineRunner getDataSet1(){
        return args -> {
            address();
        };
    }
}

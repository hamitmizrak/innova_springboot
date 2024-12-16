package com.hamitmizrak.innova_springboot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// LOMBOK
//@Log4j2

@Component //Bu nesnesin bir Spring Framework'un bir parçası olmasını sağlayacağım
@Order(2) // 2. class CommandLineRunner olarak çalışsın
public class _2_DataSet implements CommandLineRunner {


    private void address(){
        System.out.println("Address -2");
        //log.info("Address -2");
    }

    @Override
    public void run(String... args) throws Exception {
        address();
    }
}

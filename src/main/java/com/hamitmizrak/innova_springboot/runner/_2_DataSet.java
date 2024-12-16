package com.hamitmizrak.innova_springboot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

// LOMBOK
//@Log4j2

@Order(2) // 2. class CommandLineRunner olarak çalışsın
public class _2_DataSet implements CommandLineRunner {


    private void address(){
        System.out.println("Address -2");
        //log.info("Address -2");
    }

    @Override
    public void run(String... args) throws Exception {
        address();
        System.out.println("Address -2");
    }
}

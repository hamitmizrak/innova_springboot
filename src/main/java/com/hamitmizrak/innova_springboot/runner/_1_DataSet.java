package com.hamitmizrak.innova_springboot.runner;


import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// LOMBOK
@Log4j2

@Order(1) // İlk bu class CommandLineRunner olarak çalışsın
@Component //Bu nesnesin bir Spring Framework'un bir parçası olmasını sağlayacağım
public class _1_DataSet implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Project Data set -1 ");
    }
}

package com.hamitmizrak.innova_springboot.runner;


import com.hamitmizrak.innova_springboot.business.dto.AddressDto;
import com.hamitmizrak.innova_springboot.business.dto.CustomerDto;
import com.hamitmizrak.innova_springboot.business.services.interfaces.IAddressService;
import com.hamitmizrak.innova_springboot.business.services.interfaces.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// LOMBOK
@Log4j2
@RequiredArgsConstructor

@Order(1) // İlk bu class CommandLineRunner olarak çalışsın
@Component //Bu nesnesin bir Spring Framework'un bir parçası olmasını sağlayacağım
public class _1_DataSet implements CommandLineRunner {

    // Injection
    private final IAddressService iAddressService;
    private final ICustomerService iCustomerService;

    // Address Random Create
    private List<AddressDto> addressRandomSave(){
        List<AddressDto> addressDtoList = new ArrayList<>();

        // For Loop
        for (int i = 1; i <=15 ; i++) {
            AddressDto addressDto = new AddressDto();
            addressDto.setDoorNumber("door number"+i);
            addressDto.setStreet("street"+i);
            addressDto.setAvenue("avenue"+i);
            addressDto.setCity("city"+i);
            addressDto.setState("state number"+i);
            addressDto.setZipCode("zip code"+i);
            addressDto.setDescription("description"+i);
            addressDto.setAddressQrCode(UUID.randomUUID().toString());
            addressDtoList.add(addressDto);
            iAddressService.objectServiceCreate(addressDto);
        }
        return addressDtoList;
    }

    // Address Single Create
    private AddressDto addressSave(){
        AddressDto addressDto= new AddressDto();
        addressDto.setDoorNumber("door number");
        addressDto.setStreet("street");
        addressDto.setAvenue("avenue");
        addressDto.setCity("city");
        addressDto.setState("state number");
        addressDto.setZipCode("zip code");
        addressDto.setDescription("description");
        addressDto.setAddressQrCode(UUID.randomUUID().toString());
        return addressDto;
    }

    // Address Single Create
    private CustomerDto customerSave(){

        // AddressDto
        AddressDto addressDto= addressSave();

        // CustomerDto
        CustomerDto customerDto= new CustomerDto();
        customerDto.setFistname("Hamit");
        customerDto.setLastname("Mızrak");
        customerDto.setNotes("Notes");

        // Composition
        customerDto.setCompositionAddressDto(addressDto);

        // Customer Save
        iCustomerService.objectServiceCreate(customerDto);
        System.out.println(customerDto);

        return customerDto;
    }

    // Product

    // Order

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Project Data set -1 ");
        log.info("Project Data set -1 ");
        //addressSave();
        customerSave();
    }
}

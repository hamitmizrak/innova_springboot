package com.hamitmizrak.innova_springboot.runner;


import com.hamitmizrak.innova_springboot.aspect.AuditLogEntity;
import com.hamitmizrak.innova_springboot.aspect.AuditingAspect;
import com.hamitmizrak.innova_springboot.aspect.IAuditLogRepository;
import com.hamitmizrak.innova_springboot.business.dto.AddressDto;
import com.hamitmizrak.innova_springboot.business.dto.CustomerDto;
import com.hamitmizrak.innova_springboot.business.dto.OrderDto;
import com.hamitmizrak.innova_springboot.business.dto.ProductDto;
import com.hamitmizrak.innova_springboot.business.services.interfaces.IAddressService;
import com.hamitmizrak.innova_springboot.business.services.interfaces.ICustomerService;
import com.hamitmizrak.innova_springboot.business.services.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

// LOMBOK
@Log4j2
@RequiredArgsConstructor

@Order(1) // İlk bu class CommandLineRunner olarak çalışsın
@Component //Bu nesnesin bir Spring Framework'un bir parçası olmasını sağlayacağım
public class _1_DataSet implements CommandLineRunner {

    // Injection
    private final IAddressService iAddressService;
    private final ICustomerService iCustomerService;
    private final IOrderService iOrderService;

    // AOP
    private final IAuditLogRepository iAuditLogRepository;
    private final AuditingAspect auditingAspect;
    // AOP
    List<AuditLogEntity> auditLogs = new ArrayList<>();

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

    /////////////////////////////////////////////////////
    // RELATION
    // Address Single Create
    private AddressDto relationaAddressSave(){
        AddressDto addressDto= new AddressDto();
        addressDto.setDoorNumber("door number");
        addressDto.setStreet("street");
        addressDto.setAvenue("avenue");
        addressDto.setCity("city");
        addressDto.setState("state number");
        addressDto.setZipCode("zip code");
        addressDto.setDescription("description");
        addressDto.setAddressQrCode(UUID.randomUUID().toString());

        // AOP Save
        auditLogs.add(new AuditLogEntity("AddressEntity", "CREATE", "HamitM", new Date()));
        return addressDto;
    }

    // Address Single Create
    private CustomerDto relationCustomerSave(){

        // AddressDto
        AddressDto addressDto= relationaAddressSave();

        // CustomerDto
        CustomerDto customerDto= new CustomerDto();
        customerDto.setFistname("Hamit");
        customerDto.setLastname("Mızrak");
        customerDto.setNotes("Notes");

        // Composition
        customerDto.setCompositionAddressDto(addressDto);

        // Customer Save
        // iCustomerService.objectServiceCreate(customerDto);
        // System.out.println(customerDto);

        // AOP Save
        auditLogs.add(new AuditLogEntity("CustomerEntity", "CREATE", "HamitM", new Date()));
        return customerDto;
    }

    // Products
    private ProductDto[] relationProductSave(){
        ProductDto[] productDtoList= new ProductDto[2];

        // Ürün-1
        ProductDto productDto1= ProductDto.builder()
                .name("Masaüstü")
                .trade("xyz")
                .notes("i9")
                .build();

        // Ürün-2
        ProductDto productDto2= ProductDto.builder()
                .name("Laptop")
                .trade("mnp")
                .notes("i7")
                .build();

        // AOP Save
        // auditLogs.add(new AuditLogEntity("ProductEntity", "CREATE", "HamitM", new Date()));
        return new ProductDto[]{productDto1,productDto2};
    }

    // Order
    private void relationOrderSave(){
        System.out.println("###############################");
        System.out.println("Order Verileri Kaydediliyor....");

        // Sipariş -1
        OrderDto orderDto1=OrderDto.builder()
                .name("Malatya")
                .price("20")
                .notes("order notes")
                .build();

        // Composition
        // Order'un içine Müşteri Ekle NOT: Customer içinde de zaten Address vardı.
        orderDto1.setCompositionCustomerDto(relationCustomerSave());

        // Order'un içine Sipariş Ekle
        orderDto1.setCompositionProductDtoList(Arrays.asList(relationProductSave()[0],relationProductSave()[1]));

        // Order Database Kaydet
        OrderDto orderDtoSave= (OrderDto) iOrderService.objectServiceCreate(orderDto1);
        System.out.println(orderDtoSave);

        // AOP Save
        auditLogs.add(new AuditLogEntity("ProductEntity", "CREATE", "HamitM", new Date()));
        auditLogs.add(new AuditLogEntity("OrderEntity", "CREATE", "HamitM", new Date()));
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Project Data set -1 ");
        log.info("Project Data set -1 ");
        //addressSave();
        //relationCustomerSave();
        relationOrderSave();

        // Toplu olarak Audit Logs kaydet
        auditingAspect.logBatchAudit(auditLogs);
        System.out.println("Tüm kayıtlar ve audit log'lar tamamlandı.");
    }
}

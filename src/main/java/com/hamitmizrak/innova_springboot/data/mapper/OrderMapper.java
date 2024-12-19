package com.hamitmizrak.innova_springboot.data.mapper;

import com.hamitmizrak.innova_springboot.business.dto.OrderDto;
import com.hamitmizrak.innova_springboot.data.entity.OrderEntity;

public class OrderMapper {

    // 1- OrderEntity'i OrderDto'a çevir
    public static OrderDto OrderEntityToOrderDto(OrderEntity orderEntity) {
        // Instance(OrderDto)
        OrderDto orderDto = new OrderDto();

        // Field
        orderDto.setId(orderEntity.getId());
        orderDto.setName(orderEntity.getName());
        orderDto.setPrice(orderEntity.getPrice());
        orderDto.setNotes(orderEntity.getNotes());

        // DİKKAT: Composition (Order(N)- Customer(1))
        if(orderEntity.getCustomerOrderEntity()!=null) {
            orderDto.setCompositionCustomerDto(CustomerMapper.CustomerEntityToCustomerDto(orderEntity.getCustomerOrderEntity()));
        }

        // DİKKAT: Composition (Order(N)- Product(M))

        return orderDto;
    }

    // 2- OrderDto'u OrderEntity'e  çevir
    public static OrderEntity OrderDtoToOrderEntity(OrderDto orderDto) {
        // Instance(OrderEntity)
        OrderEntity orderEntity = new OrderEntity();

        // Field
        orderEntity.setId(orderDto.getId());
        orderEntity.setName(orderDto.getName());
        orderEntity.setPrice(orderDto.getPrice());
        orderEntity.setNotes(orderDto.getNotes());

        // DİKKAT: Composition (Order(N)- Customer(1))
        if(orderDto.getCompositionCustomerDto()!=null) {
            orderEntity.setCustomerOrderEntity(CustomerMapper.CustomerDtoToCustomerEntity(orderDto.getCompositionCustomerDto()));
        }

        // DİKKAT: Composition (Order(N)- Product(M))
        return orderEntity;
    }
}

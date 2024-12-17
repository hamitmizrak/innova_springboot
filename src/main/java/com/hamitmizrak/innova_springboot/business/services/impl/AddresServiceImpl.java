package com.hamitmizrak.innova_springboot.business.services.impl;

import com.hamitmizrak.innova_springboot.bean.ModelMapperBean;
import com.hamitmizrak.innova_springboot.business.dto.AddressDto;
import com.hamitmizrak.innova_springboot.business.services.interfaces.IAddressService;
import com.hamitmizrak.innova_springboot.data.entity.AddressEntity;
import com.hamitmizrak.innova_springboot.data.repository.IAddressRepository;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

// LOMBOK
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@RequiredArgsConstructor

//Service:  Asıl İş Yükünü yapan bean
@Service
public class AddresServiceImpl implements IAddressService<AddressDto, AddressEntity> {

    // INJECTION
    private final IAddressRepository iAddressRepository;
    private final ModelMapperBean modelMapperBean;


    // Model Mapper
    @Override
    public AddressDto entityToDto(AddressEntity addressEntity) {
        return null;
    }

    @Override
    public AddressEntity dtoToEntity(AddressDto addressDto) {
        return null;
    }

    ////////////////////////////////////////////////////////////////
    // CRUD
    @Override
    public AddressDto objectServiceCreate(AddressDto addressDto) {
        return null;
    }

    @Override
    public List<AddressDto> objectServiceList() {
        return List.of();
    }

    @Override
    public AddressDto objectServiceFindById(Long id) {
        return null;
    }

    @Override
    public AddressDto objectServiceUpdate(Long id, AddressDto addressDto) {
        return null;
    }

    @Override
    public AddressDto objectServiceDelete(Long id) {
        return null;
    }

    ////////////////////////////////////////////////////////////////
    // PAGE & SORT
    @Override
    public Page<AddressDto> objectServicePagination(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public List<AddressDto> objectServiceListSortedBy(String sortedBy) {
        return List.of();
    }

    @Override
    public List<AddressDto> objectServiceListSortedByAsc() {
        return List.of();
    }

    @Override
    public List<AddressDto> objectServiceListSortedByDesc() {
        return List.of();
    }
}

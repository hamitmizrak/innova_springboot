package com.hamitmizrak.innova_springboot.data.mapper;

import com.hamitmizrak.innova_springboot.business.dto.AddressDto;
import com.hamitmizrak.innova_springboot.data.embedded.AddresseEntityDetailsEmbedable;
import com.hamitmizrak.innova_springboot.data.entity.AddressEntity;

public class AddressMapper {

    // 1- AddressEntity'i AddressDto'a çevir
    public static AddressDto AddressEntityToAddressDto(AddressEntity addressEntity){
        // Instance (AddressDto)
        AddressDto addressDto= new AddressDto();

        //ID,SYSTEM DATE
        addressDto.setId(addressEntity.getId());
        addressDto.setSystemCreatedDate(addressEntity.getSystemCreatedDate());

        // Embedable içi doluysa
        if(addressEntity.getAddressDetailsEmbedable() != null){
            AddresseEntityDetailsEmbedable addressDetailsEmbedable = addressEntity.getAddressDetailsEmbedable();
            addressDto.setDoorNumber(addressDetailsEmbedable.getDoorNumber());
            addressDto.setStreet(addressDetailsEmbedable.getStreet());
            addressDto.setAvenue(addressDetailsEmbedable.getAvenue());
            addressDto.setCity(addressDetailsEmbedable.getCity());
            addressDto.setState(addressDetailsEmbedable.getState());
            addressDto.setZipCode(addressDetailsEmbedable.getZipCode());
            addressDto.setAddressQrCode(addressDetailsEmbedable.getAddressQrCode());
            addressDto.setDescription(addressDetailsEmbedable.getDescription());
            //addressDto.setCreatedBy();
        }
        return addressDto;
    }

    // 2- AddressDto'u AddressEntity'e  çevir
    public static AddressEntity AddressDtoToAddressEntity(AddressDto  addressDto){
        // Instance (AddressEntity)
        AddressEntity addressEntity= new AddressEntity();

        //ID,SYSTEM DATE
        addressEntity.setId(addressDto.getId());
        addressEntity.setSystemCreatedDate(addressDto.getSystemCreatedDate());

        // Embeddable
        AddresseEntityDetailsEmbedable addressDetailsEmbedable = new AddresseEntityDetailsEmbedable();
        addressDetailsEmbedable.setDoorNumber(addressDto.getDoorNumber());
        addressDetailsEmbedable.setStreet(addressDto.getStreet());
        addressDetailsEmbedable.setAvenue(addressDto.getAvenue());
        addressDetailsEmbedable.setCity(addressDto.getCity());
        addressDetailsEmbedable.setState(addressDto.getState());
        addressDetailsEmbedable.setZipCode(addressDto.getZipCode());
        addressDetailsEmbedable.setAddressQrCode(addressDto.getAddressQrCode());
        // AddressEntity içine "Embeddable Set" ekledik
        addressEntity.setAddressDetailsEmbedable(addressDetailsEmbedable);

        return addressEntity;
    }
}

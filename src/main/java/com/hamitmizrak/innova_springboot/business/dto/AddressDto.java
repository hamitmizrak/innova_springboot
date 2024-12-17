package com.hamitmizrak.innova_springboot.business.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

//
public class AddressDto extends BaseDto {

    // DOOR NUMBER
    @NotEmpty(message = "{address.door_number.validation.constraints.NotNull.message}")
    private String doorNumber;

    // ZIP CODE
    @NotEmpty(message = "{address.zip_code.validation.constraints.NotNull.message}")
    private String zipCode;

    // STREET
    @NotEmpty(message = "{address.street.validation.constraints.NotNull.message}")
    private String street;

    // AVENUE
    @NotEmpty(message = "{address.avenue.validation.constraints.NotNull.message}")
    private String avenue;

    // CITY
    @NotEmpty(message = "{address.city.validation.constraints.NotNull.message}")
    private String city;

    // STATE
    @NotEmpty(message = "{address.state.validation.constraints.NotNull.message}")
    private String state;

    // DESCRIPTION
    @NotEmpty(message = "{address.description.validation.constraints.NotNull.message}")
    @Size(min = 5, message = "{address.description.least.validation.constraints.NotNull.message}")
    private String description;

    // ADDRESS QR CODE
    @NotEmpty(message = "{address.qr_code.unique.validation.constraints.NotNull.message}")
    private String addressQrCode;

}



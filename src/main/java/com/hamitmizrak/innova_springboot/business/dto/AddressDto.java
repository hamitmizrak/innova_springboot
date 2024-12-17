package com.hamitmizrak.innova_springboot.business.dto;

import jakarta.validation.constraints.NotEmpty;

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
    private String description;

    // ADDRESS QR CODE
    @NotEmpty(message = "{address.qr_code.unique.validation.constraints.NotNull.message}")
    private String addressQrCode;

}



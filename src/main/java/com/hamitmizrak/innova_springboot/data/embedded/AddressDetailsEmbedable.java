package com.hamitmizrak.innova_springboot.data.embedded;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

// Gömülü Sınıf: Entity Classlarında daha sade ve karmaşıklıktan uzak tutmak için
@Embeddable
public class AddressDetailsEmbedable {

    // DOOR NUMBER
    @Column(name = "door_number",unique = true,nullable = false,updatable = true,insertable = true,length = 255,columnDefinition = "")
    private String doorNumber;

    // ZIP CODE
    @Column(name = "zip_code")
    private String zipCode;

    // STREET
    private String street;

    // AVENUE
    private String avenue;

    // CITY
    private String city;

    // STATE
    private String state;

    // DESCRIPTION
    @Lob
    private String description;

    // ADDRESS QR CODE
    @Column(name = "address_qr_code")
    private String addressQrCode;
}

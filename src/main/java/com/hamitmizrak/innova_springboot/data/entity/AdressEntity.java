package com.hamitmizrak.innova_springboot.data.entity;

import com.hamitmizrak.innova_springboot.data.embedded.AddressDetailsEmbedable;
import jakarta.persistence.*;

// Entity
@Entity(name = "Addreses")
@Table(name = "addreses")
// Address
public class AdressEntity extends BaseEntity{

    // FIELD

    // Embedded
    @Embedded
    private AddressDetailsEmbedable addressDetailsEmbedable;

    // RELATION
}

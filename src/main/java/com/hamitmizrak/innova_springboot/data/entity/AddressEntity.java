package com.hamitmizrak.innova_springboot.data.entity;

import com.hamitmizrak.innova_springboot.data.embedded.AddressDetailsEmbedable;
import jakarta.persistence.*;
import lombok.*;

// LOMBOK
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

// Entity
@Entity(name = "Addreses")
@Table(name = "addreses")
// Address
public class AddressEntity extends BaseEntity{

    // FIELD

    // Embedded
    @Embedded
    private AddressDetailsEmbedable addressDetailsEmbedable;

    // RELATION
}

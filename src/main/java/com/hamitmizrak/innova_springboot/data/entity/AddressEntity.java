package com.hamitmizrak.innova_springboot.data.entity;

import com.hamitmizrak.innova_springboot.data.embedded.AddresseEntityDetailsEmbedable;
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
    // Dikkat: Sorting için burada yazan: addresseEntityDetailsEmbedable göre işlem yapılır
    @Embedded
    private AddresseEntityDetailsEmbedable addresseEntityDetailsEmbedable;

    // RELATION
}

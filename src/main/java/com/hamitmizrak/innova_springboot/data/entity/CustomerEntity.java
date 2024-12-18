package com.hamitmizrak.innova_springboot.data.entity;

import com.hamitmizrak.innova_springboot.business.dto.AddressDto;
import com.hamitmizrak.innova_springboot.data.embedded.AddresseEntityDetailsEmbedable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter
//@ToString
//@EqualsAndHashCode
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

// Entity
@Entity(name = "Customers")
@Table(name = "customers")

// Customer(1)- Address(1)
// Customer(1) - Order(N)
public class CustomerEntity extends BaseEntity {

    // FIELD
    // FIRSTNAME
    private String fistname;

    // LASTNAME
    private String lastname;

    // NOTES
    private String notes;

    ////////////////////////////////////////////////////////////////////////////
    // RELATION
    // COMPOSITION

    // Customer(1) - Adress(1) NOT: Adres bilgilerine Customer üzerinden erişim sağlayacağım.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", unique = true)
    private AddressEntity addressCustomerEntity;

    // Customer(1) - Order(N)
} //end CustomerEntity

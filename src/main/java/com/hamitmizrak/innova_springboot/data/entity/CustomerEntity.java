package com.hamitmizrak.innova_springboot.data.entity;

import jakarta.persistence.*;
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


// @NamedQuery anotasyonu ile tanımlanan statik sorgulardır.
@NamedQueries({
        @NamedQuery(name = "CustomerEntity.findAllCustomers", query = "SELECT c FROM Customers c"),
        @NamedQuery(name = "CustomerEntity.findByLastname", query = "SELECT c FROM Customers c WHERE c.lastname = :lastname"),
        @NamedQuery(name = "CustomerEntity.findWithNotes", query = "SELECT c FROM Customers c WHERE c.notes LIKE :notes")
})

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
    // Bundan dolayı @JoinColumn Customer içinde yazıyoruz
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", unique = true)
    private AddressEntity addressCustomerEntity;

    // Customer(1) - Order(N)
} //end CustomerEntity

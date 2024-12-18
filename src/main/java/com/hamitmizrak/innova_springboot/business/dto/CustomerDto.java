package com.hamitmizrak.innova_springboot.business.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

// Customer(1)- Address(1)
// Customer(1) - Order(N)
public class CustomerDto extends BaseDto {

    // FIRSTNAME
    @NotEmpty(message = "{customer.firstname.validation.constraints.NotNull.message}")
    private String fistname;

    // LASTNAME
    @NotEmpty(message = "{customer.lastname.validation.constraints.NotNull.message}")
    private String lastname;

    // NOTES
    @NotEmpty(message = "{customer.notes.validation.constraints.NotNull.message}")
    private String notes;

    ////////////////////////////////////////////////////////////////////////////
    // RELATION
    // COMPOSITION
    // Customer(1) - Adress(1) NOT: Adres bilgilerine Customer üzerinden erişim sağlayacağım.
    private AddressDto compositionAddressDto;

    // Customer(1) - Order(N)

} // end CustomerDto




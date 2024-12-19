package com.hamitmizrak.innova_springboot.business.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

//  Product(M)- Order(N)
public class ProductDto extends BaseDto {

    // NAME
    @NotEmpty(message = "{product.name.validation.constraints.NotNull.message}")
    private String name;

    // TRADE
    @NotEmpty(message = "{product.price.validation.constraints.NotNull.message}")
    private String trade;

    // NOTES
    @NotEmpty(message = "{product.notes.validation.constraints.NotNull.message}")
    private String notes;

    ////////////////////////////////////////////////////////////////////////////
    // RELATION
    // COMPOSITION

} // end CustomerDto

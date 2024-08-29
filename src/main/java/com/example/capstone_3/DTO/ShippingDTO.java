package com.example.capstone_3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingDTO {
    @NotNull
    private Integer orderId;
    @NotEmpty
    private String shipperName;
    @NotNull
    private double price;
    @NotEmpty
    @Pattern(regexp = "^(Shipped|Out for Delivery| Delivered | Returned )$")
    private String status;
}

package com.example.capstone_3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    private Integer customerId;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotNull
    @PositiveOrZero
    private Integer buildingNumber;
}

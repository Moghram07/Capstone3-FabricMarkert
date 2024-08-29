package com.example.capstone_3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Tailor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Name cannot be empty.")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @Email(message = "Email should be valid.")
    @NotEmpty(message = "Email is required.")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty(message = "Phone number is required.")
    @Pattern(regexp = "^(\\+9665|05|5)([0-9]{8})$", message = "Invalid phone number format.")
    @Column(columnDefinition = "varchar(15) not null")
    private String phone;

    @NotNull(message = "Price per meter cannot be null.")
    @Min(value = 0, message = "Price per meter must be a positive value.")
    @Column(columnDefinition = "decimal(10, 2) not null")
    private double priceByMeter;

    @NotEmpty
    @Column(columnDefinition = "varchar(30) not null")
    private String city;
    @NotEmpty
    @Column(columnDefinition = "varchar(30) not null")
    private String street;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tailors")
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "tailor")
    private List<Order> orders;

}
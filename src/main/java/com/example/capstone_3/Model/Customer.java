package com.example.capstone_3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name must not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;
    @NotNull(message = "age")
    @Min(value = 16 , message = "Customer Age must be more than 16")
    @Column(columnDefinition = "int not null")
    private int age;
    @NotEmpty(message = "gender must not be empty")
    @Pattern(regexp = "^(Female|Male)$")
    @Column(columnDefinition = "varchar(25) not null")
    private String gender;
    @NotEmpty(message = "email must not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    @Email
    private String email;
    @NotEmpty(message = "phone must not be empty")
    @Column(columnDefinition = "varchar(11) not null")
    @Size(min = 10, max = 10)
    private String phone;
    @NotNull(message = "height must not be empty")
    @Column(columnDefinition = "double not null")
    private double height;
    @NotNull(message = "weight must not be empty")
    @Column(columnDefinition = "double not null")
    private double weight;
    @Column(columnDefinition = "date")
    private LocalDate registrationDate = LocalDate.now();
    @OneToOne(cascade = CascadeType.ALL , mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private Address address;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "custom")
    private Set<Order> orders;
}

package com.example.capstone_3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty.")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be a positive value.")
    @Column(columnDefinition = "decimal not null")
    private double price;


    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^(\\+9665|05|5)([0-9]{8})$", message = "Invalid phone number format")
    @Column(columnDefinition = "varchar(15) not null")
    private String phone;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")

    @Column(columnDefinition = "varchar(40) not null unique ")
    @Check(constraints = "email LIKE '%@%'")
    private String email;
    @NotEmpty
    @Column(columnDefinition = "varchar(30) not null")
    private String city;
    @NotEmpty
    @Column(columnDefinition = "varchar(30) not null")
    private String street;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "designer")
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "designer")
    private List<Order> orders;
}

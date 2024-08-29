package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String city;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String street;
    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "int not null")
    private Integer buildingNumber;
    @OneToOne
    @MapsId
    @JsonIgnore
    private Customer customer;
}

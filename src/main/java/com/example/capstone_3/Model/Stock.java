package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//All made by Omar Alshehri
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    //All made by Omar Alshehri
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "fabric_id")
    @JsonIgnore
    private Fabric fabric;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    @JsonIgnore
    private Merchant merchant;

    @Positive
    private int quantity;
}

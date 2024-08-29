package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
//All made by Omar Alshehri
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fabric {
    //All made by Omar Alshehri
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String color;

    @NotEmpty
    private String type;

    @NotEmpty
    private String pattern;

    @NotEmpty
    private String description;

    private double length;

    private double size;

    @Positive
    private double price;

    @OneToOne(mappedBy = "fabric", cascade = CascadeType.ALL)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    @JsonIgnore
    private Merchant merchant;

    @OneToMany(mappedBy = "fabric")
    private List<Order> orders;

}

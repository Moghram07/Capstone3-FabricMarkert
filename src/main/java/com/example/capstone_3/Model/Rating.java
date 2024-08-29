package com.example.capstone_3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Value cannot be null.")
    @Min(value = 0, message = "Value must be a positive number.")
    @Column(columnDefinition = "decimal not null")
    private double value;

    @NotEmpty(message = "Review cannot be empty.")
    @Column(columnDefinition = "varchar(255) not null")
    private String review;

    @ManyToOne
    @JsonIgnore
    private Designer designer;

    @ManyToOne
    @JsonIgnore
    private Tailor tailors;

    @ManyToOne
    @JsonIgnore
    private Merchant merchant;
}

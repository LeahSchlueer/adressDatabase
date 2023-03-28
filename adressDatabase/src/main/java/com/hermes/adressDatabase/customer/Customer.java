package com.hermes.adressDatabase.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    @Id
    @SequenceGenerator(name = "IdGeneratorCustomer", sequenceName = "customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdGeneratorCustomer")
    @NotNull
    private int id;

    @NotBlank(message = "Bitte Namen eingeben.")
    private String name;

    @NotBlank(message = "Bitte Voramen eingeben.")
    private String surName;

    @NotBlank(message = "Dies ist ein Pflichtfeld.")
    private String street;

    @NotNull(message = "Dies ist ein Pflichtfeld.")
    private Integer houseNumber;

    @Min(5)
    @Max(6)
    private Integer postCode;

    @NotBlank(message = "Dies ist ein Pflichtfeld")
    private String city;

    @NotBlank(message = "Dies ist ein Pflichtfeld")
    private String land;

    @Min(11)
    @Max(15)
    private String telephone;

    @Email
    private String eMail;

    private Long loginId;

}

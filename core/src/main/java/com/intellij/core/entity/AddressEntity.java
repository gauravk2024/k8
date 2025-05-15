package com.intellij.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.GenericArrayType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Address")
public class AddressEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private String zipcode;

}

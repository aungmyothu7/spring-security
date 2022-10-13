package com.example.springsecuritymaster.ds;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2,max = 5,message = "Code length must be between 2 and 5")
    @Pattern(regexp = "[A-Za-z]*",message = "Code contains illegal characters")
    private String code;

    @NotBlank(message = "FirstName cannot be empty.")
    @Pattern(regexp = "[A-Za-z-]*",message = "FirstName contains illegal characters.")
    private String firstName;

    @NotBlank(message = "LastName cannot be empty.")
    @Pattern(regexp = "[A-Za-z-]*",message = "LastName contains illegal characters.")
    private String lastName;

    @NotBlank(message = "Address cannot be empty.")
    @Pattern(regexp = "[\\w .\\-/,]*",message = "Address contains illegal characters.")
    private String address;

    public Customer() {
    }

    public Customer(String code, String firstName, String lastName, String address) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}

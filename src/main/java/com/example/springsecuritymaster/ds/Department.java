package com.example.springsecuritymaster.ds;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Code connot be empty")
    @Pattern(regexp = "[A-Za-z ]*",message = "Code cannot contain illegal character.")
    private String code;

    @NotBlank(message = "Name canot be empty!")
    @Pattern(regexp = "[A-Za-z]*",message = "Name cannot contain illegal character.")
    private String name;

    @NotBlank(message = "Name cannot be empty!")
    @Pattern(regexp = "[A-Za-z]*",message = "Name cannot contain illegal character.")
    private String country;


    public Department() {
    }

    public Department(String code, String name, String country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }
}

package com.example.springsecuritymaster.security.annotation.customers;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.springsecuritymaster.security.SecurityRoles.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Secured(ROLES_PREFIX+CUSTOMERS_ADMIN)
public @interface IsCustomersAdmin {
}

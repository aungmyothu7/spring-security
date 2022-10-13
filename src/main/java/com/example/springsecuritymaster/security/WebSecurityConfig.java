package com.example.springsecuritymaster.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import static com.example.springsecuritymaster.security.SecurityRoles.*;


@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private RoleHierarchy roleHierarchy;


    @Bean
    public UserDetailsService userDetailsService(){

        var uds =new InMemoryUserDetailsManager();

        var john= User.withUsername("john")
                .password("john")
                .roles(SUPER_ADMIN)
                .build();

        var emma=User.withUsername("emma")
                .password("emma")
                .roles(EMPLOYEES_ADMIN)
                .build();

        var william = User.withUsername("william")
                .password("william")
                .roles(DEPARTMENTS_CREATE,DEPARTMENTS_READ,DEPARTMENTS_PAGE_VIEW)
                .build();

        var lucas = User.withUsername("lucas")
                .password("lucas")
                .roles(CUSTOMERS_READ,CUSTOMERS_PAGE_VIEW)
                .build();

        var tom=User.withUsername("tom")
                .password("tom")
                .roles()
                .build();

        uds.createUser(john);
        uds.createUser(emma);
        uds.createUser(william);
        uds.createUser(lucas);
        uds.createUser(tom);

        return uds;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        http.authorizeRequests()
                .expressionHandler(expressionHandler())
                .mvcMatchers("/","/home").permitAll()
                .mvcMatchers("/bootstrap/**").permitAll()
                .mvcMatchers("/customers").hasRole(CUSTOMERS_PAGE_VIEW)
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

        http.csrf().disable();
        return http.build();
    };

    private DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
}

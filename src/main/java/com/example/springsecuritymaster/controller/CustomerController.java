package com.example.springsecuritymaster.controller;


import com.example.springsecuritymaster.dao.CustomerDao;
import com.example.springsecuritymaster.ds.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import static com.example.springsecuritymaster.security.SecurityRoles.*;

@Controller
public class CustomerController {
    @Autowired
    private CustomerDao customerDao;

    @Secured(ROLES_PREFIX + CUSTOMERS_CREATE)
    @GetMapping("/customers")
    public ModelAndView findAllCustomer(){
        return new ModelAndView(
                "customers",
                "customers",
                customerDao.findAll()
        );
    }
    @RolesAllowed(ROLES_PREFIX+CUSTOMERS_CREATE)
    @GetMapping("/create-customer")
    public String createCustomer(Model model){
        model.addAttribute("customer",new Customers());
        return "customer-form";
    }
    @RolesAllowed(ROLES_PREFIX+CUSTOMERS_CREATE)
    @PostMapping("/create-customer")
    public String processCustomer(@Valid Customers customer, BindingResult result){
        if(result.hasErrors()){
            return "customer-form";
        }
        customerDao.save(customer);
        return "redirect:/customers";
    }
    @RolesAllowed(ROLES_PREFIX+CUSTOMERS_DELETE)
    @GetMapping("/customers/delete")
    public String deleteCustomer(@RequestParam("id")int id){
        customerDao.deleteById(id);
        return "redirect:/customers";
    }
}

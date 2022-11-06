package com.example.springsecuritymaster.controller;


import com.example.springsecuritymaster.dao.EmployeeDao;
import com.example.springsecuritymaster.ds.Employee;
import com.example.springsecuritymaster.security.annotation.employees.IsEmployeesCreate;
import com.example.springsecuritymaster.security.annotation.employees.IsEmployeesDelete;
import com.example.springsecuritymaster.security.annotation.employees.IsEmployeesRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EmployeesController {
    @Autowired
    private EmployeeDao employeeDao;

    //@PreAuthorize("hasRole('ROLE_EMPLOYEES_READ')")
    @GetMapping("/employees")
    public ModelAndView listEmployees(){
        return new ModelAndView("employees"
                ,"employees"
                ,employeeDao.findAll());
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEES_CREATE')")
    @GetMapping("/create-employee")
    public String ceateEmployee(Model model){
        model.addAttribute("employee",new Employee());
        return "employee-form";
    }


    @PreAuthorize("'Test'.equals(#employee.firstName)|| hasRole('ROLE_EMPLOYEES_CREATE')")
    @PostMapping("/create-employee")
    public String saveEmployee(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            return "employee-form";
        }
        employeeDao.save(employee);
        return "redirect:/employees";
    }

    @PreAuthorize("#id >=5 || hasRole('ROLE_EMPLOYEES_DELETE')")
    @GetMapping("/employees/delete")
    public String deleteEmployee(@RequestParam("id")int id){
        employeeDao.deleteById(id);
        return "redirect:/employees";
    }
}

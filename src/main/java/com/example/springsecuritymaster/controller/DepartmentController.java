package com.example.springsecuritymaster.controller;

import com.example.springsecuritymaster.dao.DepartmentDao;
import com.example.springsecuritymaster.ds.Department;
import com.example.springsecuritymaster.security.annotation.departments.IsDepartmentCreate;
import com.example.springsecuritymaster.security.annotation.departments.IsDepartmentDelete;
import com.example.springsecuritymaster.security.annotation.departments.IsDepartmentRead;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentController {
    @Autowired
    private DepartmentDao departmentDao;

    @RolesAllowed(ROLES_PREFIX + DEPARTMENTS_READ)
    @GetMapping("/departments")
    public ModelAndView findAllDepartment(){
        return new ModelAndView(
                "departments",
                "departments",
                departmentDao.findAll()
        );
    }

    @RolesAllowed(ROLES_PREFIX+DEPARTMENTS_CREATE)
    @GetMapping("/create-department")
    public String createDepartment(Model model){
        model.addAttribute("department",new Department());
        return "department-form";
    }

    @RolesAllowed(ROLES_PREFIX+DEPARTMENTS_CREATE)
    @PostMapping("/create-department")
    public String processDepartment(@Valid Department department, BindingResult result){
        if(result.hasErrors()){
            return "department-form";
        }
        departmentDao.save(department);
        return "redirect:/departments";
    }

    @RolesAllowed(ROLES_PREFIX+DEPARTMENTS_DELETE)
    @GetMapping("/departments/delete")
    public String deleteDepartment(@RequestParam("id")int id){
        departmentDao.deleteById(id);
        return "redirect:/departments";
    }
}

package com.example.springsecuritymaster;

import com.example.springsecuritymaster.dao.CustomerDao;
import com.example.springsecuritymaster.dao.EmployeeDao;
import com.example.springsecuritymaster.ds.Customer;
import com.example.springsecuritymaster.ds.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringSecurityMasterApplication {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private EmployeeDao employeeDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityMasterApplication.class, args);
    }


    @Bean @Transactional @Profile("test")
    public ApplicationRunner runner2(){
        return runner ->{
            Employee e1=new Employee("John","Doe","55-55-555","Dream Land","A123");
            Employee e2=new Employee("Thomas","Hardy","55-55-556","Love Lane","B123");
            Employee e3=new Employee("Richard","Chan","55-55-557","Peeky Bridge Road","C123");

            employeeDao.save(e1);
            employeeDao.save(e2);
            employeeDao.save(e3);
        };
    }

    @Bean @Transactional @Profile("dev")
    public ApplicationRunner runner(){
        return runner ->{
           Customer c1=new Customer("AB","John","Doe",
                   "Saint Mountain Belly Bridge");
           Customer c2=new Customer("BC","Barkaley","Clara",
                   "Dream Land");
           Customer c3=new Customer("AA","Ashaley","Assans",
                   "Dark Buffalo Bridge Road");
           Customer c4=new Customer("TH","Thomas","Hardy","" +
                   "Green Grass Wexssex");
           Customer c5=new Customer("TM","Thomas","Mann",
                   "Park Avenue");

           customerDao.save(c1);
           customerDao.save(c2);
           customerDao.save(c3);
           customerDao.save(c4);
           customerDao.save(c5);

        };
    }
}

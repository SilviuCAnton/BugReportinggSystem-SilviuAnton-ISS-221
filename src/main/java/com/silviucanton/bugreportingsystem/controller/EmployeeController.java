package com.silviucanton.bugreportingsystem.controller;

import com.silviucanton.bugreportingsystem.domain.*;
import com.silviucanton.bugreportingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bugreporting/employees")
public class EmployeeController {

    private final UserService userService;

    @Autowired
    public EmployeeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String userList(Model theModel) {

        List<Employee> employees = userService.getAllEmployees();
        theModel.addAttribute("employees", employees);

        return "user-list";
    }

    @GetMapping("/showFormForUserRegistration")
    public String showRegistrationForm(Model theModel) {
        Employee theEmployee = new Employee();
        User employeeAccount = new User();
        theModel.addAttribute("employee", theEmployee);
        theModel.addAttribute("account", employeeAccount);
        return "register-user";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute Employee employee, @ModelAttribute User account) {

        String type = employee.getEmployeeType();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword("{bcrypt}" + hashedPassword);
        Authority authority = new Authority();
        switch (type) {
            case "QATester":
                authority.setUsername(account.getUsername());
                authority.setAuthority("ROLE_QATESTER");
                account.setAuthority(authority);
                account.setEnabled(1);
                employee.setAccount(account);
                userService.registerTester(employee);
                break;
            case "Developer":
                authority.setUsername(account.getUsername());
                authority.setAuthority("ROLE_DEVELOPER");
                account.setAuthority(authority);
                employee.setAccount(account);
                account.setEnabled(1);
                userService.registerDeveloper(employee);
                break;
            case "Admin":
                authority.setUsername(account.getUsername());
                authority.setAuthority("ROLE_ADMIN");
                account.setAuthority(authority);
                employee.setAccount(account);
                account.setEnabled(1);
                userService.registerAdmin(employee);
                break;
        }

        return "redirect:/bugreporting/employees/list";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int theId) {

        userService.deleteEmployee(theId);

        return "redirect:/bugreporting/employees/list";
    }

    @GetMapping("/updateEmployee")
    public String updateEmployee(@RequestParam("employeeId") int theId, Model theModel) {

        userService.findEmployeeById(theId).ifPresent(theEmployee -> {
                    theModel.addAttribute("employee", theEmployee);
                    theModel.addAttribute("account", theEmployee.getAccount());
        });

        return "register-user";
    }

}

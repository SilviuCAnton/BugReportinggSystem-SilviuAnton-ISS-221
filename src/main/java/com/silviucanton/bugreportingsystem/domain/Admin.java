package com.silviucanton.bugreportingsystem.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMIN")
public class Admin extends Employee {

    public Admin(String username, String firstName, String lastName, User account) {
        super(username, firstName, lastName, account);
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{}";
    }

    public Admin(Employee employee) {
        super();
        this.setAccount(employee.getAccount());
        this.setFirstName(employee.getFirstName());
        this.setId(employee.getId());
        this.setLastName(employee.getLastName());
        this.setEmployeeType("Admin");
    }
}

package com.silviucanton.bugreportingsystem.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue(value = "DEVELOPER")
public class Developer extends Employee {
    public Developer(String username, String firstName, String lastName, User account) {
        super(username, firstName, lastName, account);
    }

    public Developer() {
    }

    @Override
    public String toString() {
        return "Developer{}";
    }

    public Developer(Employee employee) {
        super();
        this.setAccount(employee.getAccount());
        this.setFirstName(employee.getFirstName());
        this.setId(employee.getId());
        this.setLastName(employee.getLastName());
        this.setEmployeeType("Developer");
    }
}

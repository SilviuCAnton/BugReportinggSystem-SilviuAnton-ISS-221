package com.silviucanton.bugreportingsystem.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue(value = "QATESTER")
public class QATester extends Employee{
    public QATester(String username, String firstName, String lastName, User account) {
        super(username, firstName, lastName, account);
    }

    public QATester() {
    }

    @Override
    public String toString() {
        return "QATester{}";
    }

    public QATester(Employee employee) {
        super();
        this.setAccount(employee.getAccount());
        this.setFirstName(employee.getFirstName());
        this.setId(employee.getId());
        this.setLastName(employee.getLastName());
        this.setEmployeeType("QATester");
    }
}

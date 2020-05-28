package com.silviucanton.bugreportingsystem.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String employeeType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User account;

    public Employee(Integer id, String firstName, String lastName, String employeeType, User account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeType = employeeType;
        this.account = account;
    }

    public Employee(String firstName, String lastName, String employeeType, User account) {
        this(0, firstName, lastName, employeeType, account);
    }

    public Employee() {
        this.employeeType = this.getClass().getName();
        this.id = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(account, employee.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", account=" + account +
                '}';
    }
}

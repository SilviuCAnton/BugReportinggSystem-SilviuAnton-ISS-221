package com.silviucanton.bugreportingsystem.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="testers")
public class QATester {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User account;

    @OneToMany(mappedBy = "reporter")
    private List<Bug> reportedBugs;

    public QATester(String username, String firstName, String lastName, User account) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public QATester() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QATester qaTester = (QATester) o;
        return Objects.equals(username, qaTester.username) &&
                Objects.equals(firstName, qaTester.firstName) &&
                Objects.equals(lastName, qaTester.lastName) &&
                Objects.equals(account, qaTester.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, account);
    }

    @Override
    public String toString() {
        return "QATester{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", account=" + account +
                '}';
    }
}

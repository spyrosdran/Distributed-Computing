package com.spyros.springbank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class BankClient {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "balance")
    private Float balance;

    @Column(name = "logged_in")
    private Integer loggedIn;

    public BankClient() {}

    public BankClient(String firstName, String lastName, String username, Float balance, Integer loggedIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.balance = balance;
        this.loggedIn = loggedIn;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Integer loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                ", loggedIn=" + loggedIn +
                '}';
    }
}

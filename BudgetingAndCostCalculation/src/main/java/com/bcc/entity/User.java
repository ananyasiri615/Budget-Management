package com.bcc.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String username;
    private String email;
    private String password;
    private double totalFixedIncome;
    private double totalExpenses;
    private double totalBudget;
    private double totalEmi;
    private double totalInvestment;
    private double NetSavings;

    public User(int userId, String username, String email, String password, double totalFixedIncome, double totalExpenses, double totalBudget, double totalEmi, double totalInvestment, double netSavings) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.totalFixedIncome = totalFixedIncome;
        this.totalExpenses = totalExpenses;
        this.totalBudget = totalBudget;
        this.totalEmi = totalEmi;
        this.totalInvestment = totalInvestment;
        NetSavings = netSavings;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getTotalFixedIncome() {
        return totalFixedIncome;
    }

    public void setTotalFixedIncome(double totalFixedIncome) {
        this.totalFixedIncome = totalFixedIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public double getNetSavings() {
        return NetSavings;
    }

    public void setNetSavings(double netSavings) {
        NetSavings = netSavings;
    }

    public double getTotalEmi() {
        return totalEmi;
    }

    public void setTotalEmi(double totalEmi) {
        this.totalEmi = totalEmi;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", totalFixedIncome=" + totalFixedIncome +
                ", totalExpenses=" + totalExpenses +
                ", totalBudget=" + totalBudget +
                ", totalEmi=" + totalEmi +
                ", totalInvestment=" + totalInvestment +
                ", NetSavings=" + NetSavings +
                '}';
    }
}

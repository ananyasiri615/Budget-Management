package com.bcc.entity;

import javax.persistence.*;

@Entity
@Table(name = "loan_emi")
public class LoanEMI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanId;
    private double principal;
    private double annualInterestRate;
    private int numberOfMonths;
    private double emi;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LoanEMI(int loanId, double principal, double annualInterestRate, int numberOfMonths, double emi, User user) {
        this.loanId = loanId;
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.numberOfMonths = numberOfMonths;
        this.emi = emi;
        this.user = user;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoanEMI{" +
                "loanID=" + loanId +
                ", principal=" + principal +
                ", annualInterestRate=" + annualInterestRate +
                ", numberOfMonths=" + numberOfMonths +
                ", emi=" + emi +
                ", user=" + user +
                '}';
    }

    public LoanEMI() {
    }
}


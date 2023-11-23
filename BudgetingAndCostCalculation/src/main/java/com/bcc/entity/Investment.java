package com.bcc.entity;

import javax.persistence.*;
@Entity
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_id")
    private int investmentId;

    private double futureValue;
    private double initialInvestment;
    private double annualInterestRate;
    private int compoundingFrequency;
    private int investmentDuration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Investment(int investmentId, double futureValue, double initialInvestment, double annualInterestRate, int compoundingFrequency, int investmentDuration, User user) {
        this.investmentId = investmentId;
        this.futureValue = futureValue;
        this.initialInvestment = initialInvestment;
        this.annualInterestRate = annualInterestRate;
        this.compoundingFrequency = compoundingFrequency;
        this.investmentDuration = investmentDuration;
        this.user = user;
    }

    public Investment() {
    }

    public int getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(double initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getCompoundingFrequency() {
        return compoundingFrequency;
    }

    public void setCompoundingFrequency(int compoundingFrequency) {
        this.compoundingFrequency = compoundingFrequency;
    }

    public int getInvestmentDuration() {
        return investmentDuration;
    }

    public void setInvestmentDuration(int investmentDuration) {
        this.investmentDuration = investmentDuration;
    }

    public double getFutureValue() {
        return futureValue;
    }

    public void setFutureValue(double futureValue) {
        this.futureValue = futureValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Investment{" +
                "investmentId=" + investmentId +
                ", futureValue=" + futureValue +
                ", initialInvestment=" + initialInvestment +
                ", annualInterestRate=" + annualInterestRate +
                ", compoundingFrequency=" + compoundingFrequency +
                ", investmentDuration=" + investmentDuration +
                ", user=" + user +
                '}';
    }
}

package com.bcc.entity;

import javax.persistence.*;

@Entity
@Table(name = "savings")
public class Savings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "savings_id")
    private int savingsId;
    private double netSavings;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Savings(int savingsId, double netSavings, User user) {
        this.savingsId = savingsId;
        this.netSavings = netSavings;
        this.user = user;
    }

    public int getSavingsId() {
        return savingsId;
    }

    public void setSavingsId(int savingsId) {
        this.savingsId = savingsId;
    }

    public double getNetSavings() {
        return netSavings;
    }

    public void setNetSavings(double netSavings) {
        this.netSavings = netSavings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Savings{" +
                "savingsId=" + savingsId +
                ", netSavings=" + netSavings +
                ", user=" + user +
                '}';
    }

    public Savings() {
    }
}


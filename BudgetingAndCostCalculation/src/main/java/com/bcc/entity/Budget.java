package com.bcc.entity;

import javax.persistence.*;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private int budgetId;

    private double spendingPercentage;

    private double totalExpensesInCategory;

    private double budgetedAmount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Budget(int budgetId, double spendingPercentage, double totalExpensesInCategory, double budgetedAmount, Category category, User user) {
        this.budgetId = budgetId;
        this.spendingPercentage = spendingPercentage;
        this.totalExpensesInCategory = totalExpensesInCategory;
        this.budgetedAmount = budgetedAmount;
        this.category = category;
        this.user = user;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public double getSpendingPercentage() {
        return spendingPercentage;
    }

    public void setSpendingPercentage(double spendingPercentage) {
        this.spendingPercentage = spendingPercentage;
    }

    public double getTotalExpensesInCategory() {
        return totalExpensesInCategory;
    }

    public void setTotalExpensesInCategory(double totalExpensesInCategory) {
        this.totalExpensesInCategory = totalExpensesInCategory;
    }

    public double getBudgetedAmount() {
        return budgetedAmount;
    }

    public void setBudgetedAmount(double budgetedAmount) {
        this.budgetedAmount = budgetedAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId +
                ", spendingPercentage=" + spendingPercentage +
                ", totalExpensesInCategory=" + totalExpensesInCategory +
                ", budgetedAmount=" + budgetedAmount +
                ", category=" + category +
                ", user=" + user +
                '}';
    }

    public Budget() {
    }
}


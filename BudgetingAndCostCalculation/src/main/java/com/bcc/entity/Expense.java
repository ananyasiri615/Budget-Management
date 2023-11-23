package com.bcc.entity;

import javax.persistence.*;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private int expenseId;
    private double totalExpensesInCategory;
    private double expendingPercentage;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Expense(int expenseId, double totalExpensesInCategory, double expendingPercentage, Category category, User user) {
        this.expenseId = expenseId;
        this.totalExpensesInCategory = totalExpensesInCategory;
        this.expendingPercentage = expendingPercentage;
        this.category = category;
        this.user = user;
    }

    public Expense() {
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public double getTotalExpensesInCategory() {
        return totalExpensesInCategory;
    }

    public void setTotalExpensesInCategory(double totalExpensesInCategory) {
        this.totalExpensesInCategory = totalExpensesInCategory;
    }

    public double getExpendingPercentage() {
        return expendingPercentage;
    }

    public void setExpendingPercentage(double expendingPercentage) {
        this.expendingPercentage = expendingPercentage;
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
        return "Expense{" +
                "expenseId=" + expenseId +
                ", totalExpensesInCategory=" + totalExpensesInCategory +
                ", expendingPercentage=" + expendingPercentage +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}


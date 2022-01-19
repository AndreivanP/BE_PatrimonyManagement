package com.andreivan.rest.webservices.patrimonymanagement.asset;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "asset")
public class Asset {
    @Id
    private String id;
    private String name;
    private Date date;
    private double initial_value;
    private String company;
    private double interest_rate;
    private boolean is_active;
    private String username;
    private double current_value;
    private boolean is_variable_income;
    private Date expiryDate;

    protected Asset() {
    }

    public Asset(String name, Date date, double initial_value, String company, double interest_rate, boolean is_active,
                 String username, double current_value, boolean is_variable_income, Date expiryDate) {
        this.name = name;
        this.date = date;
        this.initial_value = initial_value;
        this.company = company;
        this.interest_rate = interest_rate;
        this.is_active = is_active;
        this.username = username;
        this.current_value = current_value;
        this.is_variable_income = is_variable_income;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public double getInitial_value() {
        return initial_value;
    }

    public String getCompany() {
        return company;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public String getUsername() {
        return username;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setInitial_value(double initial_value) {
        this.initial_value = initial_value;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(double current_value) {
        this.current_value = current_value;
    }

    public boolean isIs_variable_income() {
        return is_variable_income;
    }

    public void setIs_variable_income(boolean is_variable_income) {
        this.is_variable_income = is_variable_income;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", initial_value=" + initial_value +
                ", company='" + company + '\'' +
                ", interest_rate=" + interest_rate +
                ", is_active=" + is_active +
                ", username='" + username + '\'' +
                ", current_value=" + current_value +
                ", is_variable_income=" + is_variable_income +
                ", expiryDate=" + expiryDate +
                '}';
    }
}

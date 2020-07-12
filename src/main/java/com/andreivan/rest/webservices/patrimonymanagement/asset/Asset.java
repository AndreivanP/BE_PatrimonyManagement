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

    protected Asset() {
    }

    public Asset(String name, Date date, double initial_value, String company, double interest_rate, boolean is_active, String username) {
        this.name = name;
        this.date = date;
        this.initial_value = initial_value;
        this.company = company;
        this.interest_rate = interest_rate;
        this.is_active = is_active;
        this.username = username;
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

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", initialValue=" + initial_value +
                ", company='" + company + '\'' +
                ", interestRate=" + interest_rate +
                ", isActive=" + is_active +
                '}';
    }
}
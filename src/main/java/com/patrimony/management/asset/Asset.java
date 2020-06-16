package com.patrimony.management.asset;

import java.util.Date;

public class Asset {
    private String id;
    private String name;
    private Date date;
    private double initialValue;
    private String company;
    private double interestRate;
    private boolean isActive;

    public Asset(String name, Date date, double initialValue, String company, double interestRate, boolean isActive) {
        this.name = name;
        this.date = date;
        this.initialValue = initialValue;
        this.company = company;
        this.interestRate = interestRate;
        this.isActive = isActive;
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

    public double getInitialValue() {
        return initialValue;
    }

    public String getCompany() {
        return company;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", initialValue=" + initialValue +
                ", company='" + company + '\'' +
                ", interestRate=" + interestRate +
                ", isActive=" + isActive +
                '}';
    }
}

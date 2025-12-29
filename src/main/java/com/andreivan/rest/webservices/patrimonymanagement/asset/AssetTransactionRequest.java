package com.andreivan.rest.webservices.patrimonymanagement.asset;

import javax.validation.constraints.DecimalMin;

public class AssetTransactionRequest {

    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

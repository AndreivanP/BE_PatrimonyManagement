package com.andreivan.rest.webservices.patrimonymanagement.asset;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "asset_transaction")
public class AssetTransaction {
    @Id
    private String id;
    private String assetId;
    private String username;
    private double amount;
    private Date transactionDate;
    private AssetTransactionType type;

    public AssetTransaction() {
    }

    public AssetTransaction(String assetId, String username, double amount, Date transactionDate, AssetTransactionType type) {
        this.assetId = assetId;
        this.username = username;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public AssetTransactionType getType() {
        return type;
    }

    public void setType(AssetTransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AssetTransaction{" +
                "id='" + id + '\'' +
                ", assetId='" + assetId + '\'' +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", type=" + type +
                '}';
    }
}

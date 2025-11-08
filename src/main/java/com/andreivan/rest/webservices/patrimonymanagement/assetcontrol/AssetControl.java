package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

import com.andreivan.rest.webservices.patrimonymanagement.asset.AssetCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "asset_control")
public class AssetControl {
    @Id
    private String id;
    private Date controlDate;
    private double currentTotalValue;
    private String username;
    private AssetCategory category;

    public AssetControl() {
    }

    public AssetControl(Date controlDate, double currentTotalValue, String username) {
        this.controlDate = controlDate;
        this.currentTotalValue = currentTotalValue;
        this.username = username;
    }

    public AssetControl(Date controlDate, double currentTotalValue, String username, AssetCategory category) {
        this.controlDate = controlDate;
        this.currentTotalValue = currentTotalValue;
        this.username = username;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public Date getControlDate() {
        return controlDate;
    }

    public void setControlDate(Date controlDate) {
        this.controlDate = controlDate;
    }

    public double getCurrentTotalValue() {
        return currentTotalValue;
    }

    public void setCurrentTotalValue(double currentTotalValue) {
        this.currentTotalValue = currentTotalValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AssetCategory getCategory() {
        return category;
    }

    public void setCategory(AssetCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AssetControl{" +
                "id='" + id + '\'' +
                ", controlDate=" + controlDate +
                ", currentTotalValue=" + currentTotalValue +
                ", username='" + username + '\'' +
                ", category=" + category +
                '}';
    }
}

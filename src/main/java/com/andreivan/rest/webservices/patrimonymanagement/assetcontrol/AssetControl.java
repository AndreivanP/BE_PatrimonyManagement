package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

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

    public AssetControl() {
    }

    public AssetControl(Date controlDate, double currentTotalValue, String username) {
        this.controlDate = controlDate;
        this.currentTotalValue = currentTotalValue;
        this.username = username;
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

    @Override
    public String toString() {
        return "AssetControl{" +
                "id='" + id + '\'' +
                ", controlDate=" + controlDate +
                ", currentTotalValue=" + currentTotalValue +
                ", username='" + username + '\'' +
                '}';
    }
}

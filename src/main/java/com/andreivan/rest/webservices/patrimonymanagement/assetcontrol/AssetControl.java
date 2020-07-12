package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "asset_control")
public class AssetControl {
    @Id
    private String id;
    private String assetId;
    private Date controlDate;
    private double currentValue;

    protected AssetControl() {
    }

    public AssetControl(String assetId, Date controlDate, double currentValue) {
        this.assetId = assetId;
        this.controlDate = controlDate;
        this.currentValue = currentValue;
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

    public Date getControlDate() {
        return controlDate;
    }

    public void setControlDate(Date controlDate) {
        this.controlDate = controlDate;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    @Override
    public String toString() {
        return "AssetControl{" +
                "id='" + id + '\'' +
                ", assetId='" + assetId + '\'' +
                ", controlDate=" + controlDate +
                ", currentValue=" + currentValue +
                '}';
    }
}

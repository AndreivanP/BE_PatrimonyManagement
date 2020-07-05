package com.patrimony.management.assetcontrol;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "control_asset")
public class AssetControl {
    @Id
    private String id;
    private String asset_id;
    private Date control_date;
    private double current_value;

    protected AssetControl() {
    }

    public AssetControl(String asset_id, Date control_date, double current_value) {
        this.asset_id = asset_id;
        this.control_date = control_date;
        this.current_value = current_value;
    }

    public String getId() {
        return id;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public Date getControl_date() {
        return control_date;
    }

    public void setControl_date(Date control_date) {
        this.control_date = control_date;
    }

    public double getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(double current_value) {
        this.current_value = current_value;
    }

    @Override
    public String toString() {
        return "AssetControl{" +
                "id='" + id + '\'' +
                ", assetId='" + asset_id + '\'' +
                ", controlDate=" + control_date +
                ", currentValue=" + current_value +
                '}';
    }
}

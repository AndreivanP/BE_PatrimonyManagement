package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "asset_control")
public class AssetControl {
    @Id
    private String id;
    private Date control_date;
    private double current_total_value;
    private String username;

    public AssetControl() {
    }

    public AssetControl(Date control_date, double current_total_value, String username) {
        this.control_date = control_date;
        this.current_total_value = current_total_value;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public Date getControl_date() {
        return control_date;
    }

    public void setControl_date(Date control_date) {
        this.control_date = control_date;
    }

    public double getCurrent_total_value() {
        return current_total_value;
    }

    public void setCurrent_total_value(double current_total_value) {
        this.current_total_value = current_total_value;
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
                ", controlDate=" + control_date +
                ", currentTotalValue=" + current_total_value +
                ", username='" + username + '\'' +
                '}';
    }
}

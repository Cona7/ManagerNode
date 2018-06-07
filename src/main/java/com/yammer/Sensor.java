package com.yammer;

import com.google.gson.Gson;
import org.hibernate.validator.constraints.NotBlank;

public class Sensor {

    @NotBlank
    private String name;
    @NotBlank
    private String date;
    @NotBlank
    private int value;
    @NotBlank
    private String unit;

    public Sensor() {
   }

    public Sensor(String name, String date, int value, String unit) {
        this.name = name;
        this.date = date;
        this.value = value;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
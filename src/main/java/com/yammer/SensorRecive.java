package com.yammer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SensorRecive {

        int code;

        @SerializedName("data")
        List<Sensor> sensorList;

        public List<Sensor> getSensorList() {
            return sensorList;
        }


}

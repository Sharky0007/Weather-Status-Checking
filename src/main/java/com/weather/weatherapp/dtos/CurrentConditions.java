package com.weather.weatherapp.dtos;

import java.io.Serializable;
import java.time.LocalTime;
import lombok.Data;

@Data
public class CurrentConditions implements Serializable {
    private LocalTime datetime; 
    private long datetimeEpoch;
    private double temp;
    private double feelslike;
    private double humidity;
    private double windspeed;
    private String conditions;
    private String icon;
}

package com.weather.weatherapp.dtos;

import java.time.LocalTime;
import lombok.Data;

@Data
public class CurrentConditions {
    private LocalTime datetime; 
    private long datetimeEpoch;
    private double temp;
    private double feelslike;
    private double humidity;
    private double windspeed;
    private String conditions;
    private String icon;
}

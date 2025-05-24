package com.weather.weatherapp.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class WeatherResponseDto implements Serializable {
    
    private double latitude;
    private double longitude;
    private String resolvedAddress;
    private String timezone;
    private CurrentConditions currentConditions;
}

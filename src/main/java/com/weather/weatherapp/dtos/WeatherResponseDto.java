package com.weather.weatherapp.dtos;

import lombok.Data;

@Data
public class WeatherResponseDto {
    
    private double latitude;
    private double longitude;
    private String resolvedAddress;
    private String timezone;
    private CurrentConditions currentConditions;
}

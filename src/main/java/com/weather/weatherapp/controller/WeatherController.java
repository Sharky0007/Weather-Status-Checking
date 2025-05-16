package com.weather.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.weatherapp.dtos.WeatherResponseDto;
import com.weather.weatherapp.exceptions.DataNotFoundException;
import com.weather.weatherapp.service.WeatherSvc;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherSvc svc;

    @GetMapping
    public ResponseEntity<WeatherResponseDto> getWeather(@RequestParam String city) throws DataNotFoundException{
        WeatherResponseDto response = svc.getWeather(city);
        return ResponseEntity.ok().body(response);
        } 
}

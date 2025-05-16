package com.weather.weatherapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weather.weatherapp.dtos.CurrentConditions;
import com.weather.weatherapp.dtos.WeatherResponseDto;
import com.weather.weatherapp.exceptions.DataNotFoundException;

@Service
public class WeatherSvc {

    @Value("${weather.api.base-url}")
    private String baseUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherResponseDto getWeather(String city) throws DataNotFoundException {

        String url = baseUrl + city + "?unitGroup=metric&key=" + apiKey + "&include=current";

        RestTemplate restTemplate = new RestTemplate();
        CurrentConditions curr = new CurrentConditions();
        WeatherResponseDto res = new WeatherResponseDto();

    try{
            ResponseEntity<WeatherResponseDto> response = restTemplate.getForEntity(url, WeatherResponseDto.class);
            WeatherResponseDto json = response.getBody();
            res.setTimezone(json.getTimezone());
            res.setLatitude(json.getLatitude());
            res.setLongitude(json.getLongitude());
            res.setResolvedAddress(json.getResolvedAddress());
            curr.setDatetime(json.getCurrentConditions().getDatetime());
            curr.setDatetimeEpoch(json.getCurrentConditions().getDatetimeEpoch());
            curr.setTemp(json.getCurrentConditions().getTemp());
            curr.setFeelslike(json.getCurrentConditions().getFeelslike());
            curr.setHumidity(json.getCurrentConditions().getHumidity());
            curr.setIcon(json.getCurrentConditions().getIcon());
            curr.setWindspeed(json.getCurrentConditions().getWindspeed());
            curr.setConditions(json.getCurrentConditions().getConditions());
            res.setCurrentConditions(curr);
        } catch(Exception e){     
            throw new DataNotFoundException("No Data Found for city: "+ city);
        }
        return res;
    }
}

package com.weather.weatherapp.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    RedisTemplate<String, WeatherResponseDto> redisTemplate;

    // @Cacheable(value = "City", key = "#city")
    public WeatherResponseDto getWeather(String city) throws DataNotFoundException {

        String url = baseUrl + city + "?unitGroup=metric&key=" + apiKey + "&include=current";

        RestTemplate restTemplate = new RestTemplate();
        CurrentConditions curr = new CurrentConditions();
        WeatherResponseDto res = new WeatherResponseDto();

    try{
            System.out.println("Fetching fresh weather data for: " + city);
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
            setCache(city, res);
        } catch(Exception e){     
            e.printStackTrace();
            throw new DataNotFoundException("No Data Found for city: "+ city);
        }
        return res;
    }

    private void setCache(String city, WeatherResponseDto res){
       redisTemplate.opsForValue().set(city, res, Duration.ofMinutes(1));
       Object data = redisTemplate.opsForValue().get(city);
       System.out.println("data: "+ data);
    }
}

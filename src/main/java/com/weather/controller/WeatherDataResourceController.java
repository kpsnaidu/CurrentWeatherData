package com.weather.controller;


import com.weather.domain.CurrentWeatherDataVO;
import com.weather.service.WeatherDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/weather")
class WeatherDataResourceController {

    @Autowired
    private WeatherDataService weatherDataService;



    @GetMapping("/current")
    ResponseEntity<?> currentWeatherData(@RequestParam("cityId") String cityId) {

        try {
            CurrentWeatherDataVO response = weatherDataService.currentWeatherInfoFor(cityId);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to perform the request due to :"+e.getMessage());
        }

    }

}

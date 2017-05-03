package com.weather.service;

import com.weather.domain.CurrentWeatherDataVO;

import java.io.IOException;

public interface WeatherDataService {

    CurrentWeatherDataVO currentWeatherInfoFor(String cityName) throws IOException;
}

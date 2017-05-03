package com.weather.openweathermap.client;


import java.io.IOException;

public interface OpenWeatherMap {

    com.weather.openweathermap.domain.OpenWeatherMapResponse weatherInfoFor(String cityId) throws IOException;
}

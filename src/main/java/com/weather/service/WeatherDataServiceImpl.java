package com.weather.service;


import com.weather.domain.CityNameTimeZones;
import com.weather.domain.CurrentWeatherDataVO;
import com.weather.openweathermap.client.OpenWeatherMap;
import com.weather.openweathermap.domain.OpenWeatherMapResponse;
import com.weather.openweathermap.domain.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
class WeatherDataServiceImpl implements WeatherDataService{

    @Autowired
    private OpenWeatherMap openWeatherMap;


    @Override
    public CurrentWeatherDataVO currentWeatherInfoFor(String cityId) throws IOException {

        OpenWeatherMapResponse response = openWeatherMap.weatherInfoFor(cityId);

        CurrentWeatherDataVO currentWeatherDataVO = new CurrentWeatherDataVO();
        String cityName = response.getName();
        currentWeatherDataVO.setCityName(cityName);
        currentWeatherDataVO.setCurrentDate(convertUnixTimeToLocalTime(response.getDt(), cityName, "dd-MMM-yyyy"));

        Temperature temperature = new Temperature(response.getMain().getTemp());
        currentWeatherDataVO.setTemperatureInFahrenheit(temperature.getFahrenheit() + "F");
        currentWeatherDataVO.setTemperatureInCelsius(temperature.getCelsius() + "C");

        StringJoiner weatherDesc = new StringJoiner(",");
        response.getWeather().forEach(w -> weatherDesc.add(w.getDescription()));
        currentWeatherDataVO.setWeatherDescription(weatherDesc.toString());

        String sunrise = convertUnixTimeToLocalTime(response.getSys().getSunrise(), cityName, "hh:mm a");
        String sunset = convertUnixTimeToLocalTime(response.getSys().getSunset(), cityName, "hh:mm a");

        currentWeatherDataVO.setSunriseTime(sunrise);
        currentWeatherDataVO.setSunsetTime(sunset);

        return currentWeatherDataVO;
    }

    private String convertUnixTimeToLocalTime(Integer unixUTCTimeInSeconds, String cityName, String pattern) {
        Instant instant = Instant.ofEpochSecond(unixUTCTimeInSeconds);
        String timeZone = CityNameTimeZones.valueOf(cityName.replace(" ", "_").toUpperCase()).getTimeZone();
        return instant.atZone(ZoneId.of(timeZone)).format(DateTimeFormatter.ofPattern(pattern));
    }
}

package com.weather.openweathermap.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.openweathermap.domain.OpenWeatherMapResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
class OpenWeatherMapClient implements OpenWeatherMap {

    public OpenWeatherMapResponse weatherInfoFor(String cityId) throws IOException {

        String urlPath = "http://api.openweathermap.org/data/2.5/weather?id=" + cityId + "&appid=fb6b0b96f293cd4e489f039a2bf3dfc3";

        URL url = new URL(urlPath);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(urlConnection.getInputStream(), OpenWeatherMapResponse.class);
    }

}


package com.weather.openweathermap.client;

import com.weather.openweathermap.domain.OpenWeatherMapResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class OpenWeatherMapClientTest {

    @Test
    public void shouldGetCurrentWeatherInfoFromOpenWeatherMap() throws Exception {

        OpenWeatherMap openWeatherMap = new OpenWeatherMapClient();

        OpenWeatherMapResponse response = openWeatherMap.weatherInfoFor("2172797");

        assertNotNull(response);

        assertEquals("Cairns", response.getName());
        assertEquals("2172797", response.getId().toString());

    }
}
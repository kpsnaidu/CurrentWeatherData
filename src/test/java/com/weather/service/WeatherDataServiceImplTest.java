package com.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.domain.CurrentWeatherDataVO;
import com.weather.openweathermap.client.OpenWeatherMap;
import com.weather.openweathermap.domain.OpenWeatherMapResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class WeatherDataServiceImplTest {


    @Mock
    private OpenWeatherMap mockOpenWeatherMap;
    @InjectMocks
    private WeatherDataService weatherDataService = new WeatherDataServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldReturnOKStatusCodeWithQuoteInformation() throws Exception {

        File file = new File("src/test/resources/sample-weather-map-response.json");
        ObjectMapper objectMapper = new ObjectMapper();
        OpenWeatherMapResponse weatherMapResponse = objectMapper.readValue(file, OpenWeatherMapResponse.class);

        when(mockOpenWeatherMap.weatherInfoFor("123456")).thenReturn(weatherMapResponse);

        CurrentWeatherDataVO currentWeatherDataVO = weatherDataService.currentWeatherInfoFor("123456");

        assertNotNull(currentWeatherDataVO);
        assertEquals("London", currentWeatherDataVO.getCityName());
        assertEquals("02-May-2017", currentWeatherDataVO.getCurrentDate());
        assertEquals("overcast clouds", currentWeatherDataVO.getWeatherDescription());
        assertEquals("6.33C", currentWeatherDataVO.getTemperatureInCelsius());
        assertEquals("43.39F", currentWeatherDataVO.getTemperatureInFahrenheit());
        assertEquals("05:30 AM", currentWeatherDataVO.getSunriseTime());
        assertEquals("08:25 PM", currentWeatherDataVO.getSunsetTime());
    }
}

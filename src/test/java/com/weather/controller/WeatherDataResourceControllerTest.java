package com.weather.controller;

import com.weather.domain.CurrentWeatherDataVO;
import com.weather.service.WeatherDataService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherDataResourceControllerTest {

    @Mock
    private WeatherDataService mocWeatherDataService;
    @InjectMocks
    private WeatherDataResourceController weatherDataResourceHandler = new WeatherDataResourceController();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldReturnOKStatusCodeWithQuoteInformation() throws Exception {

        when(mocWeatherDataService.currentWeatherInfoFor("123456")).thenReturn(sampleCurrentWeatherDataVO());

        ResponseEntity<?> response = weatherDataResourceHandler.currentWeatherData("123456");


        assertEquals(200, response.getStatusCodeValue());
        CurrentWeatherDataVO entity = (CurrentWeatherDataVO)response.getBody();
        assertNotNull(entity);
        assertEquals("London", entity.getCityName());
        assertEquals("22C", entity.getTemperatureInCelsius());
        assertEquals("71.6F", entity.getTemperatureInFahrenheit());
        assertEquals("4:46am", entity.getSunriseTime());
        assertEquals("9:30pm", entity.getSunsetTime());
        assertEquals("Mostly Cloudy, windy", entity.getWeatherDescription());
    }

    @Test
    public void shouldThrowInternalServerErrorIfAnyExceptionFromService() throws Exception {

        IOException ioException = new IOException("unable to get the response from openweathermap");
        when(mocWeatherDataService.currentWeatherInfoFor("123456")).thenThrow(ioException);

        ResponseEntity response = weatherDataResourceHandler.currentWeatherData("123456");

        assertEquals(500, response.getStatusCodeValue());
        String result = (String) response.getBody();
        assertTrue(result.contains(ioException.getMessage()));

    }

    private CurrentWeatherDataVO sampleCurrentWeatherDataVO() {
        CurrentWeatherDataVO currentWeatherDataVO = new CurrentWeatherDataVO();
        currentWeatherDataVO.setCityName("London");
        currentWeatherDataVO.setCurrentDate(new Date().toString());
        currentWeatherDataVO.setWeatherDescription("Mostly Cloudy, windy");
        currentWeatherDataVO.setTemperatureInCelsius("22C");
        currentWeatherDataVO.setTemperatureInFahrenheit("71.6F");
        currentWeatherDataVO.setSunriseTime("4:46am");
        currentWeatherDataVO.setSunsetTime("9:30pm");
        return currentWeatherDataVO;
    }

}
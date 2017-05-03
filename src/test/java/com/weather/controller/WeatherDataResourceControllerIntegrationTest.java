package com.weather.controller;

import com.weather.domain.CurrentWeatherDataVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherDataResourceControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void shouldGetSuccessResponseFromRestService() throws Exception {

        CurrentWeatherDataVO response = this.testRestTemplate.getForObject("/weather/current?cityId=1819729", CurrentWeatherDataVO.class);

        assertEquals("Hong Kong", response.getCityName());
        assertEquals(new SimpleDateFormat("dd-MMM-yyyy").format(new Date()), response.getCurrentDate());
        assertNotNull(response.getWeatherDescription());
        assertNotNull(response.getTemperatureInCelsius());
        assertTrue(response.getTemperatureInCelsius().endsWith("C"));
        assertNotNull(response.getTemperatureInFahrenheit());
        assertTrue(response.getTemperatureInFahrenheit().endsWith("F"));
        assertNotNull(response.getSunriseTime());
        assertNotNull(response.getSunsetTime());
    }

}

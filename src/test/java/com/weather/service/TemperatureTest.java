package com.weather.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureTest {

    @Test
    public void shouldConvertKelvinToCelsiusCorrectly() throws Exception {

        assertEquals(22.10, new Temperature(295.25).getCelsius(), 0);
        assertEquals(20.45, new Temperature(293.60).getCelsius(), 0);
        assertEquals(-27.85, new Temperature(245.30).getCelsius(), 0);
        assertEquals(0, new Temperature(273.15).getCelsius(), 0);

    }

    @Test
    public void shouldConvertKelvinToFahrenheitCorrectly() throws Exception {

        assertEquals(71.78, new Temperature(295.25).getFahrenheit(), 0);
        assertEquals(68.81, new Temperature(293.60).getFahrenheit(), 0);
        assertEquals(-18.13, new Temperature(245.30).getFahrenheit(), 0);
        assertEquals(32, new Temperature(273.15).getFahrenheit(), 0);

    }
}
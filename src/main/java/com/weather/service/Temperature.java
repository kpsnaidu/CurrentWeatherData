package com.weather.service;

import java.math.BigDecimal;

public class Temperature {

    private double kelvin;

    public Temperature(double kelvin) {
        this.kelvin = kelvin;
    }

    double getCelsius(){
        return new BigDecimal(kelvin - 273.15).setScale(2,BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    double getFahrenheit(){
        return new BigDecimal(((kelvin - 273.15) * 1.8) + 32).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }
}

package com.weather.domain;

public enum CityNameTimeZones {


    LONDON("Europe/London"),
    HONG_KONG("Asia/Hong_Kong");

    private String timeZone;

    CityNameTimeZones(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeZone() {
        return timeZone;
    }
}

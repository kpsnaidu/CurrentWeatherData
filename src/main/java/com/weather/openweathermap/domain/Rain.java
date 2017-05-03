
package com.weather.openweathermap.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.annotation.Generated;

import java.util.DoubleSummaryStatistics;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "3h",
        "1h"
})
public class Rain {

    @JsonProperty("3h")
    private Double threeHr;

    @JsonProperty("1h")
    private Double oneHr;


    @JsonProperty("3h")
    public Double getThreeHr() {
        return threeHr;
    }


    @JsonProperty("3h")
    public void setThreeHr(Double threeHr) {
        this.threeHr = threeHr;
    }

    @JsonProperty("1h")
    public Double getOneHr() {
        return oneHr;
    }


    @JsonProperty("1h")
    public void setOneHr(Double oneHr) {
        this.oneHr = oneHr;
    }

}

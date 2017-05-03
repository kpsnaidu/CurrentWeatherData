
package com.weather.openweathermap.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.annotation.Generated;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "all"
})
public class Clouds {

    @JsonProperty("all")
    private Integer all;

    /**
     * 
     * @return
     *     The all
     */
    @JsonProperty("all")
    public Integer getAll() {
        return all;
    }

    /**
     * 
     * @param all
     *     The all
     */
    @JsonProperty("all")
    public void setAll(Integer all) {
        this.all = all;
    }

}

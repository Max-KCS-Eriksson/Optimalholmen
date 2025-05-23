package com.maxeriksson.optimalholmen.client.met.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "air_pressure_at_sea_level",
    "air_temperature",
    "cloud_area_fraction",
    "relative_humidity",
    "wind_from_direction",
    "wind_speed"
})
@Generated("jsonschema2pojo")
public class Details {

    @JsonProperty("air_pressure_at_sea_level")
    private Double airPressureAtSeaLevel;

    @JsonProperty("air_temperature")
    private Double airTemperature;

    @JsonProperty("cloud_area_fraction")
    private Double cloudAreaFraction;

    @JsonProperty("relative_humidity")
    private Double relativeHumidity;

    @JsonProperty("wind_from_direction")
    private Double windFromDirection;

    @JsonProperty("wind_speed")
    private Double windSpeed;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("air_pressure_at_sea_level")
    public Double getAirPressureAtSeaLevel() {
        return airPressureAtSeaLevel;
    }

    @JsonProperty("air_pressure_at_sea_level")
    public void setAirPressureAtSeaLevel(Double airPressureAtSeaLevel) {
        this.airPressureAtSeaLevel = airPressureAtSeaLevel;
    }

    @JsonProperty("air_temperature")
    public Double getAirTemperature() {
        return airTemperature;
    }

    @JsonProperty("air_temperature")
    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    @JsonProperty("cloud_area_fraction")
    public Double getCloudAreaFraction() {
        return cloudAreaFraction;
    }

    @JsonProperty("cloud_area_fraction")
    public void setCloudAreaFraction(Double cloudAreaFraction) {
        this.cloudAreaFraction = cloudAreaFraction;
    }

    @JsonProperty("relative_humidity")
    public Double getRelativeHumidity() {
        return relativeHumidity;
    }

    @JsonProperty("relative_humidity")
    public void setRelativeHumidity(Double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    @JsonProperty("wind_from_direction")
    public Double getWindFromDirection() {
        return windFromDirection;
    }

    @JsonProperty("wind_from_direction")
    public void setWindFromDirection(Double windFromDirection) {
        this.windFromDirection = windFromDirection;
    }

    @JsonProperty("wind_speed")
    public Double getWindSpeed() {
        return windSpeed;
    }

    @JsonProperty("wind_speed")
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

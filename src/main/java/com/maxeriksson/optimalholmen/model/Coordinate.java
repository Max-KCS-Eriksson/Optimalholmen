package com.maxeriksson.optimalholmen.model;

import lombok.Data;

@Data
public class Coordinate {

    private double longitude;
    private double latitude;

    public Coordinate setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Coordinate setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }
}

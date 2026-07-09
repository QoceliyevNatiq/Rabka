package com.rabka.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
    @Column(nullable = false, name = "latitude",columnDefinition = "DECIMAL(10,8)")
    private double latitude;

    @Column(nullable = false, name = "longitude", columnDefinition = "DECIMAL(11,8)")
    private double longitude;
}

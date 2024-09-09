package com.meet_up_spot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class City {
    private String name;
    private double latitude;
    private double longitude;
}

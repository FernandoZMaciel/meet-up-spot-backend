package com.meet_up_spot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TravelDTO {
    private City origin;
    private City destination;
    private double distance;
    private double duration;
}

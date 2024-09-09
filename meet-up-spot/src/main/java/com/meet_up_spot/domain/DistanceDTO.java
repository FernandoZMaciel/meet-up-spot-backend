package com.meet_up_spot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistanceDTO {
    private City origin;
    private City destination;
    private double distance;
    private double duration;
}

package com.meet_up_spot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TotalTravelDTO {
    private List<TravelDTO> travelDTOList;
    private double totalDistance;
    private double totalDuration;
}

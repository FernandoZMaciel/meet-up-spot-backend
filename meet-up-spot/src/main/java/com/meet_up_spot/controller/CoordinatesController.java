package com.meet_up_spot.controller;

import com.meet_up_spot.domain.City;
import com.meet_up_spot.domain.TotalTravelDTO;
import com.meet_up_spot.domain.TravelDTO;
import com.meet_up_spot.services.CoordinatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coordinates")
public class CoordinatesController {
    private final CoordinatesService coordinatesService = new CoordinatesService();


    @GetMapping("/meetup-spot")
    public TotalTravelDTO getTotalDistancesAndDuration (@RequestParam List<String> request){
        List <City> cities = coordinatesService.cityListByNameList(request);
        double[] coordinates = coordinatesService.getCenter(cities);
        System.out.println("lat: "+ coordinates[0] + ", lon: " + coordinates[1]);
        return coordinatesService.getTotalDistanceAndDuration(coordinatesService.getAllDistancesAndDuration(coordinatesService.getMeetPoint(cities, new City("Center", coordinates[0], coordinates[1]))));
    }

}

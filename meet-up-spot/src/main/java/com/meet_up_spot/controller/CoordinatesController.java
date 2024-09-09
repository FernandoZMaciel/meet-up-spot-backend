package com.meet_up_spot.controller;

import com.meet_up_spot.domain.City;
import com.meet_up_spot.domain.TotalTravelDTO;
import com.meet_up_spot.domain.TravelDTO;
import com.meet_up_spot.services.CoordinatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coordinates")
public class CoordinatesController {
    private final CoordinatesService coordinatesService = new CoordinatesService();
    private final List<City> cities = List.of(new City("Blumenau", -26.9194,  -49.0661),
            new City("Lapa", -25.7671, -49.7169),
            new City("Porto Alegre", -30.0346, -51.2177),
            new City("São Paulo", -23.5505, -46.6333));


    @GetMapping("/center")
    public double[] getCenter() {
        return coordinatesService.getCenter(cities);
    }

    @GetMapping("/distance")
    public double getDistance(){
        return coordinatesService.getDistance(cities.getFirst(), cities.getLast());
    }

    @GetMapping("/meetspot")

    public List<Map.Entry<City, Double>> getMeetPoint() {
        double[] coordinates = coordinatesService.getCenter(cities);
        return coordinatesService.getMeetPoint(cities, new City("Centro", coordinates[0], coordinates[1]));
    }

    @GetMapping("/listall")
    public List<TravelDTO> getAllDistancesAndDuration (){
        double[] coordinates = coordinatesService.getCenter(cities);
        return coordinatesService.getAllDistancesAndDuration(coordinatesService.getMeetPoint(cities, new City("Centro", coordinates[0], coordinates[1])));
    }

    @GetMapping("/listallandtotal")
    public TotalTravelDTO getTotalDistancesAndDuration (){
        double[] coordinates = coordinatesService.getCenter(cities);
        return coordinatesService.getTotalDistanceAndDuration(coordinatesService.getAllDistancesAndDuration(coordinatesService.getMeetPoint(cities, new City("Centro", coordinates[0], coordinates[1]))));
    }
}

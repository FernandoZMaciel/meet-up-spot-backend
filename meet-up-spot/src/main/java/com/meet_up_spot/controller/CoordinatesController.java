package com.meet_up_spot.controller;

import com.meet_up_spot.domain.City;
import com.meet_up_spot.services.CoordinatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coordinates")
public class CoordinatesController {
    private final CoordinatesService coordinatesService = new CoordinatesService();

    @GetMapping("/center")
    public double[] getCenter() {
        List<City> cities = List.of(new City("Blumenau", -26.9194,  -49.0661),
                new City("São Paulo", -23.5505, -46.6333),
                new City("Lapa", -25.7671, -49.7169),
                new City("Porto Alegre", -30.0346, -51.2177));


        return coordinatesService.getCenter(cities);
    }

}

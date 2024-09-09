package com.meet_up_spot.controller;

import com.meet_up_spot.domain.City;
import com.meet_up_spot.domain.DistanceDTO;
import com.meet_up_spot.services.OpenRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final OpenRouteService apiService;

    public RouteController(OpenRouteService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/external-data")
    public DistanceDTO fetchData() {
        return apiService.getDataFromApi(new City("Blumenau", -26.9194,  -49.0661),  new City("São Paulo", -23.5505, -46.6333));
    }
}

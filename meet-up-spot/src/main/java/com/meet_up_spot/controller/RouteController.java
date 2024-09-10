package com.meet_up_spot.controller;

import com.meet_up_spot.domain.City;
import com.meet_up_spot.domain.TravelDTO;
import com.meet_up_spot.services.OpenRouteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final OpenRouteService apiService;

    public RouteController(OpenRouteService apiService) {
        this.apiService = apiService;
    }
    public TravelDTO fetchData() {
        return apiService.getDataFromApi(new City("Blumenau", -26.9194,  -49.0661),  new City("Porto Alegre", -30.0346, -51.2177));
    }

    public City getLatAndLongByCityName() {
        return apiService.getLatAndLongByCityName("Blumenau");
    }
}

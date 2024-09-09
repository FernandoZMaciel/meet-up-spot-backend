package com.meet_up_spot.controller;

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
    public ResponseEntity<?> fetchData() {
        String url = "https://api-v2.distancematrix.ai/maps/api/distancematrix/json?origins=51.4822656,-0.1933769&destinations=51.4994794,-0.1269979&key=YcUz8JvYZDWUOotIo8a3hfqsuMy9uuxCd2vw2ZZPPj3Leu9GtYgvh8Qa7NByjLGr";
        Map<String, Object> data = apiService.getDataFromApi(url);
        return ResponseEntity.ok(data);
    }
}

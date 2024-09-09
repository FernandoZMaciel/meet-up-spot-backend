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
        Map<String, Object> data = apiService.getDataFromApi();
        return ResponseEntity.ok(data);
    }
}

package com.meet_up_spot.services;

import com.meet_up_spot.domain.City;
import com.meet_up_spot.domain.TotalTravelDTO;
import com.meet_up_spot.domain.TravelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class CoordinatesService {
    private final OpenRouteService openRouteService;

    @Autowired
    public CoordinatesService(OpenRouteService openRouteService) {
        this.openRouteService = openRouteService;
    }

    public double[] getCenter(List<City> cities) {
        double coordinatesX = 0;
        double coordinatesY = 0;
        double coordinatesZ = 0;
        double[] centerCoordinates = new double[2];
        for (City city : cities) {
            double radLat = Math.toRadians(city.getLatitude());
            double radLong = Math.toRadians(city.getLongitude());
            coordinatesX += (Math.cos(radLat) * Math.cos(radLong)) / cities.size();
            coordinatesY += (Math.cos(radLat) * Math.sin(radLong)) / cities.size();
            coordinatesZ += (Math.sin(radLat)) / cities.size();
        }

        double hypotenuse = Math.sqrt(coordinatesX * coordinatesX + coordinatesY * coordinatesY);
        centerCoordinates[0] = Math.toDegrees(Math.atan2(coordinatesZ, hypotenuse));
        centerCoordinates[1] = Math.toDegrees(Math.atan2(coordinatesY, coordinatesX));

        return centerCoordinates;
    }

    public double getDistance(City originCity, City destinationCity) {
        double radOriginCityLat = Math.toRadians(originCity.getLatitude());
        double radOriginCityLong = Math.toRadians(originCity.getLongitude());
        double radDestinationCityLat = Math.toRadians(destinationCity.getLatitude());
        double radDestinationCityLong = Math.toRadians(destinationCity.getLongitude());
        double distanceLatDelta = Math.sin((radDestinationCityLat - radOriginCityLat) / 2);
        double distanceLongDelta = Math.sin((radDestinationCityLong - radOriginCityLong) / 2);
        double distanceDelta = distanceLatDelta * distanceLatDelta + Math.cos(radOriginCityLat) * Math.cos(radDestinationCityLat) * distanceLongDelta * distanceLongDelta;

        double EARTH_RADIUS_METERS = 6371000;
        return 2 * EARTH_RADIUS_METERS * Math.asin(Math.sqrt(distanceDelta));
    }

    public List<Map.Entry<City, Double>> getMeetPoint(List<City> cities, City center) {
        List<Map.Entry<City, Double>> list = new ArrayList<>();
        for (City city : cities) {
            list.add(new AbstractMap.SimpleEntry<>(city, this.getDistance(city, center)));
        }
        list.sort(Map.Entry.comparingByValue());

        return list;
    }

    public List<TravelDTO> getAllDistancesAndDuration(List<Map.Entry<City, Double>> cities){
        List<TravelDTO> list = new ArrayList<>();
        City meetSpot = cities.getFirst().getKey();
        for (Map.Entry<City, Double> city : cities) {
            if (!meetSpot.equals(city.getKey())) {
                list.add(openRouteService.getDataFromApi(city.getKey(), meetSpot));
            }
        }

        return list;
    }

    public TotalTravelDTO getTotalDistanceAndDuration(List<TravelDTO> travelDTOList){
        double totalDistance = 0;
        double totalDuration = 0;
        for (TravelDTO travelDTO : travelDTOList) {
            totalDistance += travelDTO.getDistance();
            totalDuration += travelDTO.getDuration();
        }
        return new TotalTravelDTO(travelDTOList, totalDistance, totalDuration);
    }

    public List<City> cityListByNameList(List<String> list){
        List<City> cities = new ArrayList<>();
        for (String cityName : list) {
            cities.add(openRouteService.getLatAndLongByCityName(cityName));
        }
        return cities;
    }

}

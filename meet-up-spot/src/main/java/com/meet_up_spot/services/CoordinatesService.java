package com.meet_up_spot.services;

import com.meet_up_spot.domain.City;

import java.util.List;

public class CoordinatesService {
    public double[] getCenter(List<City> cities){
        double coordinatesX = 0;
        double coordinatesY = 0;
        double coordinatesZ = 0;
        double[] centerCoordinates = new double[2];
        for (City city : cities) {
            double radLat = Math.toRadians(city.getLatitude());
            double radLong = Math.toRadians(city.getLongitude());
            coordinatesX += (Math.cos(radLat)*Math.cos(radLong))/cities.size();
            coordinatesY += (Math.cos(radLat)*Math.sin(radLong))/cities.size();
            coordinatesZ += (Math.sin(radLat))/cities.size();
        }

        double hypotenuse = Math.sqrt(coordinatesX*coordinatesX + coordinatesY*coordinatesY);
        centerCoordinates[0] = Math.toDegrees(Math.atan2(coordinatesZ, hypotenuse));
        centerCoordinates[1] = Math.toDegrees(Math.atan2(coordinatesY, coordinatesX));

        return centerCoordinates;
    }
}

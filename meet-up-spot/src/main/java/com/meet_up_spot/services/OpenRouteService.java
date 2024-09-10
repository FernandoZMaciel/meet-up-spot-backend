package com.meet_up_spot.services;

import com.meet_up_spot.domain.City;
import com.meet_up_spot.domain.TravelDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Log4j2
@Service
public class OpenRouteService {
    private final WebClient webClient;
    private final Environment environment;


    public OpenRouteService(WebClient.Builder webClientBuilder, Environment environment) {
        this.webClient = webClientBuilder.baseUrl("").build();
        this.environment = environment;
    }

    @SuppressWarnings("rawtypes")
    public TravelDTO getDataFromApi(City origin, City destination) {
        String coordinatesOrigin = origin.getLatitude() + "," + origin.getLongitude();
        String coordinatesDestination = destination.getLatitude() + "," + destination.getLongitude();
        String routeKey = environment.getProperty("route.key");
        String endpoint = String.format("https://api-v2.distancematrix.ai/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s", coordinatesOrigin, coordinatesDestination, routeKey);
        Mono<Map> response = this.webClient.get().uri(endpoint).retrieve().bodyToMono(Map.class);


        String rawString =  response.block().get("rows").toString();
        log.info(rawString);
        String regex = "value=(\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawString);

        double distance = 0;
        double duration = 0;
        int count = 0;

        while (matcher.find()) {
            if (count == 0) {
                distance = Double.parseDouble(matcher.group(1));
            } else if (count == 1) {
                duration = Double.parseDouble(matcher.group(1));
            }
            count++;
        }
        return new TravelDTO(origin, destination, distance, duration);
    }

    public City getLatAndLongByCityName(String cityName) {
        String latKey = environment.getProperty("lat.key");
        String endpoint = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s,BR&limit=1&appid=%s", cityName, latKey);
        Mono<List<Map<String, Object>>> response = this.webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {});
        List<Map<String, Object>> resultList = response.block();
         double cityLat= (Double) resultList.get(0).get("lat");
         double cityLon = (Double) resultList.get(0).get("lon");
        return new City(cityName, cityLat, cityLon);
    }


}

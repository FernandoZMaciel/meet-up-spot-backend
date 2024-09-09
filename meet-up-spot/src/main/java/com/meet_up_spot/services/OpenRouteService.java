package com.meet_up_spot.services;

import com.meet_up_spot.domain.City;
import com.meet_up_spot.domain.TravelDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Log4j2
@Service
public class OpenRouteService {
    private final WebClient webClient;
    private final String API_KEY = "YcUz8JvYZDWUOotIo8a3hfqsuMy9uuxCd2vw2ZZPPj3Leu9GtYgvh8Qa7NByjLGr";

    public OpenRouteService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("").build();
    }

    @SuppressWarnings("rawtypes")
    public TravelDTO getDataFromApi(City origin, City destination) {
        String coordinatesOrigin = origin.getLatitude() + "," + origin.getLongitude();
        String coordinatesDestination = destination.getLatitude() + "," + destination.getLongitude();
        String endpoint = String.format("https://api-v2.distancematrix.ai/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s", coordinatesOrigin, coordinatesDestination, API_KEY);
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
}

package com.meet_up_spot.services;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;


@Service
public class OpenRouteService {
    private final WebClient webClient;

    public OpenRouteService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("").build();
    }

    public Map<String, Object> getDataFromApi() {
        String endpoint = "https://api-v2.distancematrix.ai/maps/api/distancematrix/json?origins=51.4822656,-0.1933769&destinations=51.4994794,-0.1269979&key=YcUz8JvYZDWUOotIo8a3hfqsuMy9uuxCd2vw2ZZPPj3Leu9GtYgvh8Qa7NByjLGr"
        Mono<Map> response = this.webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(Map.class);
        return response.block();
    }
}

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

    public Map<String, Object> getDataFromApi(String endpoint) {
        Mono<Map> response = this.webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(Map.class);
        return response.block();
    }
}

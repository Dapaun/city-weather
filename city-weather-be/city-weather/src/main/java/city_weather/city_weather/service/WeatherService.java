package city_weather.city_weather.service;

import city_weather.city_weather.dto.WeatherDTO;
import city_weather.city_weather.model.WeatherLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final WebClient webClient;
    private final String apiKey;

    public WeatherService(
            WebClient.Builder webClientBuilder,
            @Value("${weatherLayer.base-url}") String baseUrl,
            @Value("${weatherLayer.api-key}") String apiKey
    ) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    public WeatherLayer getWeatherByCity(String cityName) {
        log.info("Get weather {}", cityName);
        WeatherLayer response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("weather")
                        .queryParam("q", cityName)
                        .queryParam("appid", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(WeatherLayer.class)
                .block();

        System.out.println("RAW JSON = " + response);
        if (response == null) {
            throw new RuntimeException("Weather nor found: {}" + cityName);
        }
        return response;
    }
}
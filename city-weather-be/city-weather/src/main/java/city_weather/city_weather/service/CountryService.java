package city_weather.city_weather.service;

import city_weather.city_weather.dto.CountryDTO;
import city_weather.city_weather.model.CountryLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CountryService {
    private static final Logger log = LoggerFactory.getLogger(CountryService.class);
    private final WebClient webClient;
    private final String apiKey;

    public CountryService(
            WebClient.Builder webClientBuilder,
            @Value("${countrylayer.base-url}") String baseUrl,
            @Value("${countrylayer.api-key}") String apiKey
    ) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    public CountryLayer getCountryByName(String countryName) {
        log.info("Get country {}", countryName);
        CountryLayer[] response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/name/{name}")
                        .queryParam("access_key", apiKey)
                        .queryParam("fullText", true)
                        .queryParam("filters", "name;capital;region")
                        .build(countryName))
                .retrieve()
                .bodyToMono(CountryLayer[].class)
                .block();

        System.out.println("RAW JSON = " + response);
        if(response == null) {
            throw new RuntimeException("Country nor found: {}" + countryName);
        }
        return response[0];
    }

    public CountryDTO getCountryDTO(String name) {
        CountryLayer raw = getCountryByName(name);  // <-- this is YOUR existing method

        CountryDTO dto = new CountryDTO();
        dto.setName(raw.getName());
        dto.setCity(raw.getCapital());
        dto.setRegion(raw.getRegion());

        return dto;
    }
}

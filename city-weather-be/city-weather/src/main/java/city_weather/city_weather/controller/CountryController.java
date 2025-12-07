package city_weather.city_weather.controller;

import city_weather.city_weather.dto.CountryDTO;
import city_weather.city_weather.model.CountryLayer;
import city_weather.city_weather.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/country")
    public CountryDTO getCountry(@RequestParam String name) {
        return countryService.getCountryDTO(name);
    }
}

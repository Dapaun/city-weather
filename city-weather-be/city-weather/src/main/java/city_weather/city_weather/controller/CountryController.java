package city_weather.city_weather.controller;

import city_weather.city_weather.dto.CountryWeatherDTO;
import city_weather.city_weather.service.CountryWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CountryController {
    private final CountryWeatherService countryWeatherService;

    public CountryController(CountryWeatherService countryWeatherService) {
        this.countryWeatherService = countryWeatherService;
    }

    @GetMapping("/country")
    public CountryWeatherDTO getCountry(@RequestParam String name) {
        return countryWeatherService.getCountryWeather(name);
    }
}

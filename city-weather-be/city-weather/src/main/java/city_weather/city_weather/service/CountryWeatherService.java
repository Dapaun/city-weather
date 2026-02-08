package city_weather.city_weather.service;

import city_weather.city_weather.dto.CountryWeatherDTO;
import city_weather.city_weather.dto.WeatherDTO;
import city_weather.city_weather.model.CountryLayer;
import city_weather.city_weather.model.WeatherLayer;
import org.springframework.stereotype.Service;

@Service
public class CountryWeatherService {

    private final CountryService countryService;
    private final WeatherService weatherService;

    public CountryWeatherService(CountryService countryService,
                                 WeatherService weatherService) {
        this.countryService = countryService;
        this.weatherService = weatherService;
    }

    public CountryWeatherDTO getCountryWeather(String name) {
        CountryLayer country = countryService.getCountryByName(name);
        WeatherLayer weatherLayer = weatherService.getWeatherByCity(country.getCapital());
        WeatherDTO weatherDTO = toWeatherDTO(weatherLayer);

        CountryWeatherDTO dto = new CountryWeatherDTO();
        dto.setName(country.getName());
        dto.setCity(country.getCapital());
        dto.setRegion(country.getRegion());
        dto.setWeather(weatherDTO);

        return dto;
    }

    private WeatherDTO toWeatherDTO(WeatherLayer raw) {
        WeatherDTO dto = new WeatherDTO();

        if (raw.getWeather() != null && !raw.getWeather().isEmpty()) {
            dto.setDescription(raw.getWeather().get(0).getDescription());
        }

        if (raw.getMain() != null) {
            dto.setTemperature(raw.getMain().getTemp());
            dto.setHumidity(raw.getMain().getHumidity());
        }

        if (raw.getWind() != null) {
            dto.setWindSpeed(raw.getWind().getSpeed());
        }

        return dto;
    }
}


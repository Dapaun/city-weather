package city_weather.city_weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityWeatherApplication {

	private static final Logger log = LoggerFactory.getLogger(CityWeatherApplication.class);
	private static final String COUNTRY_API_KEY = "3c7143d6ac21b5839a8eecf5dbdb22b9";
	private static final String COUNTRY_API_ENDPOINT = "api.countrylayer.com/v2/name/{name}?access_key=";

	public static void main(String[] args) {
		log.info("Server started->");
		SpringApplication.run(CityWeatherApplication.class, args);
	}

}

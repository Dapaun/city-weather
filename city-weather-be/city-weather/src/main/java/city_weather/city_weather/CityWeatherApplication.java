package city_weather.city_weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityWeatherApplication {

	private static final Logger log = LoggerFactory.getLogger(CityWeatherApplication.class);

	public static void main(String[] args) {
		log.info("Server started->");
		SpringApplication.run(CityWeatherApplication.class, args);
	}

}

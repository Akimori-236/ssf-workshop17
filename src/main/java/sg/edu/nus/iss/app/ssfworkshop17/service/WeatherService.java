package sg.edu.nus.iss.app.ssfworkshop17.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.app.ssfworkshop17.model.Weather;

@Service
public class WeatherService {
    private static final String OPEN_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    public Optional<Weather> getweather(String city, String units) throws IOException {
        String apiKey = System.getenv("OPEN_WEATHER_WEB_API_KEY");
        System.out.println("KEY >>> " + apiKey);
        // Building the custom url
        String weatherUrl = UriComponentsBuilder.fromUriString(OPEN_WEATHER_URL)
                .queryParam("q", city.replaceAll(" ", "+"))
                .queryParam("units", units) // metric or standard or imperial
                .queryParam("appId", apiKey)
                .toUriString();
        System.out.println("URL -> " + weatherUrl);

        RestTemplate template = new RestTemplate();
        // default to null
        ResponseEntity<String> resp = null;
        resp = template.getForEntity(weatherUrl, String.class);
        // create weather object from json response body
        Weather w = Weather.create(resp.getBody());
        if (w == null) {
            return Optional.empty();
        }
        return Optional.of(w);
    }
    
    public String getTime(Long unix) {
        // create instance of time
        Instant inst = Instant.ofEpochSecond(unix);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return inst.format(dtf);
    }

    
}

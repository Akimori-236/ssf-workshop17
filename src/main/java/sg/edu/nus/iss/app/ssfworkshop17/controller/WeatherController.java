package sg.edu.nus.iss.app.ssfworkshop17.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.app.ssfworkshop17.model.Weather;
import sg.edu.nus.iss.app.ssfworkshop17.service.WeatherService;

@Controller
@RequestMapping(path = "/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping
    public String getWeather(@RequestParam(required = true) String city, 
                @RequestParam(required = false) String units, Model model) 
                throws IOException {
        if (units == null || units.isEmpty()) {
            units = "metric";
        }
        Optional<Weather> w = weatherSvc.getweather(city, units);

        model.addAttribute("weather", w.get());
        return "weather";
    }
}

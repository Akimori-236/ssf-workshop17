package sg.edu.nus.iss.app.ssfworkshop17.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Weather implements Serializable {
    private String city;
    private String temperature;

    // weather in json response is an array, could have more than 1 condition obj
    public List<Condition> conditions = new LinkedList<>();

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public static Weather create(String json) throws IOException {
        Weather w = new Weather();
        try {
            InputStream is = new ByteArrayInputStream(json.getBytes());
            JsonReader jr = Json.createReader(is);
            // read the json into object
            JsonObject jo = jr.readObject();
            w.setCity(jo.getString("name"));
            JsonObject mainObj = jo.getJsonObject("main");
            w.setTemperature(mainObj.getJsonNumber("temp").toString());
            w.conditions = jo.getJsonArray("weather")
                    .stream()
                    .map(v -> (JsonObject) v)
                    .map(v -> Condition.createJson(v))
                    .toList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return w;
    }
}

package sg.edu.nus.iss.app.ssfworkshop17.model;

import java.io.Serializable;

import jakarta.json.JsonObject;

public class Condition implements Serializable {
    private String description;
    private String icon;
    private static String iconZoom = "4x";
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static Condition createJson(JsonObject jobj) {
        Condition c = new Condition();
        c.description = "%s - %s".formatted(
                jobj.getString("main"), jobj.getString("description"));
        // http://openweathermap.org/img/wn/10d@2x.png
        c.icon = "http://openweathermap.org/img/wn/%s@%s.png".formatted(
            jobj.getString("icon"), iconZoom
            );
        return c;
    }
}

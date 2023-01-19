package sg.edu.nus.iss.app.ssfworkshop17.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Sys {
    private Long sunrise;
    private Long sunset;
    private int timezone;

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getDate(Long epoch) {
        Date d = new Date(epoch * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat(
                "hh:mm a z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(d);
    }

    public String getSunriseTime() {
        return getDate(this.sunrise);
    }

    public String getSunsetTime() {
        return getDate(this.sunset);
    }
}

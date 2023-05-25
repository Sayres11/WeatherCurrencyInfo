package zad1;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;
import java.util.stream.Collectors;

public class Weather {
    private String weatherURL;
    private String country;
    private String city;
    private String curCode;
    private double temp;
    private String json;

    public Weather(String country) {
        this.country = country;
    }

    public String getJson(String location) {
        try {
            city = location;
            weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "," + country + "&appid=67051998f96f1fcc4962b74b4be15838";
            URL url = new URL(weatherURL);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            json = bufferedReader.lines().collect(Collectors.joining());
            setCurrencyCode(country);
            return json;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTemp(double temp) {
        this.temp = temp - 273.15;
    }

    public void setCurrencyCode(String country) {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (locale.getDisplayCountry(Locale.forLanguageTag("EN")).equals(country)) {
                Currency currency1 = Currency.getInstance(locale);
                curCode = currency1.getCurrencyCode();
            }
        }
    }

    public void setWeatherURL(String weatherURL) {
        this.weatherURL = weatherURL;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public double getTemp() throws IOException, ParseException {
        if (json != null) {
            try {
                JSONParser parser = new JSONParser();
                JSONObject parse = (JSONObject) parser.parse(getJson(city));
                JSONObject parseMain = (JSONObject) parse.get("main");
                setTemp(Double.parseDouble(String.valueOf(parseMain.get("temp"))));
                return temp;
            } catch (ParseException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            return 0.0;
        }
        return temp;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCurCode() {
        return curCode;
    }

    public String getJson() {
        return json;
    }

    public String getWeatherURL() {
        return weatherURL;
    }
}

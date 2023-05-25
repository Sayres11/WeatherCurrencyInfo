/**
 *
 *  @author Karabelnikau Aliaksei S22141
 *
 */

package zad1;

public class Service {
    private String curCode;
    private final Weather weather;
    private final Currency currency = new Currency();

    public Service(String country) {
        weather = new Weather(country);
    }

    String getWeather(String city)  {
        return weather.getJson(city);
    }

    Double getRateFor(String currency_code) {
        curCode = currency_code;
        return currency.getExchangeRate(currency_code, weather);
    }

    Double getNBPRate() {
        if(currency.NBP(weather)==null){
            return 0.0;
        }
        return Double.valueOf((currency.NBP(weather).getString("kurs_sredni").replaceAll(",", ".")));
    }


    public Currency getCurrency() {
        return currency;
    }

    public Weather getWeather() {
        return weather;
    }

    public String getCurCode() {
        return curCode;
    }
}



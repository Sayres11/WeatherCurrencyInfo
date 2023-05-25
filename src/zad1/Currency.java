package zad1;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Currency {
    private String from;

    public double getExchangeRate(String currency_code, Weather weather) {
        from = currency_code;

        String rate = getContentFromUrl("https://api.exchangerate.host/convert?from=" + currency_code + "&to=" + weather.getCurCode());
        JSONObject jsonObj = new JSONObject(rate);

        return  jsonObj.getDouble("result");
    }

    public JSONObject NBP(Weather weather) {
        String xmlA = getContentFromUrl("https://www.nbp.pl/kursy/xml/a054z220318.xml");
        String xmlB = getContentFromUrl("https://www.nbp.pl/kursy/xml/b011z220316.xml");

        JSONObject jsonObj = XML.toJSONObject(xmlA);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObj);
        jsonObj = XML.toJSONObject(xmlB);
        jsonArray.put(jsonObj);

        for (int i = 0; i < jsonArray.length(); i++) {
            for (int j = 0; j < jsonArray.getJSONObject(i).optJSONObject("tabela_kursow").getJSONArray("pozycja").length(); j++) {
                JSONObject objTemp = jsonArray.getJSONObject(i).optJSONObject("tabela_kursow").getJSONArray("pozycja").getJSONObject(j);
                if (objTemp.optString("kod_waluty").equals(weather.getCurCode())) {
                    return objTemp;
                }
            }
        }
        return null;
    }

    static public String getContentFromUrl(String url) {
        StringBuffer textData = null;
        URL myURL;

        try {
            myURL = new URL(url);
            BufferedReader rd = new BufferedReader(new InputStreamReader(myURL.openConnection().getInputStream()));

            textData = new StringBuffer();
            String line;

            while ((line = rd.readLine()) != null) {
                textData.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textData != null ? textData.toString() : "";
    }

    public String getFrom() {
        return from;
    }
}

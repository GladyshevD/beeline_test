package ru.beeline.geo.util;

import org.json.JSONObject;
import ru.beeline.geo.util.exception.UrlFormingException;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GeoDecoding {

    private GeoDecoding() {
    }

    public static String getCity(double latitude, double longitude) throws UrlFormingException {
        String requestURL = encodeParams(latitude, longitude);
        return getString(requestURL);
    }

    //get string of URL to Yandex GeoCode
    private static String encodeParams(double latitude, double longitude) {
        String geoCode = latitude + "," + longitude;
        String key = "de51caa4-4674-448b-95df-f1be1b857227";
        String URL = "https://geocode-maps.yandex.ru/1.x";

        return URL + "?" + "geocode=" + geoCode +
                "&apikey=" + key +
                "&kind=locality" +
                "&format=json" +
                "&lang=ru_RU" +
                "&sco=latlong" +
                "&results=1";
    }

    //get city from Yandex Geocode
    private static String getString(String requestURL) throws UrlFormingException {

        Scanner scanner;
        StringBuilder str = new StringBuilder();

        try {
            URL url = new URL(requestURL);
            scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                str.append(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            throw new UrlFormingException();
        }

        JSONObject obj = new JSONObject(str.toString());

        return obj.getJSONObject("response")
                .getJSONObject("GeoObjectCollection")
                .getJSONArray("featureMember")
                .getJSONObject(0)
                .getJSONObject("GeoObject")
                .getString("name");
    }
}

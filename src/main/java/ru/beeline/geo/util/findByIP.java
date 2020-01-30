package ru.beeline.geo.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;
import ru.beeline.geo.util.exception.IPHandlerException;
import ru.beeline.geo.util.exception.UrlFormingException;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import static ru.beeline.geo.util.GeoDecoding.getCity;

public class findByIP {
    public static final String dataBaseCity = "/home/dimas/java/projects/GEOIP2/GeoLite2-City_20200128/GeoLite2-City.mmdb";

    public static String getDataBaseCity(String ipAddress) throws IPHandlerException, UrlFormingException {
        File dbFile = new File(dataBaseCity);

        Location location;
        try {
            DatabaseReader reader = new DatabaseReader.Builder(dbFile).build();
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            CityResponse response = reader.city(inetAddress);
            location = response.getLocation();
        } catch (IOException | GeoIp2Exception e) {
            throw new IPHandlerException();
        }

        return getCity(location.getLatitude(), location.getLongitude());
    }
}

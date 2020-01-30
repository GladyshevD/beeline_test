package ru.beeline.geo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.beeline.geo.model.SalesPointDo;
import ru.beeline.geo.repository.SalesPointsRepository;
import ru.beeline.geo.util.exception.IPHandlerException;
import ru.beeline.geo.util.exception.UrlFormingException;

import java.util.List;

import static ru.beeline.geo.util.GeoDecoding.getCity;
import static ru.beeline.geo.util.findByIP.getDataBaseCity;

@Service
public class StoresService {

    private final SalesPointsRepository repository;

    @Autowired
    public StoresService(SalesPointsRepository repository) {
        this.repository = repository;
    }

    public List<SalesPointDo> getPoints(double latitude, double longitude, String remoteAddr)
            throws UrlFormingException, IPHandlerException {
        String city;
        if (checkCoordinates(latitude, longitude)) {
            city = getDataBaseCity(remoteAddr);
        } else {
            city = getCity(latitude, longitude);
        }

        return repository.getSalesPointDosByCity(city);
    }

    private boolean checkCoordinates(Double latitude, Double longitude) {
        return latitude == null || longitude == null || (latitude == 0 && longitude == 0);
    }
}

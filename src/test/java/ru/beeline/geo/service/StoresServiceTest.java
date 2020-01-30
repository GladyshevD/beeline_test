package ru.beeline.geo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.beeline.geo.model.SalesPointDo;
import ru.beeline.geo.util.exception.IPFindingException;
import ru.beeline.geo.util.exception.NotPointsFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.beeline.geo.service.SalesPointsTestData.salesPointsByCity;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
class StoresServiceTest {
    private static final String BAD_IP = "0.0.0.0";
    private static final String IP_MOSCOW = "213.24.134.62";
    private static final String MOSCOW_CITY = "Москва";
    private static final Double OTHER_CITY_LAT = 53.202772;
    private static final Double OTHER_CITY_LON = 44.982118;
    private static final Double BAD_LAT = 99.0;
    private static final Double BAD_LON = 99.0;

    @Autowired
    protected StoresService service;

    @Test
        //Get Sales Points when latitude ang longitude equal null and request IP is incorrect
    void getPointsNullFieldsAndBadIP() {
        assertThrows(IPFindingException.class, () ->
                service.getPoints(null, null, BAD_IP));
    }

    @Test
        //Get Sales Points when latitude ang longitude equal null and request IP is correct
        //and is present in GeoLite2 database
    void getPointsNullFieldsButIp() {
        List<SalesPointDo> salesPointDoList = service.getPoints(null, null, IP_MOSCOW);
        assertMatch(salesPointDoList, salesPointsByCity.get(MOSCOW_CITY));
    }


    @Test
        //Get Sales Points when latitude ang longitude equal zero and request IP is incorrect
    void getPointsWithZeroFieldsAndBadIP() {
        assertThrows(IPFindingException.class, () ->
                service.getPoints(Double.parseDouble("0"), Double.parseDouble("0"), BAD_IP));
    }

    @Test
        //Get Sales Points when latitude ang longitude equal zero and request IP is correct
        //and is present in GeoLite2 database
    void getPointsWithZeroFieldsButIP() {
        List<SalesPointDo> salesPointDoList = service.getPoints(Double.parseDouble("0"), Double.parseDouble("0"), IP_MOSCOW);
        assertMatch(salesPointDoList, salesPointsByCity.get(MOSCOW_CITY));
    }

    @Test
        //Get Sales Points when latitude ang longitude of city are not present in Sales Points database
        //and request IP is incorrect
    void PointsNotFoundInCity() {
        assertThrows(NotPointsFoundException.class, () ->
                service.getPoints(OTHER_CITY_LAT, OTHER_CITY_LON, BAD_IP));
    }

    @Test
        //Get Sale Points when latitude ang longitude of city and response IP are incorrect
    void getPointsBadLatLongAndIP() {
        assertThrows(NotPointsFoundException.class, () ->
                service.getPoints(BAD_LAT, BAD_LON, BAD_IP));
    }

    @Test
        //Get Sale Points when latitude ang longitude of city are incorrect although response IP is correct
    void getPointsBadLatLongButIP() {
        assertThrows(NotPointsFoundException.class, () ->
                service.getPoints(BAD_LAT, BAD_LON, IP_MOSCOW));
    }

    public <T> void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered").isEqualTo(expected);
    }
}
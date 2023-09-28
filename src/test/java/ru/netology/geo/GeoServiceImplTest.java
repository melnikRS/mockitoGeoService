package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void IsByIp() {

        var geoService = new GeoServiceImpl();

        Assertions.assertEquals(Country.RUSSIA, geoService.byIp("172.").getCountry());

    }
}
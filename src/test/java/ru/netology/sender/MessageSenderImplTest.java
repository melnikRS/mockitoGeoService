package ru.netology.sender;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {

    Map<String, String> headers = new HashMap<String, String>();

    @Test
    void IsSendMoscow() {
        var localizationService = Mockito.mock(LocalizationService.class);
        var geoService = Mockito.mock(GeoService.class);

        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(geoService.byIp("172.123.12.19")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        var messageSender = new MessageSenderImpl(geoService, localizationService);

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    void IsSendRu() {
        var localizationService = Mockito.mock(LocalizationService.class);
        var geoService = Mockito.mock(GeoService.class);

        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        var messageSender = new MessageSenderImpl(geoService, localizationService);

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    void IsSendEn() {
        var geoService = Mockito.mock(GeoService.class);
        var localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(geoService.byIp("96.")).thenReturn(new Location("New York", Country.USA, null,  0));

        var messageSender = new MessageSenderImpl(geoService, localizationService);

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }

    @Test
    void IsSendNewYork() {
        var geoService = Mockito.mock(GeoService.class);
        var localizationService = Mockito.mock(LocalizationService.class);

        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        var messageSender = new MessageSenderImpl(geoService, localizationService);

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }
}
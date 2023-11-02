import netology.sender.MessageSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import netology.geo.GeoService;
import netology.i18n.LocalizationService;
import netology.sender.MessageSenderImpl;
import netology.enity.Country;
import netology.enity.Location;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;

public class MessageSenderTest {
    @Test
    public void testSendRussianMessage() {
      GeoService geoService = Mockito.mock(GeoService.class);
      Mockito.when(geoService.byIp(anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 12345));
        // Создаем мок объект LocalizationService
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        // Задаем поведение мок объекта: всегда возвращать русский текст
        Mockito.when(localizationService.locale(Country.valueOf("RUSSIA"))).thenReturn("Привет");

        // Создаем объект MessageSenderImpl и передаем ему мок объекты
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        // Создаем заголовки с российским IP-адресом
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "192.168.0.1");

        // Вызываем метод send()
        String result = messageSender.send(headers);

        // Проверяем, что результат соответствует ожидаемому русскому тексту
        Assertions.assertEquals("Привет", result);
    }
    @Test
    public void testSendEnglishMessage() {
        // Создаем мок объект GeoService
        GeoService geoService = Mockito.mock(GeoService.class);
        // Задаем поведение мок объекта: всегда возвращать американскую локацию
        Mockito.when(geoService.byIp(anyString())).thenReturn(new Location("New York", Country.USA, "Times Square", 345));

        // Создаем мок объект LocalizationService
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        // Задаем поведение мок объекта: всегда возвращать английский текст
        Mockito.when(localizationService.locale(Country.valueOf("USA"))).thenReturn("Hello");

        // Создаем объект MessageSenderImpl и передаем ему мок объекты
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        // Создаем заголовки с американским IP-адресом
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "123.456.789.0");

        // Вызываем метод send()
        String result = messageSender.send(headers);

        // Проверяем, что результат соответствует ожидаемому английскому тексту
        Assertions.assertEquals("Hello", result);
    }
}

package netology.geo;

import netology.enity.Country;
import netology.enity.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    public void testByIp_MoscowIp_ReturnsMoscowLocation() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String ip = "172.0.32.11";

        // Act
        Location result = geoService.byIp(ip);

        // Assert
        assertEquals("Moscow", result.getCity());
        assertEquals(Country.RUSSIA, result.getCountry());
        assertEquals("Lenina", result.getStreet());
        assertEquals(15, result.getBuiling());
    }



    @Test
    public void testByIp_NewYorkIp_ReturnsNewYorkLocation() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String ip = "96.44.183.149";

        // Act
        Location result = geoService.byIp(ip);

        // Assert
        assertEquals("New York", result.getCity());
        assertEquals(Country.USA, result.getCountry());
        assertEquals(" 10th Avenue", result.getStreet());
        assertEquals(32, result.getBuiling());
    }



    @Test
    public void testByIp_InternalMoscowIp_ReturnsMoscowLocation() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String ip = "172.16.0.1";

        // Act
        Location result = geoService.byIp(ip);

        // Assert
        assertEquals("Moscow", result.getCity());
        assertEquals(Country.RUSSIA, result.getCountry());
        assertNull(result.getStreet());
        assertEquals(0, result.getBuiling());
    }


    @Test
    public void testByIp_InternalNewYorkIp_ReturnsNewYorkLocation() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String ip = "96.0.0.1";

        // Act
        Location result = geoService.byIp(ip);

        // Assert
        assertEquals("New York", result.getCity());
        assertEquals(Country.USA, result.getCountry());
        assertNull(result.getStreet());
        assertEquals(0, result.getBuiling());
    }



    @Test
    public void testByIp_UnknownIp_ReturnsNull() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();
        String ip = "192.168.0.1";

        // Act
        Location result = geoService.byIp(ip);

        // Assert
        assertNull(result);
    }
}
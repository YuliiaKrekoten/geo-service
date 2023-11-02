package netology.geo;
import netology.enity.Location;

public interface GeoService {
    Location byIp(String ip);

    Location byCoordinates(double latitude, double longitude);
}

package netology.i18n;
import netology.enity.Country;

public class LocalizationServiceImpl implements LocalizationService{
    public String locale(Country country) {
        switch (country) {
            case RUSSIA:
                return "Добро пожаловать";
            default:
                return "Welcome";
        }
    }
}

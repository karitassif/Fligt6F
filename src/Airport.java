/**
 * Created by atlim on 29.3.2017.
 */
public class Airport {

    private String airportCity;
    private String airportCountry;
    private String airportCode;

    public Airport(String airportCity, String airportCountry, String airportCode){
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
        this.airportCode = airportCode;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportCountry() {
        return airportCountry;
    }
}

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface DBManagerInterface {

    public List<Flight> findFlights(String departure, String destination, int maxPrice,
                                    int passengerCount, Calendar depTime);
    public List<Flight> findFlights(int maxPrice);

    public void changeAvailableSeats(int flightNumber, int passengerCount);

}

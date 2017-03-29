import java.util.Date;
import java.util.List;

public interface DBManagerInterface {

    public List<Flight> findFlights(String departure, String destination, int maxPrice,
                                    int passengerCount, Date depTime);

    public void changeAvailableSeats(int flightNumber, int passengerCount);
}

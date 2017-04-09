import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface DBManagerInterface {

    public List<Flight> findFlights(String departure, String destination, int maxPrice,
                                    int passengerCount, Calendar depTime) throws SQLException;
    public List<Flight> findFlights(int maxPrice) throws SQLException;

    public void changeAvailableSeats(int flightNumber, int passengerCount) throws SQLException;

}

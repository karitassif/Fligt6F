import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by atlim on 29.3.2017.
 */
public class DBMock implements DBManagerInterface {

    public List<Flight> findFlights(String departure, String destination, int maxPrice,
                                    int passengerCount, Date depTime){
        return new ArrayList<Flight>();

    }

    public void changeAvailableSeats(int flightNumber, int passengerCount){

    }

}

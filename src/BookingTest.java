import edu.princeton.cs.algs4.StdIn;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by atlim on 10.4.2017.
 */
public class BookingTest {

    public static void main(String[] args) throws SQLException {
        SearchController sc = new SearchController();
        Calendar cal = new GregorianCalendar(2017, 7, 24);
        sc.searchFlights("KEF", "CHI", 100000, 2, cal);
        List<Flight> flights = sc.getFlights();
        for (Flight flight : flights){
            String flightinfo = "";
            flightinfo += flight.getFlightNumber() + ": ";
            flightinfo += "from " + flight.getDeparture().getAirportCity();
            flightinfo += " to " + flight.getDestination().getAirportCity() + " ";
            flightinfo += "Departure " + flight.getFormattedDepTime();
            flightinfo += " Arrival " + flight.getFormattedArrTime();
            flightinfo += " Price: " + flight.getPrice();

            System.out.println(flightinfo);
        }
        BookingController bc = new BookingController();
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger("2009952959", "Atli Marcher Palsson"));
        passengers.add(new Passenger("1211963529", "Karlotta Bjorg Hjaltadottir"));

        Booking booking = bc.bookFlight(flights.get(1), passengers,"Blablabla");
        System.out.println(booking.getBookingID());
        bc.searchBooking(booking.getBookingID());

    }
}

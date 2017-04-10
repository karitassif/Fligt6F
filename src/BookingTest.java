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

        int bookingid = bc.bookFlight(flights.get(1), passengers,"Blablabla");
        System.out.println(bookingid);
        Booking booking  = bc.searchBooking(bookingid);
        System.out.println(booking.getBookingID());
        System.out.println(booking.getFlight().getFormattedDepTime());
        System.out.println(booking.getFlight().getFormattedArrTime());
        System.out.println(booking.getFlight().getDeparture().getAirportCity());
        System.out.println(booking.getFlight().getDestination().getAirportCity());
        for (Passenger passenger : booking.getPassengers()){
            System.out.println(passenger.getKennitala() + " " + passenger.getName());
        }

    }
}

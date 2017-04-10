import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by atlim on 5.4.2017.
 */
public class BookingController {

    public void bookFlight(Flight flight, List<Passenger> passengers, String comment) throws SQLException {
        DatabaseManagerBooking DBMB = new DatabaseManagerBooking();
        DatabaseManagerSearch DBMS = new DatabaseManagerSearch();
        Booking booking = new Booking(1, passengers, flight, comment);
        DBMS.changeAvailableSeats(flight.getFlightNumber(), passengers.size());
        DBMB.addBooking(booking);
    }

    public Booking searchBooking(int bookingID) throws SQLException {
        DatabaseManagerBooking DBMB = new DatabaseManagerBooking();
        return DBMB.findBooking(bookingID);
    }

}

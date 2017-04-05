import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by atlim on 5.4.2017.
 */
public class BookingController {

    public void bookFlight(Flight flight, Passenger[] passengers, String comment){
        DatabaseManagerBooking DBMB = new DatabaseManagerBooking();
        Booking booking = new Booking(1, passengers, flight, comment);
        DBMB.addBooking(booking);
    }

    public Booking searchBooking(int bookingID){
        DatabaseManagerBooking DBMB = new DatabaseManagerBooking();
        return DBMB.findBooking(bookingID);
    }
}

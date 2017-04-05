import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by atlim on 5.4.2017.
 */
public class BookingController {

    public void bookFlight(Flight flight, Passenger[] passengers, String comment){

    }

    public Booking searchBooking(int bookingID){
        Airport KEF = new Airport("Keflavik", "Iceland", "KEF");
        Airport CPH = new Airport("Copenhagen", "Denmark", "CPH");
        Calendar deptime1 = new GregorianCalendar(2017, 5, 24, 14,30);
        Calendar arrtime1 = new GregorianCalendar(2017, 5, 24, 17,30);
        Flight flight = new Flight(1, KEF, CPH, 10, deptime1, arrtime1, 200, 0.3);
        return new Booking(1, new Passenger[2], flight, "Fokkjú");
    }
}

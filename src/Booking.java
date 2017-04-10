import java.awt.print.Book;
import java.util.List;

/**
 * Created by atlim on 5.4.2017.
 */
public class Booking {

    private int bookingID;
    private List<Passenger> passengers;
    private Flight flight;
    private String comment;


    public Booking(int bookingID, List<Passenger> passengers, Flight flight, String comment){
        for (int i = 0; i < passengers.size(); i++){
            if (passengers.get(i) == null) throw new IllegalArgumentException("Fokkjú");
        }
        this.bookingID = bookingID;
        this.flight = flight;
        this.comment = comment;
    }

    public int getBookingID() {
        return bookingID;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getComment() {
        return comment;
    }
}

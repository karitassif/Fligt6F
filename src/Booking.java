import java.awt.print.Book;

/**
 * Created by atlim on 5.4.2017.
 */
public class Booking {

    private int bookingID;
    private Passenger[] passengers;
    private Flight flight;
    private String comment;


    public Booking(int bookingID, Passenger[] passengers, Flight flight, String comment){
        for (int i = 0; i < passengers.length; i++){
            if (passengers[i] == null) throw new IllegalArgumentException("Fokkjú");
        }
        this.bookingID = bookingID;
        this.flight = flight;
        this.comment = comment;
    }

    public int getBookingID() {
        return bookingID;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getComment() {
        return comment;
    }
}

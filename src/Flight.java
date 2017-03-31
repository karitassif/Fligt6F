import java.util.Calendar;
import java.util.Date;

/**
 * Created by atlim on 29.3.2017.
 */
public class Flight {

    private int flightNumber;
    private Airport departure;
    private Airport destination;
    private int price;
    private int availableSeats;
    private Calendar depTime;
    private Calendar arrTime;
    private double childDiscount;

    public Flight(int flightNumber, Airport departure, Airport destination, int price, Calendar depTime,
                  Calendar arrTime, int availableSeats, double childDiscount){
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
        this.availableSeats = availableSeats;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.childDiscount = childDiscount;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getDestination() {
        return destination;
    }

    public Calendar getDepTime() {
        return depTime;
    }

    public Calendar getArrTime() {
        return arrTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getChildDiscount() {
        return childDiscount;
    }

    public int getPrice() {
        return price;
    }
}


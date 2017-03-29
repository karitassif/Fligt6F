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
    private Date depTime;
    private Date arrTime;
    private float childDiscount;

    public Flight(int flightNumber, Airport departure, Airport destination, int price, Date depTime,
                  Date arrTime, int availableSeats, float childDiscount){
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

    public Date getDepTime() {
        return depTime;
    }

    public Date getArrTime() {
        return arrTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public float getChildDiscount() {
        return childDiscount;
    }

    public int getPrice() {
        return price;
    }
}


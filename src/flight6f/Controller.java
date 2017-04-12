package flight6f;

import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

    public void buttonPressed(ActionEvent event) throws SQLException {
        SearchController sc = new SearchController();
        Calendar cal = new GregorianCalendar(2017,06,23);
        sc.searchDiscountFlights(20000, cal);
        for (Flight flight : sc.getFlights()) {
            String flightinfo = "";
            flightinfo += flight.getFlightNumber() + ": ";
            flightinfo += "from " + flight.getDeparture().getAirportCity();
            flightinfo += " to " + flight.getDestination().getAirportCity() + " ";
            flightinfo += "Departure " + flight.getFormattedDepTime();
            flightinfo += " Arrival " + flight.getFormattedArrTime();
            flightinfo += " Price: " + flight.getPrice();

            System.out.println(flightinfo);
        }
    }
}

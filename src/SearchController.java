import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchController {

    private List<Flight> flights;
    private List<Flight> filteredFlights;
    DBManagerInterface dbm = new DatabaseManagerSearch();

    public void searchFlights(String departure, String destination, int maxPrice,
                              int passengerCount, Calendar depTime) throws SQLException {
        flights = dbm.findFlights(departure, destination, maxPrice, passengerCount, depTime);
    }

    public void searchDiscountFlights(int maxPrice) throws SQLException {
        flights  = dbm.findFlights(maxPrice);
    }

    public int showPrice(Flight flight, int adultCount, int childCount){
        return (int) (adultCount*flight.getPrice() + 1.0*childCount*flight.getChildDiscount()*flight.getPrice());
    }

    public void filterFlights(int maxPrice, boolean childDiscount){
        filteredFlights = new ArrayList<Flight>();
        for (Flight flight : flights){
            boolean cDiscount = flight.getChildDiscount() < 1;
            if (maxPrice >= flight.getPrice() && cDiscount == childDiscount){
                filteredFlights.add(flight);
            }
        }

    }

    public void sortFlights(boolean sortByPrice){
        if (sortByPrice) {
            for (int i = 0; i < flights.size(); i++) {
                int index = i;
                int lowest = flights.get(i).getPrice();
                for (int j = i+1; j < flights.size(); j++) {
                    if (flights.get(j).getPrice() < lowest){
                        lowest = flights.get(j).getPrice();
                        index  = j;
                    }
                }
                Flight temp = flights.get(i);
                flights.set(i, flights.get(index));
                flights.set(index, temp);
            }
        }
        else{
            for (int i = 0; i < flights.size(); i++) {
                int index = i;
                Calendar lowest = flights.get(i).getDepTime();
                for (int j = i + 1; j < flights.size(); j++) {
                    if (flights.get(j).getDepTime().compareTo(lowest) < 0) {
                        lowest = flights.get(j).getDepTime();
                        index = j;
                    }
                }
                Flight temp = flights.get(i);
                flights.set(i, flights.get(index));
                flights.set(index, temp);
            }
        }

    }

    public List<Flight> getFlights() {

        return flights;
    }

    public List<Flight> getFilteredFlights() {

        return filteredFlights;
    }
}

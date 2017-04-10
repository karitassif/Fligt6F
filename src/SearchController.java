import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SearchController {

    private List<Flight> flights;
    private List<Flight> filteredFlights;
    DBManagerInterface dbm = new DatabaseManagerSearch();

    public void searchFlights(String departure, String destination, int maxPrice,
                              int passengerCount, Calendar depTime) throws SQLException {
        flights = dbm.findFlights(departure, destination, maxPrice, passengerCount, depTime);
    }

    public void searchDiscountFlights(int maxPrice, Calendar deptime) throws SQLException {
        flights  = dbm.findFlights(maxPrice, deptime);
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
    public static void main(String[] args) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. hh:mm");
        SearchController sc = new SearchController();
        Calendar cal = new GregorianCalendar(2017,11,1);
        sc.searchFlights("KEF", "CPH", 50000, 5, cal);
        for (Flight flight : sc.getFlights()){
            String flightinfo = "";
            flightinfo += flight.getFlightNumber() + ": ";
            flightinfo += "from " + flight.getDeparture().getAirportCity();
            flightinfo += " to " + flight.getDestination().getAirportCity() + " ";
            flightinfo += flight.getDepTime().get(Calendar.DAY_OF_MONTH) + ".";
            flightinfo += flight.getDepTime().get(Calendar.MONTH) + ".";
            flightinfo += flight.getDepTime().get(Calendar.YEAR) + "  ";
            flightinfo += flight.getDepTime().get(Calendar.HOUR_OF_DAY) + ":";
            flightinfo += flight.getDepTime().get(Calendar.MINUTE) + ", ";
            flightinfo += flight.getArrTime().get(Calendar.HOUR_OF_DAY) + ":";
            flightinfo += flight.getArrTime().get(Calendar.MINUTE) + ", ";
            //System.out.println(flightinfo);
        }
        sc.searchDiscountFlights(70000, cal);
        for (Flight flight : sc.getFlights()){
            String flightinfo = "";
            flightinfo += flight.getFlightNumber() + ": ";
            flightinfo += "from " + flight.getDeparture().getAirportCity();
            flightinfo += " to " + flight.getDestination().getAirportCity() + " ";
            flightinfo += "Departure " + flight.getFormattedDepTime();
            flightinfo += " Arrival " + flight.getFormattedArrTime();
            flightinfo += " Price: " + flight.getPrice();

            System.out.println(flightinfo);
            //System.out.println(date);
        }
    }
}

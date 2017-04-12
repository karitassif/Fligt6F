import java.util.*;

/**
 * Created by atlim on 29.3.2017.
 */
public class DBMock /*implements flight6f.DBManagerInterface*/ {

    /*
    List<flight6f.Flight> flights;

    public DBMock(){
        flights = new ArrayList<flight6f.Flight>();
        flight6f.Airport KEF = new flight6f.Airport("Keflavik", "Iceland", "KEF");
        flight6f.Airport CPH = new flight6f.Airport("Copenhagen", "Denmark", "CPH");
        flight6f.Airport ARN = new flight6f.Airport("Stockholm", "Sweden", "ARN");
        flight6f.Airport CDG = new flight6f.Airport("Paris", "France", "CDG");
        flight6f.Airport BER = new flight6f.Airport("Berlin", "Germany", "BER");
        flight6f.Airport YYZ = new flight6f.Airport("Toronto", "Canada", "YYZ");

        Calendar deptime1 = new GregorianCalendar(2017, 5, 24, 14,30);
        Calendar arrtime1 = new GregorianCalendar(2017, 5, 24, 17,30);
        Calendar deptime2 = new GregorianCalendar(2017, 6, 24, 17,0);
        Calendar arrtime2 = new GregorianCalendar(2017, 6, 24, 18,0);
        Calendar deptime3 = new GregorianCalendar(2017, 5, 22, 7,30);
        Calendar arrtime3 = new GregorianCalendar(2017, 5, 22, 9,30);
        Calendar deptime4 = new GregorianCalendar(2017, 3, 24, 6,25);
        Calendar arrtime4 = new GregorianCalendar(2017, 3, 24, 10,30);
        Calendar arrtime5 = new GregorianCalendar(2017, 4, 12, 14,30);
        Calendar deptime5 = new GregorianCalendar(2017, 4, 12, 23,30);
        Calendar deptime6 = new GregorianCalendar(2017, 6, 24, 17,15);
        Calendar arrtime6 = new GregorianCalendar(2017, 6, 24, 18,0);
        Calendar deptime7 = new GregorianCalendar(2017, 6, 24, 17,20);
        Calendar arrtime7 = new GregorianCalendar(2017, 6, 24, 18,0);

        flights.add(new flight6f.Flight(1, KEF, CPH, 10, deptime1, arrtime1, 200, 0.3));
        flights.add(new flight6f.Flight(2, KEF, ARN, 11, deptime2, arrtime2, 5, 1));
        flights.add(new flight6f.Flight(3, KEF, ARN, 12, deptime6, arrtime6, 4, 0.3));
        flights.add(new flight6f.Flight(4, KEF, ARN, 13, deptime7, arrtime7, 3, 0.3));
        flights.add(new flight6f.Flight(5, KEF, CDG, 12, deptime3, arrtime3, 4, 0.3));
        flights.add(new flight6f.Flight(6, KEF, BER, 13, deptime4, arrtime4, 3, 0.3));
        flights.add(new flight6f.Flight(7, KEF, YYZ, 14, deptime5, arrtime5, 0, 0.3));
    }

    public List<flight6f.Flight> findFlights(String departure, String destination, int maxPrice,
                                    int passengerCount, Calendar depTime){
        List<flight6f.Flight> result = new ArrayList<flight6f.Flight>();
        for(flight6f.Flight flight : flights){
            if (departure.equals(flight.getDeparture().getAirportCode()) &&
                    destination.equals(flight.getDestination().getAirportCode()) &&
                    (maxPrice >= flight.getPrice()) &&
                    (passengerCount <= flight.getAvailableSeats()) &&
                    (depTime.get(Calendar.YEAR) == flight.getDepTime().get(Calendar.YEAR)) &&
                    (depTime.get(Calendar.MONTH) == flight.getDepTime().get(Calendar.MONTH)) &&
                    (depTime.get(Calendar.DAY_OF_MONTH) == flight.getDepTime().get(Calendar.DAY_OF_MONTH))){
                result.add(flight);
            }
        }
        return result;
    }

    public List<flight6f.Flight> findFlights(int maxPrice){
        List<flight6f.Flight> result = new ArrayList<flight6f.Flight>();
        for(flight6f.Flight flight : flights){
            if (flight.getPrice() <= maxPrice){
                result.add(flight);
            }
        }
        return result;
    }

    public void changeAvailableSeats(int flightNumber, int passengerCount){
    }*/

}

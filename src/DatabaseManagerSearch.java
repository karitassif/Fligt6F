import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by atlim on 9.4.2017.
 */
public class DatabaseManagerSearch implements DBManagerInterface{

    private final String url = "jdbc:postgresql://localhost/FLIGHT6F";
    private final String user = "postgres";
    private final String password = "Flight6f";

    private Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public List<Flight> findFlights(String departure, String destination, int maxPrice,
                                    int passengerCount, Calendar depTime) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        String date = "'" + depTime.get(Calendar.DAY_OF_MONTH) + "." + depTime.get(Calendar.MONTH) + "." +
                depTime.get(Calendar.YEAR) + "%'";
        String sql = "select * from flights where depcode = " + "'" + departure + "'" + "and arrcode =" +
                "'"  + destination + "'"
                + "and price <" + maxPrice + " and availableseats >" + passengerCount + " and deptime like " + date;
        ResultSet rs =  statement.executeQuery(sql);

        List<Flight> flights = new ArrayList<>();

        while (rs.next()){

            int flightnumber = rs.getInt("flightnumber");
            String depcode = rs.getString("depcode");
            String depcity = rs.getString("depcity");
            String depcountry = rs.getString("depcountry");
            String arrcode = rs.getString("arrcode");
            String arrcity = rs.getString("arrcity");
            String arrcountry = rs.getString("arrcountry");
            int price = rs.getInt("price");
            int available = rs.getInt("availableseats");
            double childdiscount = rs.getDouble("childdiscount");
            String dept = rs.getString("deptime");
            String arrt = rs.getString("arrtime");



            String[] depsplit = dept.split("\\.");
            String[] arrsplit = arrt.split("\\.");


            Airport airport1 = new Airport(depcity, depcountry, depcode);
            Airport airport2 = new Airport(arrcity, arrcountry, arrcode);
            Calendar dep = new GregorianCalendar(Integer.parseInt(depsplit[2]), Integer.parseInt(depsplit[1])
                                                , Integer.parseInt(depsplit[0]), Integer.parseInt(depsplit [3])
                                                , Integer.parseInt(depsplit[4]) );
            Calendar arr = new GregorianCalendar(Integer.parseInt(arrsplit[2]), Integer.parseInt(arrsplit[1])
                    , Integer.parseInt(arrsplit[0]), Integer.parseInt(arrsplit [3])
                    , Integer.parseInt(arrsplit[4]) );

            Flight flight = new Flight(flightnumber, airport1, airport2, price, dep, arr, available, childdiscount);
            flights.add(flight);
        }
        return flights;
    }
    public List<Flight> findFlights(int maxPrice) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        String sql = "select * from flights where price <" + maxPrice;
        ResultSet rs =  statement.executeQuery(sql);

        List<Flight> flights = new ArrayList<>();

        while (rs.next()){

            int flightnumber = rs.getInt("flightnumber");
            String depcode = rs.getString("depcode");
            String depcity = rs.getString("depcity");
            String depcountry = rs.getString("depcountry");
            String arrcode = rs.getString("arrcode");
            String arrcity = rs.getString("arrcity");
            String arrcountry = rs.getString("arrcountry");
            int price = rs.getInt("price");
            int available = rs.getInt("availableseats");
            double childdiscount = rs.getDouble("childdiscount");
            String dept = rs.getString("deptime");
            String arrt = rs.getString("arrtime");



            String[] depsplit = dept.split("\\.");
            String[] arrsplit = arrt.split("\\.");


            Airport airport1 = new Airport(depcity, depcountry, depcode);
            Airport airport2 = new Airport(arrcity, arrcountry, arrcode);
            Calendar dep = new GregorianCalendar(Integer.parseInt(depsplit[2]), Integer.parseInt(depsplit[1])
                    , Integer.parseInt(depsplit[0]), Integer.parseInt(depsplit [3])
                    , Integer.parseInt(depsplit[4]) );
            Calendar arr = new GregorianCalendar(Integer.parseInt(arrsplit[2]), Integer.parseInt(arrsplit[1])
                    , Integer.parseInt(arrsplit[0]), Integer.parseInt(arrsplit [3])
                    , Integer.parseInt(arrsplit[4]) );

            Flight flight = new Flight(flightnumber, airport1, airport2, price, dep, arr, available, childdiscount);
            flights.add(flight);
        }
        return flights;
    }

    public void changeAvailableSeats(int flightNumber, int passengerCount) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        String sql = "select availableseats from flights where flightnumber = " + flightNumber;
        ResultSet rs = statement.executeQuery(sql);
        int seats = 0;
        while (rs.next()) {
            seats = rs.getInt("availableseats");
        }
        seats -= passengerCount;
        String update = "update flights set availableseats =" + seats + "where flightnumber = " + flightNumber;
        statement.execute(update);
    }

    public static void main(String[] args) throws SQLException {
        Calendar cal = new GregorianCalendar(2017,5,24);
        DatabaseManagerSearch dbms = new DatabaseManagerSearch();
        List<Flight> res = dbms.findFlights(20000);
        for (Flight flight : res){
            System.out.println(flight.getDeparture().getAirportCode());
            System.out.println(flight.getDestination().getAirportCode());
            System.out.println();
        }
        dbms.changeAvailableSeats(23005,5);
    }



}

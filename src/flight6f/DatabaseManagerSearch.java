package flight6f;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by atlim on 9.4.2017.
 */
public class DatabaseManagerSearch implements DBManagerInterface{

    private final String url = "jdbc:postgresql://127.0.0.1:5432/FLIGHT6F";
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

    /* because of duplicate code make private method to create flight list from ResultSet

     */
    private List<Flight> makeFlights(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {

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
            String depdate = rs.getString("depdate");
            String arrdate = rs.getString("arrdate");


            String[] depsplit = dept.split("\\.");
            String[] arrsplit = arrt.split("\\.");
            String[] depdatesplit = depdate.split("-");
            String[] arrdatesplit = arrdate.split("-");


            Airport airport1 = new Airport(depcity, depcountry, depcode);
            Airport airport2 = new Airport(arrcity, arrcountry, arrcode);
            // Calendar.java starts month with 0 so we subtract 1 from date
            Calendar dep = new GregorianCalendar(Integer.parseInt(depdatesplit[0]),
                    Integer.parseInt(depdatesplit[1])-1,
                    Integer.parseInt(depdatesplit[2]),
                    Integer.parseInt(depsplit[0]),
                    Integer.parseInt(depsplit[1]));
            Calendar arr = new GregorianCalendar(Integer.parseInt(arrdatesplit[0]),
                    Integer.parseInt(arrdatesplit[1])-1,
                    Integer.parseInt(arrdatesplit[2]),
                    Integer.parseInt(arrsplit[0]),
                    Integer.parseInt(arrsplit[1]));

            Flight flight = new Flight(flightnumber, airport1, airport2, price, dep, arr, available, childdiscount);
            flights.add(flight);
        }
        return flights;
    }


    //Finnur flug útfrá flugvallarkóða
    public List<Flight> findFlights(String departure, String destination, int maxPrice,
                                    int passengerCount, Calendar depTime) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        Calendar before = (Calendar) depTime.clone();
        Calendar after = (Calendar) depTime.clone();
        //Search every flight within parameters in one week frame, the middle of the week being search parameter
        before.add(Calendar.DAY_OF_YEAR, -3);
        after.add(Calendar.DAY_OF_YEAR, 3);
        String beforedate = "'" + before.get(Calendar.YEAR) + "-" + (before.get(Calendar.MONTH)+1) + "-" +
                            before.get(Calendar.DAY_OF_MONTH) + "'";
        String afterdate = "'" + after.get(Calendar.YEAR) + "-" + (after.get(Calendar.MONTH)+1) + "-" +
                           after.get(Calendar.DAY_OF_MONTH) + "'";


        String sql = "select * from flights where depcode = " + "'" + departure + "'" + "and arrcode ="
                + "'"  + destination + "'" + "and price <" + maxPrice + " and availableseats >"
                + passengerCount + " and depdate BETWEEN " + beforedate +  " and " + afterdate;
        ResultSet rs =  statement.executeQuery(sql);

        return makeFlights(rs);
    }
    public List<Flight> findFlights(int maxPrice, Calendar depTime, int passengerCount) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        Calendar before = (Calendar) depTime.clone();
        Calendar after = (Calendar) depTime.clone();
        before.add(Calendar.DAY_OF_YEAR, -3);
        after.add(Calendar.DAY_OF_YEAR, 3);
        String beforedate = "'" + before.get(Calendar.YEAR) + "-" + (before.get(Calendar.MONTH)+1) + "-" +
                before.get(Calendar.DAY_OF_MONTH) + "'";
        String afterdate = "'" + after.get(Calendar.YEAR) + "-" + (after.get(Calendar.MONTH)+1) + "-" +
                after.get(Calendar.DAY_OF_MONTH) + "'";
        String sql = "select * from flights where price <" + maxPrice + " and availableseats >" + passengerCount +
                " and depdate between " + beforedate + " and " + afterdate;
        ResultSet rs =  statement.executeQuery(sql);

        return makeFlights(rs);
    }

    public void changeAvailableSeats(int flightNumber, int passengerCount) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        //get number of available seats on given flight
        String sql = "select availableseats from flights where flightnumber = " + flightNumber;
        ResultSet rs = statement.executeQuery(sql);
        int seats = 0;
        while (rs.next()) {
            seats = rs.getInt("availableseats");
        }
        // update available seats in database
        seats -= passengerCount;
        String update = "update flights set availableseats =" + seats + "where flightnumber = " + flightNumber;
        statement.execute(update);
    }

    public static void main(String[] args) throws SQLException {
        Calendar cal = new GregorianCalendar(2017,6,24);
        DatabaseManagerSearch dbms = new DatabaseManagerSearch();
        List<Flight> res = dbms.findFlights("KEF", "CPH", 100000, 5, cal);
        for (Flight flight : res){
            System.out.println(flight.getDeparture().getAirportCode());
            System.out.println(flight.getDestination().getAirportCode());
            System.out.println();
        }
        dbms.changeAvailableSeats(23005,5);
    }



}

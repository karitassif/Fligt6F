import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by atlim on 5.4.2017.
 */
public class DatabaseManagerBooking {

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


    public Booking findBooking(int bookingID) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        String sql = "select * from bookings where bookingid = " + bookingID;
        ResultSet rs = statement.executeQuery(sql);
        List<Passenger> passengerlist = new ArrayList<>();
        int id = 0;
        int flightnumber = 0;
        String comment = "";
        String pass = "";
        while (rs.next()){
            id = rs.getInt("bookingid");
            flightnumber = rs.getInt("flightnumber");
            comment = rs.getString("comment");
            pass = rs.getString("passengers");
            String[] passengers = pass.split(";");
            for (int i = 0; i < passengers.length; i++){
                String[] passenger = passengers[i].split(",");
                Passenger passenger1 = new Passenger(passenger[0], passenger[1]);
                passengerlist.add(passenger1);
            }
        }
        sql = "select * from flights where fligtnumber =" + flightnumber;
        rs =  statement.executeQuery(sql);

        Flight flight;

        while (rs.next()){

            flightnumber = rs.getInt("flightnumber");
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
            Calendar dep = new GregorianCalendar(Integer.parseInt(depdatesplit[0]),
                    Integer.parseInt(depdatesplit[1])-1,
                    Integer.parseInt(depdatesplit[2]),
                    Integer.parseInt(depsplit [0]),
                    Integer.parseInt(depsplit[1]) );
            Calendar arr = new GregorianCalendar(Integer.parseInt(arrdatesplit[0]),
                    Integer.parseInt(arrdatesplit[1])-1,
                    Integer.parseInt(arrdatesplit[2]),
                    Integer.parseInt(arrsplit [0]),
                    Integer.parseInt(arrsplit[1]) );

            flight = new Flight(flightnumber, airport1, airport2, price, dep, arr, available, childdiscount);
            return new Booking(id, passengerlist, flight, comment);
        }
        return null;

    }
    public int addBooking(Booking booking) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        String sql = "insert into bookings (flightnumber, comment, passengers) values (";
        String passengers = "'";
        for (Passenger passenger : booking.getPassengers()){
            passengers += passenger.getKennitala() + "," + passenger.getName();
            passengers += ";";
        }
        passengers += "'";
        int flightnumber = booking.getFlight().getFlightNumber();
        String comment = "'" + booking.getComment() + "'";
        sql += flightnumber + "," + comment + "," + passengers + ")";
        statement.execute(sql);
        sql = "select bookingid from bookings where passengers = " + passengers  + "and comment = " + comment;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            return rs.getInt("bookingid");
        }
        return -1;
    }

    public static void main(String[] args){

    }
}

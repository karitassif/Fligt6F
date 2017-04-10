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


    /* Returns a Connection to database

     */
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


    /* searches database for booking with given bookingID

     */
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
            /* passengers are formatted with (kennitala1, name1;kennitala2, name2; ...)

              */
            for (int i = 0; i < passengers.length; i++){
                String[] passengerinfo = passengers[i].split(",");
                Passenger passenger = new Passenger(passengerinfo[0], passengerinfo[1]);
                passengerlist.add(passenger);
            }
        }
        /* searches flight connected with given booking

         */
        sql = "select * from flights where flightnumber =" + flightnumber;
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

            //month goes from 0 to 11 so we subtract 1 from month
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
        /*if no such booking return null?

         */
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
        /* returns bookingID from primary key in database

         */
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

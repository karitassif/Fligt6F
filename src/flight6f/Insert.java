package flight6f;

import edu.princeton.cs.algs4.*;

import java.sql.*;

/**
 * Created by atlim on 9.4.2017.
 */
public class Insert {


    public static Connection connect(){
        String url = "jdbc:postgresql://localhost/FLIGHT6F";
        String user = "postgres";
        String password = ".idea/Flight6f";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Succesfull");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public static void main(String[] args) throws SQLException {
        /*

        Connection conn = connect();
        Statement statement = conn.createStatement();
        String sql = "select * from flights where depdate = '31.12.2017'";
        ResultSet rs = statement.executeQuery(sql);

        flight6f.Flight flight;

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

            System.out.println(depdate);

            flight6f.Airport airport1 = new flight6f.Airport(depcity, depcountry, depcode);
            flight6f.Airport airport2 = new flight6f.Airport(arrcity, arrcountry, arrcode);

            //month goes from 0 to 11 so we subtract 1 from month
            Calendar dep = new GregorianCalendar(Integer.parseInt(depdatesplit[0]),
                    Integer.parseInt(depdatesplit[1]) - 1,
                    Integer.parseInt(depdatesplit[2]),
                    Integer.parseInt(depsplit[0]),
                    Integer.parseInt(depsplit[1]));
            Calendar arr = new GregorianCalendar(Integer.parseInt(arrdatesplit[0]),
                    Integer.parseInt(arrdatesplit[1]) - 1,
                    Integer.parseInt(arrdatesplit[2]),
                    Integer.parseInt(arrsplit[0]),
                    Integer.parseInt(arrsplit[1]));

            flight = new flight6f.Flight(flightnumber, airport1, airport2, price, dep, arr, available, childdiscount);
            System.out.println(flight.getFormattedDepTime());
        }

        */



        In in = new In("flugvellir.txt");
        String[] lines = in.readAllLines();
        int n = lines.length;
        String[] codes = new String[n];
        String[] cities = new String[n];
        String[] countries = new String[n];
        String[] dep1 = new String[n];
        String[] arr1 = new String[n];
        String[] dep2 = new String[n];
        String[] arr2 = new String[n];

        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(";");
            codes[i] = line[0];
            cities[i] = line[1];
            countries[i] = line[2];
            dep1[i] = line[3];
            arr1[i] = line[4];
            dep2[i] = line[5];
            arr2[i] = line[6];
        }
        /*
        for (int i = 0; i < codes.length; i++){
            String airport = "";
            airport += codes[i] + " ";
            airport += cities[i] + " ";
            airport += countries[i] + " ";
            airport += dep1[i] + " ";
            airport += arr1[i] + " ";
            airport += dep2[i] + " ";
            airport += arr2[i] + " ";
            StdOut.println(airport);
        }
        */



        Connection conn = connect();
        Statement statement = conn.createStatement();
        String sql1 = "insert into flights (depcode, depcity, depcountry, arrcode," +
                      "arrcity, arrcountry, price, availableseats, childdiscount, deptime, arrtime, depdate, arrdate)";
        String sql2 = "insert into flights (arrcode, arrcity, arrcountry, depcode," +
                "depcity, depcountry, price, availableseats, childdiscount, deptime, arrtime, depdate, arrdate)";
        Days day = new Days(10,4,2017);
        for (int i = 0; i < 730; i++){
            for (int j = 0; j < codes.length; j++){
                int price1 = (int)(Math.random()*80000) + 15000;
                int seats1 = (int)(Math.random()*200);
                int price2 = (int)(Math.random()*800000) + 15000;
                int seats2 = (int)(Math.random()*200);
                String deptime1 = "'" + dep1[j] + "'";
                String arrtime1 = "'" + arr1[j] + "'";
                String deptime2 = "'" + dep2[j] + "'";
                String arrtime2 = "'" + arr2[j] + "'";
                String depdate1 = "'" + day.toString() + "'";
                String arrdate1 = "'" + day.toString() + "'";
                String sql3 = "values ('KEF','Keflavik', 'Iceland', " + codes[j] + "," +
                               cities[j] + "," + countries[j] + ", " + price1 + "," + seats1 + "," +
                                       0.7 + "," + deptime1 + "," + arrtime1 +
                                       "," + depdate1 + "," + arrdate1 + ")";
                String sql4 = "values ('KEF','Keflavik', 'Iceland', " + codes[j] + "," +
                        cities[j] + "," + countries[j] + ", " + price2 + "," + seats2 + "," +
                        0.7 + "," + deptime2 + "," + arrtime2 + "," + depdate1 + "," + arrdate1 + ")";

                String query1 = sql1 + sql3;
                String query2 = sql2 + sql4;
                statement.execute(query1);
                statement.execute(query2);
            }
            day.increment();
        }

        conn.close();

    }

}

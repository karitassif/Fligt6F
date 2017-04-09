import edu.princeton.cs.algs4.*;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by atlim on 9.4.2017.
 */
public class Insert {


    public static Connection connect(){
        String url = "jdbc:postgresql://localhost/FLIGHT6F";
        String user = "postgres";
        String password = "Flight6f";
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
                      "arrcity, arrcountry, price, availableseats, childdiscount, deptime, arrtime)";
        String sql2 = "insert into flights (arrcode, arrcity, arrcountry, depcode," +
                "depcity, depcountry, price, availableseats, childdiscount, deptime, arrtime)";
        Days day = new Days(20,5,2017);
        for (int i = 0; i < 365; i++){
            for (int j = 0; j < codes.length; j++){
                int price1 = (int)(Math.random()*100000);
                int seats1 = (int)(Math.random()*200);
                int price2 = (int)(Math.random()*100000);
                int seats2 = (int)(Math.random()*200);
                String depart1 = "'" + day.toString() + "." + dep1[j] + "'";
                String arriv1 = "'" +  day.toString() + "." + arr1[j] + "'";
                String depart2 = "'" + day.toString() + "." + dep2[j] + "'";
                String arriv2 = "'" +  day.toString() + "." + arr2[j] + "'";
                String sql3 = "values ('KEF','Keflavik', 'Iceland', " + codes[j] + "," +
                               cities[j] + "," + countries[j] + ", " + price1 + "," + seats1 + "," +
                                       0.7 + "," + depart1 + "," + arriv1 + ")";
                String sql4 = "values ('KEF','Keflavik', 'Iceland', " + codes[j] + "," +
                        cities[j] + "," + countries[j] + ", " + price2 + "," + seats2 + "," +
                        0.7 + "," + depart2 + "," + arriv2 + ")";

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

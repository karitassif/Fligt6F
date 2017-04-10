import java.sql.*;

/**
 * Created by atlim on 10.4.2017.
 */
public class Datetest {



    private static Connection connect(){
        String url = "jdbc:postgresql://localhost/FLIGHT6F";
        String user = "postgres";
        String password = "Flight6f";
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

    public static void main(String[] args) throws SQLException {
        Connection conn = connect();
        Statement statement = conn.createStatement();
        String sql = "select date from dates";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString("date"));
        }
    }
}

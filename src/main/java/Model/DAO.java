package Model;

import Entity.DataObject;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/StockData";

    //Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "Minh1592";

    public void layDataTheoMa(String date, String maSan, String tenSan) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql = "SELECT `<Ticker>` FROM StockData.`CafeF.HNX.02.11.2020`";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
//                List<String> tickerList = new ArrayList<String>();
//                tickerList.add(rs.getNString("<Ticker>"));
                System.out.println(rs.getNString("<Ticker>"));
            }
            System.out.println();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

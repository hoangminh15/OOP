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

    public void layDataTheoMa(String date, String maSan, String maCoPhieu) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            //Query theo ngay - san - ma
            String nam = date.substring(0, 4);
            String thang = date.substring(4, 6);
            String ngay = date.substring(6);
            if (Integer.valueOf(ngay) < 10){
                ngay = "0" + ngay;
            }

            System.out.println(ngay + " " + thang + " " + nam);
            String sql = "SELECT * FROM StockData.`CafeF." + maSan + "." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + maCoPhieu +"'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
//                List<String> tickerList = new ArrayList<String>();
//                tickerList.add(rs.getNString("<Ticker>"));
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
                System.out.println(rs.getString(6));
                System.out.println(rs.getString(7));
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

package Model;

import Entity.DataTheoMa;

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

    public DataTheoMa layDataTheoMa(String date, String maSan, String maCoPhieu) throws Exception {

        Connection connection = null;
        Statement statement = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        //Query theo ngay - san - ma
        String nam = date.substring(0, 4);
        String thang = date.substring(4, 6);
        String ngay = date.substring(6);
        if (Integer.valueOf(ngay) < 10) {
            ngay = "0" + ngay;
        }

        System.out.println(ngay + " " + thang + " " + nam);
        String sql = "SELECT * FROM StockData.`CafeF." + maSan + "." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + maCoPhieu + "'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String ticker = rs.getString("<Ticker>");
        String dateTime = rs.getString("<DTYYYYMMDD>");
        double open = Double.parseDouble(rs.getString("<Open>"));
        double close = Double.parseDouble(rs.getString("<Close>"));
        double high = Double.parseDouble(rs.getString("<High>"));
        double low = Double.parseDouble(rs.getString("<Low>"));
        double volume = Double.parseDouble(rs.getString("<Volume>"));
        return new DataTheoMa(ticker, dateTime, open, high, low, close, volume);

    }
}

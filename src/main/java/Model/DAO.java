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

    Connection connection = null;
    Statement statement = null;

    String ticker;
    String dateTime;
    double open;
    double close;
    double high;
    double low;
    double volume;


    //Lay data the theo ma, san va ngay
    public DataTheoMa layDataTheoMa(String date, String maSan, String maCoPhieu) throws Exception {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        //Query theo ngay - san - ma
        String nam = date.substring(0, 4);
        String thang = date.substring(4, 6);
        String ngay = date.substring(6);
        if (Integer.valueOf(ngay) < 10) {
            ngay = "0" + ngay;
        }

        String sql = "SELECT * FROM StockData.`CafeF." + maSan + "." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + maCoPhieu + "'";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        extractFromResultSet(rs);
        return new DataTheoMa(ticker, dateTime, open, high, low, close, volume);
    }


    //Tách dữ liệu từ bộ k quả tránh lặp code giữa các method lấy data
    public void extractFromResultSet(ResultSet rs) throws Exception {
        ticker = rs.getString("<Ticker>");
        dateTime = rs.getString("<DTYYYYMMDD>");
        open = Double.parseDouble(rs.getString("<Open>"));
        close = Double.parseDouble(rs.getString("<Close>"));
        high = Double.parseDouble(rs.getString("<High>"));
        low = Double.parseDouble(rs.getString("<Low>"));
        volume = Double.parseDouble(rs.getString("<Volume>"));
    }

    //Lấy data các cổ phiểu bluechip
    public ArrayList<DataTheoMa> layDataBluechip(String date) throws Exception {
        var bluechipList = new String[]{"VNM", "VCB", "VIC", "FPT", "MWG", "VJC", "HPG", "DHG", "SAB", "MBB", "BID", "POW"};
        var bluechipObjectList = new ArrayList<DataTheoMa>();

        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        statement = connection.createStatement();

        //Query bluechip theo ngay
        //Optimize đoạn code này tránh việc copy paste code nhiều lần...
        String nam = date.substring(0, 4);
        String thang = date.substring(4, 6);
        String ngay = date.substring(6);
        if (Integer.valueOf(ngay) < 10) {
            ngay = "0" + ngay;
        }
        int listLength = bluechipList.length;
        for (int i = 0; i < listLength; i++) {
            String sql = "SELECT * FROM StockData.`CafeF.HSX." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + bluechipList[i] + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            extractFromResultSet(rs);
            bluechipObjectList.add(new DataTheoMa(ticker, date, open, high, low, close, volume));
        }
        return bluechipObjectList;
    }
}

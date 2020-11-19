package DAO;

import Entity.DataTheoMa;
import javafx.scene.control.Alert;

import java.sql.*;

public class DataGetter {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/StockData";

    //Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "12345678";

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    String ticker;
    String dateTime;
    double open;
    double close;
    double high;
    double low;
    double volume;

    String nam;
    String ngay;
    String thang;

    public void thietLapKetNoi() {

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText("Can't establish connection with database");
            alert.setContentText("Please try again!");
        }

    }

    //Tách dữ liệu từ bộ k quả tránh lặp code giữa các method lấy data
    public void setFromResultSet(ResultSet rs) throws Exception {
        ticker = rs.getString("<Ticker>");
        dateTime = rs.getString("<DTYYYYMMDD>");
        open = Double.parseDouble(rs.getString("<Open>"));
        close = Double.parseDouble(rs.getString("<Close>"));
        high = Double.parseDouble(rs.getString("<High>"));
        low = Double.parseDouble(rs.getString("<Low>"));
        volume = Double.parseDouble(rs.getString("<Volume>"));
    }



}

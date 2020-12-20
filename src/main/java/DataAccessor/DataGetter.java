package DataAccessor;

import DataService.DataTheoMaFetcher;
import DataService.DataTheoMaNhieuNgayFetcher;
import Entity.Data;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.List;

public class DataGetter {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/StockData";

    //Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "Minh1592";


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

    //Strategy Pattern
    DataTheoMaFetcher dataTheoMaFetcher;
    DataTheoMaNhieuNgayFetcher dataTheoMaNhieuNgayFetcher;

    public void setDataTheoMaFetcher(DataTheoMaFetcher dataTheoMaFetcher) {
        this.dataTheoMaFetcher = dataTheoMaFetcher;
    }

    public Data thucHienLayDataTheoMa(String namThangNgay, String maSan, String maCoPhieu){
        Data data = null;
        try {
            data = dataTheoMaFetcher.layDataTheoMa(namThangNgay, maSan, maCoPhieu);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setDataTheoMaNhieuNgayFetcher(DataTheoMaNhieuNgayFetcher dataTheoMaNhieuNgayFetcher) {
        this.dataTheoMaNhieuNgayFetcher = dataTheoMaNhieuNgayFetcher;
    }

    public List<Data> thucHienLayDataTheoMaNhieuNgay(String namThangNgay, String maSan, String maCoPhieu){
        List<Data> dataList;
        dataList = dataTheoMaNhieuNgayFetcher.layDataTheoMaNhieuNgay(namThangNgay, maSan, maCoPhieu);
        return dataList;
    }

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
}

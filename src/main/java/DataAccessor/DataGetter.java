package DataAccessor;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DataGetter {
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

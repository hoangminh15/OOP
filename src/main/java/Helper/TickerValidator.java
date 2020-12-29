package Helper;

import DataService.MySQLConnection;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TickerValidator{
    //Kiem tra xem ma co phieu lieu co thuoc san chung khoan hay khong
    public boolean checkExistence(String maCoPhieu, String maSan) {

        boolean isExisting = true;
        String sql = "SELECT * FROM StockData.`CafeF." + maSan + ".02.11.2020` WHERE `<Ticker>` = '" + maCoPhieu + "'";
        try {
            Connection connection = MySQLConnection.getMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.next())
                isExisting = false;
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Bạn hãy nhập đầy đủ thông tin");
            alert.show();
        }
        return isExisting;
    }
}

package DataAccessor;

import javafx.scene.control.Alert;

public class TickerValidator extends DataGetter {

    boolean isExisting = true;

    public TickerValidator() {
        thietLapKetNoi();
    }

    //Kiem tra xem ma co phieu lieu co thuoc san chung khoan hay khong
    public boolean checkExistence(String maCoPhieu, String maSan) {
        String sql = "SELECT * FROM StockData.`CafeF." + maSan + ".02.11.2020` WHERE `<Ticker>` = '" + maCoPhieu + "'";
        try {
            rs = statement.executeQuery(sql);
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

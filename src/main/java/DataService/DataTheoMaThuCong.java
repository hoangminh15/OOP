package DataService;

import Entity.Data;
import Entity.DataThuCong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataTheoMaThuCong implements DataTheoMaFetcher{
    Data data;
    String nam;
    String thang;
    String ngay;
    String ticker;
    String dateTime;
    double open;
    double close;
    double high;
    double low;
    double volume;

    @Override
    public Data layDataTheoMa(String date, String maSan, String maCoPhieu) throws SQLException, ClassNotFoundException {
        Connection connection = MySQLConnection.getMySQLConnection();
        Statement statement = connection.createStatement();
        formatDate(date);
        String sql = "SELECT * FROM StockData.`CafeF." + maSan + "." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + maCoPhieu + "'";
        try {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            setFromResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = new DataThuCong(ticker, dateTime, open, high, low, close, volume);
        return data;
    }

    public void formatDate(String date) {
        nam = date.substring(0, 4);
        thang = date.substring(4, 6);
        ngay = date.substring(6);
        if (Integer.valueOf(ngay) < 10) {
            ngay = "0" + ngay;
        }
        if (Integer.valueOf(thang) < 10){
            thang = "0" + thang;
        }
    }

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

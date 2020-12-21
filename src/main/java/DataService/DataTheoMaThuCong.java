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
    String date;
    String maCoPhieu;
    double giaMoCua;
    double giaCaoNhat;
    double giaThapNhat;
    double giaDongCua;
    double klgdKhopLenh;

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
        data = new DataThuCong(maCoPhieu, date, giaMoCua, giaCaoNhat, giaThapNhat, giaDongCua, klgdKhopLenh);
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
        maCoPhieu = rs.getString("<Ticker>");
        date = rs.getString("<DTYYYYMMDD>");
        giaMoCua = Double.parseDouble(rs.getString("<Open>"));
        giaDongCua = Double.parseDouble(rs.getString("<Close>"));
        giaCaoNhat = Double.parseDouble(rs.getString("<High>"));
        giaThapNhat = Double.parseDouble(rs.getString("<Low>"));
        klgdKhopLenh = Double.parseDouble(rs.getString("<Volume>"));
    }
}

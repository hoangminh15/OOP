package DataService;

import Entity.Data;
import Entity.DataThuCong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBluechipThuCong implements DataBluechipFetcher {
    String ngay;
    String thang;
    String nam;
    String date;
    String maCoPhieu;
    double giaMoCua;
    double giaCaoNhat;
    double giaThapNhat;
    double giaDongCua;
    double klgdKhopLenh;

    @Override
    public List<Data> layDataBlueChip(String date) {
        var bluechipList = new String[]{"VNM", "VCB", "VIC", "FPT", "MWG", "VJC", "HPG", "DHG", "SAB", "MBB", "BID", "POW"};
        List<Data> bluechipObjectList = new ArrayList<>();
        formatDate(date);
        //Query bluechip theo ngay
        int listLength = bluechipList.length;
        try {
            for (int i = 0; i < listLength; i++) {
                Connection connection = MySQLConnection.getMySQLConnection();
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM StockData.`CafeF.HSX." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + bluechipList[i] + "'";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                setFromResultSet(rs);
                Data data = new DataThuCong(maCoPhieu, date, giaMoCua, giaCaoNhat, giaThapNhat, giaDongCua, klgdKhopLenh);
                bluechipObjectList.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bluechipObjectList;
    }

    public void formatDate(String date) {
        nam = date.substring(0, 4);
        thang = date.substring(4, 6);
        ngay = date.substring(6);
        if (Integer.valueOf(ngay) < 10) {
            ngay = "0" + ngay;
        }
        if (Integer.valueOf(thang) < 10) {
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

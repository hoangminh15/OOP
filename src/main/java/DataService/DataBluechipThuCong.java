package DataService;

import Entity.Data;
import Entity.DataThuCong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBluechipThuCong implements DataBluechipFetcher{
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
                Data data = new DataThuCong(ticker, date, open, high, low, close, volume);
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

package DataService;

import Entity.Data;
import Entity.DataThuCong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataTheoMaNhieuNgayThuCong implements DataTheoMaNhieuNgayFetcher{
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

    @Override
    public List<Data> layDataTheoMaNhieuNgay(String namThangNgay, String maSan, String maCoPhieu) {
        ArrayList<String> listTables= new ArrayList<>();
        listTables.add("CafeF.HNX.02.11.2020");
        listTables.add("CafeF.HNX.03.11.2020");
        listTables.add("CafeF.HNX.04.11.2020");
        listTables.add("CafeF.HNX.29.10.2020");
        listTables.add("CafeF.HNX.30.10.2020");
        listTables.add("CafeF.HSX.02.11.2020");
        listTables.add("CafeF.HSX.03.11.2020");
        listTables.add("CafeF.HSX.30.10.2020");

        List<Data> listData = new ArrayList<>();
        for (String item: listTables) {
            if(item.contains(maSan)){
                String sql = "SELECT * FROM StockData.`" + item +"` WHERE `<Ticker>` = '" + maCoPhieu + "'";
                try {
                    Connection connection = MySQLConnection.getMySQLConnection();
                    connection.createStatement();
                    rs = statement.executeQuery(sql);
                    rs.next();
                    setFromResultSet(rs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Data data = new DataThuCong(ticker, dateTime, open, high, low, close, volume);
                listData.add(data);
            }
        }
        return listData;
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

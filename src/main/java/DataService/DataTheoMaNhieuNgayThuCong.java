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
    String date;
    String maCoPhieu;
    double giaMoCua;
    double giaCaoNhat;
    double giaThapNhat;
    double giaDongCua;
    double klgdKhopLenh;


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
                Data data = new DataThuCong(maCoPhieu, date, giaMoCua, giaCaoNhat, giaThapNhat, giaDongCua, klgdKhopLenh);
                listData.add(data);
            }
        }
        return listData;
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

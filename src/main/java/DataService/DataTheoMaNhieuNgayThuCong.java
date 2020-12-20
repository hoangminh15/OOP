package DataService;

import Entity.Data;
import Entity.DataRealtime;
import Entity.DataThuCong;
import Helper.Find7LatestDay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataTheoMaNhieuNgayThuCong implements DataTheoMaNhieuNgayFetcher{
    private String maCoPhieu;
    private double giaDongCua;
    private String thayDoi;
    private double giaThamChieu;
    private double giaMoCua;
    private double giaCaoNhat;
    private double giaThapNhat;
    private long klgdKhopLenh;
    private long gtgdKhopLenh;

    private String nam;
    private String ngay;
    private String thang;
    private String maSan;
    Statement statement;
    ResultSet rs;
    @Override
    public List<Data> layDataTheoMaNhieuNgay(String namThangNgay, String maSan, String maCoPhieu) {
        try{
            Connection connection = MySQLConnection.getMySQLConnection();
            statement = connection.createStatement();

        } catch (Exception e){
            e.printStackTrace();
        }
        //Convert maSan cho phu hop voi database
        maSan = validateMaSan(maSan);

        String nam = namThangNgay.substring(0, 4);
        String thang = namThangNgay.substring(4, 6);
        String ngay = namThangNgay.substring(6);
        String ngayThangNam = ngay + thang + nam;
        List<String> bayNgayGanNhat = new Find7LatestDay().lay7NgayGanNhat(ngayThangNam);
        String finalMaSan = maSan;
        //Chuyen ma san tu dang 02122020 -> HOSE02122020
        List<String> listTables = bayNgayGanNhat.stream().map(NTN -> finalMaSan + NTN).collect(Collectors.toList());

        //List chua data duoc tra ve
        List<Data> listData = new ArrayList<>();
        for (String table: listTables) {
            if(table.contains(maSan)){
                String sql = "SELECT * FROM StockData.`" + table +"` WHERE maCoPhieu = '" + maCoPhieu + "'";
                try {
                    rs = statement.executeQuery(sql);
                    rs.next();
                    setFromResultSet(rs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Data data = new DataRealtime(maCoPhieu,  giaDongCua,  thayDoi,  giaThamChieu,  giaMoCua,  giaCaoNhat,  giaThapNhat,  klgdKhopLenh,  gtgdKhopLenh);
                listData.add(data);
            }
        }
        return listData;
    }

    public String validateMaSan(String maSan){
        if (maSan.equals("HSX")){
            maSan = "HOSE";
        } else if(maSan.equals("HNX")){
            maSan = "HASTC";
        } else if(maSan.equals("VN30")){
            maSan = "VN30";
        }
        return maSan;
    }

    public void setFromResultSet(ResultSet rs) throws Exception {
        maCoPhieu = rs.getString("maCoPhieu");
        giaDongCua = Double.parseDouble(rs.getString("giaDongCua"));
        thayDoi = rs.getString("thayDoi");
        giaThamChieu = Double.parseDouble(rs.getString("giaThamChieu"));
        giaMoCua = Double.parseDouble(rs.getString("giaMoCua"));
        giaCaoNhat = Double.parseDouble(rs.getString("giaCaoNhat"));
        giaThapNhat = Double.parseDouble(rs.getString("giaThapNhat"));
        klgdKhopLenh = Long.parseLong(rs.getString("klgdKhopLenh"));
        gtgdKhopLenh = Long.parseLong(rs.getString("gtgdKhopLenh"));
    }

}

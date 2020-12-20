package DataService;

import Entity.Data;
import Entity.DataRealtime;
import Entity.DataThuCong;
import Helper.Find7LatestDay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataTheoMaNhieuNgayRealtime implements DataTheoMaNhieuNgayFetcher {
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
    DataTheoMaRealtime dataTheoMaRealtime;

    public DataTheoMaNhieuNgayRealtime() {
        dataTheoMaRealtime = new DataTheoMaRealtime();
    }

    @Override
    public List<Data> layDataTheoMaNhieuNgay(String namThangNgay, String maSan, String maCoPhieu) {
        try {
            Connection connection = MySQLConnection.getMySQLConnection();
            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Convert maSan cho phu hop voi database
//        maSan = validateMaSan(maSan);

        String nam = namThangNgay.substring(0, 4);
        String thang = namThangNgay.substring(4, 6);
        String ngay = namThangNgay.substring(6);
        //Chuyen tu dang 1122020 sang 01122020
        if (!ngay.startsWith("0")) {
            if (Integer.parseInt(ngay) < 10) {
                ngay = "0" + ngay;
            }
        }
        if (Integer.parseInt(thang) < 10) {
            thang = "0" + thang;
        }
        //Lay 7 ngay gan nhat dang 02112020
        String ngayThangNam = ngay + thang + nam;
        List<String> bayNgayGanNhat = new Find7LatestDay().lay7NgayGanNhat(ngayThangNam);

        String finalMaSan = maSan;
//        //Chuyen ma san tu dang 02122020 -> HOSE02122020
//        List<String> listTables = bayNgayGanNhat.stream().map(NTN -> finalMaSan + NTN).collect(Collectors.toList());

        //List chua data duoc tra ve
        List<Data> listData = new ArrayList<>();
        for (String ngayTrongThang : bayNgayGanNhat) {
            Data data = null;
            try {
                System.out.println("Table thi sao: " + ngayTrongThang);
                String ngayToPass = ngayTrongThang.substring(0, 2);
                String thangToPass = ngayTrongThang.substring(2, 4);
                String namToPass = ngayTrongThang.substring(4);
                data = dataTheoMaRealtime.layDataTheoMa(namToPass + thangToPass + ngayToPass, maSan, maCoPhieu);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            listData.add(data);

        }
        return listData;
    }


    public String validateMaSan(String maSan) {
        if (maSan.equals("HSX")) {
            maSan = "HOSE";
        } else if (maSan.equals("HNX")) {
            maSan = "HASTC";
        } else if (maSan.equals("VN30")) {
            maSan = "VN30";
        }
        return maSan;
    }

//    public void setFromResultSet(ResultSet rs) throws Exception {
//        maCoPhieu = rs.getString("maCoPhieu");
//        giaDongCua = Double.parseDouble(rs.getString("giaDongCua"));
//        thayDoi = rs.getString("thayDoi");
//        giaThamChieu = Double.parseDouble(rs.getString("giaThamChieu"));
//        giaMoCua = Double.parseDouble(rs.getString("giaMoCua"));
//        giaCaoNhat = Double.parseDouble(rs.getString("giaCaoNhat"));
//        giaThapNhat = Double.parseDouble(rs.getString("giaThapNhat"));
//        klgdKhopLenh = Long.parseLong(rs.getString("klgdKhopLenh"));
//        gtgdKhopLenh = Long.parseLong(rs.getString("gtgdKhopLenh"));
//    }

}

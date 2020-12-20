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
    Statement statement;
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

        //List chua data duoc tra ve
        List<Data> listData = new ArrayList<>();
        for (String ngayTrongThang : bayNgayGanNhat) {
            Data data = null;
            try {
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
}

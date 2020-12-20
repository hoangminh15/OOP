package DataService;

import DataAccessor.DataGetter;
import Entity.Data;
import Entity.DataThuCong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBluechipRealtime implements DataBluechipFetcher {
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

    @Override
    public List<Data> layDataBlueChip(String date) {
        var bluechipList = new String[]{"VNM", "VCB", "VIC", "FPT", "MWG", "VJC", "HPG", "DHG", "SAB", "MBB", "BID", "POW"};
        List<Data> bluechipObjectList = new ArrayList<>();
        formatDate(date);
        //Query bluechip theo ngay
        int listLength = bluechipList.length;
        try {
            for (int i = 0; i < listLength; i++) {
                DataGetter dataGetter = new DataGetter();
                dataGetter.setDataTheoMaFetcher(new DataTheoMaRealtime());
                Data data = dataGetter.thucHienLayDataTheoMa(nam + thang + ngay, "HSX", bluechipList[i]);
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

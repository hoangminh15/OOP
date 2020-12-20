package DataService;

import Entity.Data;
import Entity.DataRealtime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataTheoMaRealtime implements DataTheoMaFetcher{

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
    //Lấy data phục vụ sinh câu.
    public Data layDataTheoMa(String namThangNgay, String maSanNotConvertedYet, String maCoPhieu) throws SQLException, ClassNotFoundException {
        Connection connection = MySQLConnection.getMySQLConnection();
        statement = connection.createStatement();
        //Chuyen doi ma san nguoi dung nhap vao sang ma san tuong duong. (HSX = HOSE; HNX = HASTC)
        String maSan = "";
        //ex: namThangNgay: 20201109
        nam = namThangNgay.substring(0, 4);
        thang = namThangNgay.substring(4, 6);
        ngay = namThangNgay.substring(6);
        if (maSanNotConvertedYet.equals("HSX")){
            maSan = "HOSE";
        } else if(maSanNotConvertedYet.equals("HNX")){
            maSan = "HASTC";
        } else if(maSanNotConvertedYet.equals("VN30")){
            maSan = "VN30";
        }
        this.maSan = maSan;

        //Lay data
        //Nếu data ngày đó chưa tồn tại thì fetch về và cho vào database
        String querySQL = "SELECT * FROM StockData." + maSan + ngay + thang + nam + " WHERE maCoPhieu = '" + maCoPhieu + "'";
        try {
            rs = statement.executeQuery(querySQL);
            rs.next();
            setFromResultSet(rs);
        } catch (SQLException throwables) {
            //Lay data neu nhu bang chua ton tai
            fetchDataRealtimeAndPutInDatabase(maSan);
            try {
                rs = statement.executeQuery(querySQL);
                rs.next();
                setFromResultSet(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        Data data =  new DataRealtime(maCoPhieu,  giaDongCua,  thayDoi,  giaThamChieu,  giaMoCua,  giaCaoNhat,  giaThapNhat,  klgdKhopLenh,  gtgdKhopLenh, nam + thang + ngay);
        return data;

    }

    //Lay ngay hien tai. Roi loop tu qua khu toi bay gio
    //Tao bang cho moi ngay => insert vao bang.
    public void fetchDataRealtimeAndPutInDatabase(String maSan) {

        String url = "https://s.cafef.vn/TraCuuLichSu2/1/" + maSan + "/" + ngay + "/" + thang + "/" + nam + ".chn";
        try {
            Document document = Jsoup.connect(url).timeout(20000).get();
            //Tao bang de chua data realtime fetch tu tren mang ve theo ngay
            taoBang(maSan, ngay, thang, nam);

            Elements rows = document.select("table#table2sort tr");
            for (Element row : rows) {
                String[] price = row.select("td.Item_Price1").text().split("\\s+");
                if (price.length != 9) continue;
                maCoPhieu = row.select("td.Item_DateItem_lsg").text();
                giaDongCua = Double.parseDouble(price[0]);
                thayDoi = row.select("td.Item_ChangePrice_lsg").text();
                giaThamChieu = Double.parseDouble(price[1]);
                giaMoCua = Double.parseDouble(price[2]);
                giaCaoNhat = Double.parseDouble(price[3]);
                giaThapNhat = Double.parseDouble(price[4]);
                klgdKhopLenh = Long.parseLong(String.join("", price[5].split(",")));
                gtgdKhopLenh = Long.parseLong(String.join("", price[5].split(",")));
                //Them data vao trong bang da tao
                themDataRealtime();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return;
    }

    public void taoBang(String maSan, String ngay, String thang, String nam) throws SQLException {
        String createTableSql = "CREATE TABLE `StockData`.`" + maSan + ngay + thang + nam + "` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `maCoPhieu` VARCHAR(45) NOT NULL,\n" +
                "  `giaDongCua` DECIMAL(10,2) NOT NULL,\n" +
                "  `thayDoi` VARCHAR(45) NOT NULL,\n" +
                "  `giaThamChieu` DECIMAL(10,2) NOT NULL,\n" +
                "  `giaMoCua` DECIMAL(10,2) NOT NULL,\n" +
                "  `giaCaoNhat` DECIMAL(10,2) NOT NULL,\n" +
                "  `giaThapNhat` DECIMAL(10,2) NOT NULL,\n" +
                "  `klgdKhopLenh` VARCHAR(45) NOT NULL,\n" +
                "  `gtgdKhopLenh` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `maCoPhieu_UNIQUE` (`id` ASC) VISIBLE);";
        statement.executeUpdate(createTableSql);
    }

    public void themDataRealtime() throws SQLException {
        String insertSQL = "INSERT INTO `StockData`.`" + maSan + ngay + thang + nam + "` (`id`, `maCoPhieu`, `giaDongCua`, `thayDoi`, `giaThamChieu`, `giaMoCua`, `giaCaoNhat`, `giaThapNhat`, `klgdKhopLenh`, `gtgdKhopLenh`) VALUES (null, '" + maCoPhieu + "', '" + giaDongCua + "', '" + thayDoi + "', '" + giaThamChieu + "', '" + giaMoCua + "', '" + giaCaoNhat + "', '" + giaThapNhat + "', '" + klgdKhopLenh + "', '" + gtgdKhopLenh + "');";
        statement.executeUpdate(insertSQL);
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

    public void formatDate(String ngay, String thang) {
        if(!ngay.startsWith("0")) {
            if (Integer.parseInt(ngay) < 10) {
                this.ngay = "0" + ngay;
            }
        }

        if (Integer.parseInt(thang) < 10) {
            this.thang = "0" + thang;
        }
    }
}

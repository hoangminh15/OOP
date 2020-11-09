package DAO;

import Entity.DataTheoMa;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseGetter extends DataGetter {

    public DatabaseGetter() {
        thietLapKetNoi();
    }

    //Lay data the theo ma, san va ngay
    public DataTheoMa layDataTheoMa(String date, String maSan, String maCoPhieu)  {

        //Query theo ngay - san - ma
        nam = date.substring(0, 4);
        thang = date.substring(4, 6);
        ngay = date.substring(6);
        if (Integer.valueOf(ngay) < 10) {
            ngay = "0" + ngay;
        }

        String sql = "SELECT * FROM StockData.`CafeF." + maSan + "." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + maCoPhieu + "'";
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
            rs.next();
            extractFromResultSet(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Tra ve object chua data
        return new DataTheoMa(ticker, dateTime, open, high, low, close, volume);
    }




    //Lấy data các cổ phiểu bluechip
    public ArrayList<DataTheoMa> layDataBluechip(String date) throws Exception {
        var bluechipList = new String[]{"VNM", "VCB", "VIC", "FPT", "MWG", "VJC", "HPG", "DHG", "SAB", "MBB", "BID", "POW"};
        var bluechipObjectList = new ArrayList<DataTheoMa>();

        //Query bluechip theo ngay
        //Optimize đoạn code này tránh việc copy paste code nhiều lần...
        nam = date.substring(0, 4);
        thang = date.substring(4, 6);
        ngay = date.substring(6);
        if (Integer.valueOf(ngay) < 10) {
            ngay = "0" + ngay;
        }
        int listLength = bluechipList.length;
        for (int i = 0; i < listLength; i++) {
            String sql = "SELECT * FROM StockData.`CafeF.HSX." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + bluechipList[i] + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            extractFromResultSet(rs);
            bluechipObjectList.add(new DataTheoMa(ticker, date, open, high, low, close, volume));
        }
        return bluechipObjectList;
    }

//    public ArrayList<DataTheoMa> layDataTangManh(String date, String maSan){
//        var tangManhObjectList = new ArrayList<DataTheoMa>();
//
//    }
}

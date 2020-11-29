package DataAccessor;

import Entity.DataThuCong;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DatabaseGetter extends DataGetter implements iResultSetter,iDataFetcher{


    public DatabaseGetter() {
        thietLapKetNoi();
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

    //Lay data the theo ma, san va ngay
    public DataThuCong layDataTheoMa(String date, String maSan, String maCoPhieu) {
        //Query theo ngay - san - ma
        formatDate(date);
        String sql = "SELECT * FROM StockData.`CafeF." + maSan + "." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + maCoPhieu + "'";
        try {
            rs = statement.executeQuery(sql);
            rs.next();
            setFromResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Tra ve object chua data
        return new DataThuCong(ticker, dateTime, open, high, low, close, volume);
    }

    public ArrayList<DataThuCong> layDataTheoMaNhieuNgay(String maSan, String maCoPhieu){
        ArrayList<String> listTables= new ArrayList<>();
        listTables.add("CafeF.HNX.02.11.2020");
        listTables.add("CafeF.HNX.03.11.2020");
        listTables.add("CafeF.HNX.04.11.2020");
        listTables.add("CafeF.HNX.29.10.2020");
        listTables.add("CafeF.HNX.30.10.2020");
        listTables.add("CafeF.HSX.02.11.2020");
        listTables.add("CafeF.HSX.03.11.2020");
        listTables.add("CafeF.HSX.30.10.2020");

        ArrayList<DataThuCong> listData = new ArrayList<>();
        for (String item: listTables) {
            if(item.contains(maSan)){
                String sql = "SELECT * FROM StockData.`" + item +"` WHERE `<Ticker>` = '" + maCoPhieu + "'";
                try {
                    rs = statement.executeQuery(sql);
                    rs.next();
                    setFromResultSet(rs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listData.add(new DataThuCong(ticker, dateTime, open, high, low, close, volume));
            }
        }
        return listData;
    }

    // Lấy tất cả data
    public ArrayList<DataThuCong> layAllData(){
        ArrayList<String> listTables= new ArrayList<>();
        listTables.add("CafeF.HNX.02.11.2020");
        listTables.add("CafeF.HNX.03.11.2020");
        listTables.add("CafeF.HNX.04.11.2020");
        listTables.add("CafeF.HNX.29.10.2020");
        listTables.add("CafeF.HNX.30.10.2020");
        listTables.add("CafeF.HSX.02.11.2020");
        listTables.add("CafeF.HSX.03.11.2020");
        listTables.add("CafeF.HSX.30.10.2020");

        ArrayList<DataThuCong> listData = new ArrayList<>();
        for (String item: listTables) {
            String sql = "SELECT * FROM StockData.`" + item +"`";
            String tenSan = "";
            if(item.startsWith("CafeF.HNX")){
                tenSan = "HNX";
            }
            else if (item.startsWith("CafeF.HSX")){
                tenSan = "HSX";
            }
            try {
                rs = statement.executeQuery(sql);
                if(rs.next()){
                    do{
                        setFromResultSet(rs);
                        var dataTheoMa = new DataThuCong(ticker, dateTime, open, high, low, close, volume);
                        dataTheoMa.setSan(tenSan);
                        listData.add(dataTheoMa);
                    }
                    while (rs.next());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listData;
    }

    //Lay data theo group
    public ArrayList<DataThuCong> layDataTheoGroup(String groupName){
        ArrayList<String> listMaDauKhi = new ArrayList<>(
                Arrays.asList("GAS","PLX","PVD","PVT")
        );

        ArrayList<String> listMaNganHang = new ArrayList<>(
                Arrays.asList("STB","VCB","CTG","EIB","MBB","BID","VPB","HDB","TPB","TCB")
        );

        ArrayList<DataThuCong> listData = layAllData();

        if(groupName.equals("Dầu khí")){
            return listData.stream().filter(data -> listMaDauKhi.contains(data.getMaCoPhieu())).collect(Collectors.toCollection(ArrayList::new));
        }
        else if(groupName.equals("Ngân hàng")){
            return listData.stream().filter(data -> listMaNganHang.contains(data.getMaCoPhieu())).collect(Collectors.toCollection(ArrayList::new));
        }
        else{
            return listData;
        }
    }

    //Lấy data các cổ phiểu bluechip
    public ArrayList<DataThuCong> layDataBluechip(String date)  {
        var bluechipList = new String[]{"VNM", "VCB", "VIC", "FPT", "MWG", "VJC", "HPG", "DHG", "SAB", "MBB", "BID", "POW"};
        var bluechipObjectList = new ArrayList<DataThuCong>();
        formatDate(date);
        //Query bluechip theo ngay
        int listLength = bluechipList.length;

        try {
            for (int i = 0; i < listLength; i++) {
                String sql = "SELECT * FROM StockData.`CafeF.HSX." + ngay + "." + thang + "." + nam + "` WHERE `<Ticker>` = '" + bluechipList[i] + "'";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                setFromResultSet(rs);
                bluechipObjectList.add(new DataThuCong(ticker, date, open, high, low, close, volume));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bluechipObjectList;
    }

//    public ArrayList<DataTheoMa> layDataTangManh(String date, String maSan){
//        var tangManhObjectList = new ArrayList<DataTheoMa>();
//
//    }

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

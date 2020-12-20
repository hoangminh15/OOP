package DataAccessor;

import Entity.DataThuCong;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseGetter extends DataGetter {


    public DatabaseGetter() {
        thietLapKetNoi();
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

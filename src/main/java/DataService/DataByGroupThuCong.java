package DataService;

import Entity.Data;
import Entity.DataThuCong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataByGroupThuCong implements DataByGroupFetcher{
    String ticker;
    String dateTime;
    double open;
    double close;
    double high;
    double low;
    double volume;

    // Lấy tất cả data
    public List<Data> layAllData(){
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
        for (String table: listTables) {
            String sql = "SELECT * FROM StockData.`" + table +"`";
            try {
                Connection connection = MySQLConnection.getMySQLConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if(rs.next()){
                    do{
                        setFromResultSet(rs);
                        var dataTheoMa = new DataThuCong(ticker, dateTime, open, high, low, close, volume);
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

    @Override
    //Lay data theo group
    public List<Data> layDataTheoGroup(String groupName){
        ArrayList<String> listMaDauKhi = new ArrayList<>(
                Arrays.asList("GAS","PLX","PVD","PVT")
        );

        ArrayList<String> listMaNganHang = new ArrayList<>(
                Arrays.asList("STB","VCB","CTG","EIB","MBB","BID","VPB","HDB","TPB","TCB")
        );

        List<Data> listData = layAllData();

        if(groupName.equals("Dầu khí")){
            return listData.stream().map(data -> (DataThuCong) data).filter(data -> listMaDauKhi.contains(data.getMaCoPhieu())).collect(Collectors.toCollection(ArrayList::new));
        }
        else if(groupName.equals("Ngân hàng")){
            return listData.stream().map(data -> (DataThuCong) data).filter(data -> listMaNganHang.contains(data.getMaCoPhieu())).collect(Collectors.toCollection(ArrayList::new));
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

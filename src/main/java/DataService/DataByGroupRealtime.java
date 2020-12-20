package DataService;

import DataAccessor.DataGetter;
import Entity.Data;
import Entity.DataThuCong;
import Helper.PreviousWorkingDay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataByGroupRealtime implements DataByGroupFetcher{

    @Override
    //Lay data theo group
    public List<Data> layDataTheoGroup(String groupName){
        //Tim ngay gan nhat khong phai thu 7 + CN
        Date previousWorkingDay = PreviousWorkingDay.getPreviousWorkingDay(new Date());
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String namThangNgay = dateFormat.format(previousWorkingDay);
        DataGetter dataGetter = new DataGetter();
        dataGetter.setDataTheoMaFetcher(new DataTheoMaRealtime());

        String[] maDauKhi = new String[]{"GAS","PLX","PVD","PVT"};
        List<String> listMaDauKhi = Arrays.asList(maDauKhi);
        String[] maNganHang = new String[]{"STB","VCB","CTG","EIB","MBB","BID","VPB","HDB","TPB","TCB"};
        List<String> listMaNganHang = Arrays.asList(maNganHang);

        List<Data> listDataTheoNhom = new ArrayList<>();
        if(groupName.equals("Dầu khí")){
            listMaDauKhi.forEach(maCoPhieu -> {
                Data data = dataGetter.thucHienLayDataTheoMa(namThangNgay, "HSX", maCoPhieu);
                listDataTheoNhom.add(data);
            });
        }
        else if(groupName.equals("Ngân hàng")){
            listMaNganHang.forEach(maCoPhieu -> {
                Data data = dataGetter.thucHienLayDataTheoMa(namThangNgay, "HSX", maCoPhieu);
                listDataTheoNhom.add(data);
            });
        }
        return listDataTheoNhom;
    }
}

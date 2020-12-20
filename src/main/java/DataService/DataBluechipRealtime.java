package DataService;

import DataAccessor.DataGetter;
import Entity.Data;

import Helper.DateFormatter;

import java.util.ArrayList;
import java.util.List;

public class DataBluechipRealtime implements DataBluechipFetcher {
    @Override
    public List<Data> layDataBlueChip(String date) {
        var bluechipList = new String[]{"VNM", "VCB", "VIC", "FPT", "MWG", "VJC", "HPG", "DHG", "SAB", "MBB", "BID", "POW"};
        List<Data> bluechipObjectList = new ArrayList<>();
        //Query bluechip theo ngay
        int listLength = bluechipList.length;
        try {
            for (int i = 0; i < listLength; i++) {
                DataGetter dataGetter = new DataGetter();
                dataGetter.setDataTheoMaFetcher(new DataTheoMaRealtime());
                Data data = dataGetter.thucHienLayDataTheoMa(DateFormatter.formatDate(date), "HSX", bluechipList[i]);
                bluechipObjectList.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bluechipObjectList;
    }
}

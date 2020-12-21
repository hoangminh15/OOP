package DataAccessor;

import DataService.DataBluechipFetcher;
import DataService.DataByGroupFetcher;
import DataService.DataTheoMaFetcher;
import DataService.DataTheoMaNhieuNgayFetcher;
import Entity.Data;

import java.sql.*;
import java.util.List;

public class DataGetter {

    //Strategy Pattern
    DataTheoMaFetcher dataTheoMaFetcher;
    DataTheoMaNhieuNgayFetcher dataTheoMaNhieuNgayFetcher;
    DataBluechipFetcher dataBluechipFetcher;
    DataByGroupFetcher dataByGroupFetcher;

    public void setDataTheoMaFetcher(DataTheoMaFetcher dataTheoMaFetcher) {
        this.dataTheoMaFetcher = dataTheoMaFetcher;
    }

    public Data thucHienLayDataTheoMa(String namThangNgay, String maSan, String maCoPhieu){
        Data data = null;
        try {
            data = dataTheoMaFetcher.layDataTheoMa(namThangNgay, maSan, maCoPhieu);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void setDataTheoMaNhieuNgayFetcher(DataTheoMaNhieuNgayFetcher dataTheoMaNhieuNgayFetcher) {
        this.dataTheoMaNhieuNgayFetcher = dataTheoMaNhieuNgayFetcher;
    }

    public List<Data> thucHienLayDataTheoMaNhieuNgay(String namThangNgay, String maSan, String maCoPhieu){
        List<Data> dataList = dataTheoMaNhieuNgayFetcher.layDataTheoMaNhieuNgay(namThangNgay, maSan, maCoPhieu);
        return dataList;
    }

    public void setDataBluechipFetcher(DataBluechipFetcher dataBluechipFetcher) {
        this.dataBluechipFetcher = dataBluechipFetcher;
    }

    public List<Data> thucHienLayDataBlueChip(String date){
        List<Data> dataList = dataBluechipFetcher.layDataBlueChip(date);
        return dataList;
    }

    public void setDataByGroupFetcher(DataByGroupFetcher dataByGroupFetcher) {
        this.dataByGroupFetcher = dataByGroupFetcher;
    }

    public List<Data> thucHienLayDataTheoGroup(String groupName){
        List<Data> listData = dataByGroupFetcher.layDataTheoGroup(groupName);
        return listData;
    }
}

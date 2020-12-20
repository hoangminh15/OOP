package DataService;

import Entity.Data;

import java.sql.SQLException;
import java.util.List;

public interface DataTheoMaNhieuNgayFetcher {
    List<Data> layDataTheoMaNhieuNgay(String namThangNgay, String maSan, String maCoPhieu) ;
}

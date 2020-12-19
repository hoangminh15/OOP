package DataService;

import Entity.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface DataTheoMaFetcher {
    Data layDataTheoMa(String date, String maSan, String maCoPhieu) throws SQLException, ClassNotFoundException;
}

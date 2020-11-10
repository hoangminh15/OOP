package DAO;

import DAO.DataGetter;

import java.sql.*;

public class TickerValidator extends DataGetter {

    boolean isExisting = true;

    public TickerValidator() {
        thietLapKetNoi();
    }

    public boolean checkExistence(String maCoPhieu, String maSan) {
        String sql = "SELECT * FROM StockData.`CafeF." + maSan + ".02.11.2020` WHERE `<Ticker>` = '" + maCoPhieu + "'";
        try {
            rs = statement.executeQuery(sql);
            if (!rs.next())
                isExisting = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExisting;
    }
}

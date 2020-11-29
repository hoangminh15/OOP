package DataAccessor;

import java.sql.ResultSet;

public interface iResultSetter {
    //Tách dữ liệu từ bộ k quả tránh lặp code giữa các method lấy data
    void setFromResultSet(ResultSet rs) throws Exception;
}

package DataService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

        public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
            String hostName = "localhost";
            String dbName = "QuanLyNhanKhau";
            String userName = "root";
            String password = "Minh1592";
            return getMySQLConnection(hostName, dbName, userName, password);
        }

        public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException{
            String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
            Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
            return conn;
        }

}

package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private  static String url = "jdbc:mysql://localhost/database1";
    private  static String userName = "root";
    private static String password = "1234";

    //public static Statement getStatement () throws SQLException {return  Util.getConnection().createStatement();}
    public  static  Connection getConnection () {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Util.url,userName, password);
        }
        catch (SQLException e) {
            System.out.println("Ошибка при поключении к БД");
        }
        return conn;
    }

}

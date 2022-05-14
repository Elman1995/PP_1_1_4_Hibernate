package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.ArrayList;

public class Util {

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String connectionURL = "jdbc:mysql://localhost:3306/kata";
        String userName = "root";
        String password = "Sesh2001";
        return getMySQLConnection(connectionURL, userName, password);
    }

    public static Connection getMySQLConnection(String connectionURL, String userName, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

}

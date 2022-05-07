package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private Connection connection;

    public Util() throws SQLException, ClassNotFoundException {
        String  userName = "root",
                password = "Sesh2001",
                connectionURL = "jdbc:mysql://localhost:3306/kata";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection connectionP = DriverManager.getConnection(connectionURL, userName, password);
            this.connection = connectionP;
        } catch (SQLException e) {}
    }

    public Connection getConnection() {
        return this.connection;
    }
}

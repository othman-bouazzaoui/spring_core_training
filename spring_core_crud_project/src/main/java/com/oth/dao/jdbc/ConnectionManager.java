package com.oth.dao.jdbc;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ConnectionManager {

    private static String BD_URL = "jdbc:mysql://localhost:3306/spring";
    private static String  BD_USER= "root";
    private static String  BD_PASSWORD = "root";

    private static ConnectionManager connection;

    private ConnectionManager() {
    }

    public static ConnectionManager getConnection() {
        if (connection == null) {
            connection = new ConnectionManager();
        }
        return connection;
    }

    public Connection openConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(BD_URL, BD_USER, BD_PASSWORD);
        } catch (Exception ex) {
            System.err.println("ex = " + ex);
        }
        return con;
    }

    public void closeConnection(Connection connection) {
        try {
            if( null != connection && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception ex) {
            System.err.println("ex = " + ex);
        }
    }
}

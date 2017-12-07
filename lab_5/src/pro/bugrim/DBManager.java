package pro.bugrim;

import java.sql.*;

public class DBManager {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/?user=root&password=root&useSSL=false";
    private Connection connection;

    private void init() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }

        try {
            connection = DriverManager.getConnection(CONN_STRING);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void createDatabase(String name) {
        try {
            String sql = "CREATE DATABASE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
            ps.execute("USE " + name);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

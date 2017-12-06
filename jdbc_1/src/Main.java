import java.sql.*;
import java.util.Enumeration;

public class Main {

    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/?user=root&password=root&useSSL=false";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }

        outDrivers();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONN_STRING);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        if (connection == null) {
            return;
        }
        try {
            ResultSet resultSet = connection.getMetaData().getCatalogs();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TABLE_CAT"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        try {
            Statement st = connection.createStatement();
            int res = st.executeUpdate("CREATE DATABASE db");
            System.out.println("Database db created, " + res + " result");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            Statement st = connection.createStatement();
            st.executeUpdate("USE db");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            Statement st = connection.createStatement();
//            st.executeUpdate("CREATE TABLE persons (id int(11), name varchar(64), surname varchar(64), age int(6))");
//            st.executeUpdate("INSERT INTO persons (id, name, surname, age) VALUES ( 1007, 'Name15', 'Surname3', 45)");

            ResultSet rs = st.executeQuery("SELECT * FROM persons ORDER BY name");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void outDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement());
        }
    }
}

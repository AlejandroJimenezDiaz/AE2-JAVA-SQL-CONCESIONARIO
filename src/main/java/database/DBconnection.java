package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            newConnection();
        }
        return connection;
    }

    private void newConnection() {
        String url = "jdbc:mysql://localhost:3306/"+SchemaDB.DB_NAME;
        try {
            connection = DriverManager.getConnection(url,SchemaDB.USER, SchemaDB.PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error en la conexion: " + e.getMessage());
        }
        System.out.println("Conexion exitosa");
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion");
        } finally {
            connection = null;
            System.out.println("Conexion cerrada");
        }
    }
}


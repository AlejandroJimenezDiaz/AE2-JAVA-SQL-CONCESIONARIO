package dao;

import database.DBconnection;
import database.SchemaDB;
import model.Coche;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CochePasajeDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public CochePasajeDAO() {
        connection = new DBconnection().getConnection();
    }
    public ArrayList<Coche> mostrarCochesValidos() throws SQLException {
        ArrayList<Coche> cochesValidos = new ArrayList<>();

        String query = String.format(
                "SELECT c.id, c.marca, c.modelo, c.cv " +
                        "FROM %s c " +
                        "LEFT JOIN %s p ON c.id = p.idCoche " +
                        "GROUP BY c.id, c.marca, c.modelo, c.cv " +
                        "HAVING COUNT(p.idCoche) < 4",
                SchemaDB.TAB_CH, SchemaDB.TAB_PA
        );

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Coche coche = new Coche(
                        resultSet.getInt("id"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getInt("cv")
                );
                cochesValidos.add(coche);
            }
        }
        return cochesValidos;
    }
    public void mostrarCochesYPasajeros() throws SQLException {
        String query = String.format(
                "SELECT c.id, c.marca, c.modelo, c.cv, p.id AS pasajero_id, p.nombre " +
                        "FROM %s c " +
                        "LEFT JOIN %s p ON c.id = p.idCoche",
                SchemaDB.TAB_CH,  // Tabla de coches
                SchemaDB.TAB_PA   // Tabla de pasajeros
        );

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int cocheId = resultSet.getInt("id");
            String marca = resultSet.getString("marca");
            String modelo = resultSet.getString("modelo");
            int cv = resultSet.getInt("cv");
            int pasajeroId = resultSet.getInt("pasajero_id");
            String pasajeroNombre = resultSet.getString("nombre");

            if (pasajeroId != 0) { // Si hay un pasajero asociado
                System.out.println("Coche ID: " + cocheId + ", Marca: " + marca + ", Modelo: " + modelo + ", CV: " + cv +
                        " | Pasajero ID: " + pasajeroId + ", Nombre: " + pasajeroNombre);
            } else {
                System.out.println("Coche ID: " + cocheId + ", Marca: " + marca + ", Modelo: " + modelo + ", CV: " + cv +
                        " | Sin pasajeros");
            }
        }
    }
    public void eliminarPasajeroDeCoche(int idPasajero, int idCoche) throws SQLException {
        // Consulta para eliminar la relación
        String query = String.format(
                "UPDATE %s SET idCoche = NULL WHERE id = ? AND idCoche = ?",
                SchemaDB.TAB_PA // Tabla de pasajeros
        );

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idPasajero); // Establecer id del coche
        preparedStatement.setInt(2, idCoche); // Establecer id del pasajero
        int filasAfectadas = preparedStatement.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Pasajero eliminado del coche correctamente.");
        } else {
            System.out.println("No se encontró al pasajero en el coche especificado.");
        }
    }
    public void asignarCoche(int idPasajero, int idCoche) throws SQLException {
        // Se utiliza la columna de idCoche para hacer el update, no el id del pasajero
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                SchemaDB.TAB_PA,                // Tabla de pasajeros
                SchemaDB.COL_PA_ID_CO,          // Columna donde guardamos el id del coche
                SchemaDB.COL_PA_ID);            // Condición basada en el id del pasajero

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche); // Asignar el idCoche al pasajero
        preparedStatement.setInt(2, idPasajero); // Identificar al pasajero por su id

        int filasActualizadas = preparedStatement.executeUpdate();

        if (filasActualizadas > 0) {
            System.out.println("El pasajero con id : " + idPasajero + " ha sido asignado al coche con id : " + idCoche);
        } else {
            System.out.println("No se encontró al pasajero con id : " + idPasajero);
        }
    }
    public ArrayList<Pasajero> buscarPasajeroIDCoche(int idCoche) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s=?",
                SchemaDB.TAB_PA,
                SchemaDB.COL_PA_ID_CO);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(SchemaDB.COL_PA_ID);
            String nombre = resultSet.getString(SchemaDB.COL_PA_NAME);
            int edad = resultSet.getInt(SchemaDB.COL_PA_EDAD);
            double peso = resultSet.getDouble(SchemaDB.COL_PA_PESO);
            int idcoche = resultSet.getInt(SchemaDB.COL_PA_ID_CO);
            Pasajero pasajero = new Pasajero(nombre,edad,peso,id,idcoche);
            listaPasajeros.add(pasajero);
        }return listaPasajeros;

    }
}

package dao;

import database.DBconnection;
import database.SchemaDB;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PasajeroDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PasajeroDAO() {
        connection = new DBconnection().getConnection();
    }

    private ArrayList<Pasajero> getResultado(ResultSet datosResultantes) throws SQLException {
        ArrayList<Pasajero> listaResultado = new ArrayList<>();
        while (datosResultantes.next()) {
            int id = resultSet.getInt(SchemaDB.COL_PA_ID);
            String nombre = resultSet.getString(SchemaDB.COL_PA_NAME);
            int edad = resultSet.getInt(SchemaDB.COL_PA_EDAD);
            Double peso = resultSet.getDouble(SchemaDB.COL_PA_PESO);
            int id_coche = resultSet.getInt(SchemaDB.COL_PA_ID_CO);
            listaResultado.add(mapearPasajero(nombre, edad, peso, id, id_coche));
        }
        return listaResultado;
    }

    private Pasajero mapearPasajero(String nombre, int edad, Double peso, int id, int id_coche) {
        return new Pasajero(nombre, edad, peso, id, id_coche);
    }

    public void addPasajero(Pasajero pasajero) throws SQLException {
        preparedStatement = connection.prepareStatement(String.format("INSERT INTO  %s (%s,%s,%s) VALUES (?,?,?)",
                SchemaDB.TAB_PA,
                SchemaDB.COL_PA_NAME,
                SchemaDB.COL_PA_EDAD,
                SchemaDB.COL_PA_PESO
        ));
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setDouble(3, pasajero.getPeso());
        int filasAfectadas = preparedStatement.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Se ha registrado correctamente");
        } else {
            System.out.println("No se ha registrado");
        }
    }

    public void borrarPasajero(int id) throws SQLException {
        String query = String.format("DELETE FROM  %s WHERE %s = ?",
                SchemaDB.TAB_PA,
                SchemaDB.COL_PA_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int filasAfectadas = preparedStatement.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Se ha eliminado correctamente");
        } else {
            System.out.println("No se ha registrado");
        }
    }

    public Pasajero consultarPasajeroID(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s=?",
                SchemaDB.TAB_PA,
                SchemaDB.COL_PA_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Pasajero> pasajeros;
        pasajeros = getResultado(resultSet);
        if (!pasajeros.isEmpty()) {
            return pasajeros.getFirst();
        } else {
            return null;
        }
    }

    public ArrayList<Pasajero> obtenerPasajeros() throws SQLException {

        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_PA);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(SchemaDB.COL_PA_ID);
            String nombre = resultSet.getString(SchemaDB.COL_PA_NAME);
            int edad = resultSet.getInt(SchemaDB.COL_PA_EDAD);
            double peso = resultSet.getDouble(SchemaDB.COL_PA_PESO);
            int idcoche = resultSet.getInt(SchemaDB.COL_PA_ID_CO);
            Pasajero pasajero = new Pasajero(nombre, edad, peso, id, idcoche);
            listaPasajeros.add(pasajero);
        }
        return listaPasajeros;
    }

}

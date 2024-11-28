package dao;

import database.DBconnection;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CochesDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CochesDAO() {
        connection = new DBconnection().getConnection();
    }

    private Coche mapearCoche(int id , String marca, String modelo, int cv, int precio) {
        return new Coche(id , marca, modelo, cv, precio);
    }
    private ArrayList<Coche> getResultado(ResultSet datosResultantes) throws SQLException {
        ArrayList<Coche> listaResultado = new ArrayList<>();
        while (datosResultantes.next()) {
            int id = resultSet.getInt(SchemaDB.COL_CH_ID);
            String marca = resultSet.getString(SchemaDB.COL_CH_MARCA);
            String modelo = resultSet.getString(SchemaDB.COL_CH_MODELO);
            int cv = resultSet.getInt(SchemaDB.COL_CH_CV);
            int precio = resultSet.getInt(SchemaDB.COL_CH_PRE);
            listaResultado.add(mapearCoche(id,marca, modelo, cv, precio));
        }
        return listaResultado;
    }
    public void addCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_CH,
                SchemaDB.COL_CH_MARCA,
                SchemaDB.COL_CH_MODELO,
                SchemaDB.COL_CH_CV,
                SchemaDB.COL_CH_PRE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMarca());
        preparedStatement.setString(2, coche.getModelo());
        preparedStatement.setInt(3, coche.getCv());
        preparedStatement.setInt(4, coche.getPrecio());
        int filasAfectadas = preparedStatement.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Se ha registrado correctamente");
        } else {
            System.out.println("No se ha registrado");
        }
    }
    public void deleteCoche(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE (%s) = (?)",
                SchemaDB.TAB_CH,
                SchemaDB.COL_CH_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int filasAfectadas = preparedStatement.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Se ha registrado correctamente");
        } else {
            System.out.println("No se ha registrado");
        }
    }
    public Coche buscarCocheID(int id) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s=?",
                SchemaDB.TAB_CH,
                SchemaDB.COL_CH_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Coche> coches = getResultado(resultSet);
        if (!coches.isEmpty()) {
            return coches.getFirst();
        }else{
            return null;
        }
    }
    public void modificarCoche(Coche coche) throws SQLException {
        String query = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
                SchemaDB.TAB_CH,
                SchemaDB.COL_CH_MARCA,
                SchemaDB.COL_CH_MODELO,
                SchemaDB.COL_CH_CV,
                SchemaDB.COL_CH_PRE,
                SchemaDB.COL_CH_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMarca());
        preparedStatement.setString(2, coche.getModelo());
        preparedStatement.setInt(3, coche.getCv());
        preparedStatement.setInt(4, coche.getPrecio());
        preparedStatement.setInt(5, coche.getId());
        int filas = preparedStatement.executeUpdate();
        if (filas > 0) {
            System.out.println("Se ha modificado correctamente");
        }else{
            System.out.println("No se ha modificado correctamente");
        }
    }
    public ArrayList<Coche> obtenerCoches() throws SQLException {
        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_CH);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Coche> listaCoches = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(SchemaDB.COL_CH_ID);
            String marca = resultSet.getString(SchemaDB.COL_CH_MARCA);
            String modelo = resultSet.getString(SchemaDB.COL_CH_MODELO);
            int cv = resultSet.getInt(SchemaDB.COL_CH_CV);
            int precio = resultSet.getInt(SchemaDB.COL_CH_PRE);
            Coche coche = new Coche(id , marca, modelo, cv, precio);
            listaCoches.add(coche);
        }
        return listaCoches;
    }
}

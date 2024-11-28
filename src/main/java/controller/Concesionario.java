package controller;

import dao.CochePasajeDAO;
import dao.CochesDAO;
import dao.PasajeroDAO;
import model.Coche;
import model.Pasajero;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Concesionario {
    private CochesDAO cochesDAO;
    private PasajeroDAO pasajeroDAO;
    private CochePasajeDAO cochePasajeDAO;
    private Scanner scanner;

    public Concesionario() {
        cochesDAO = new CochesDAO();
        pasajeroDAO = new PasajeroDAO();

    }

    public void agregarCoche() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese marca del coche");
            String marca = scanner.next();
            System.out.println("Ingrese modelo del coche");
            String modelo = scanner.next();
            System.out.println("Ingrese CV del coche ");
            int cv = scanner.nextInt();
            System.out.println("Ingrese precio del coche");
            int precio = scanner.nextInt();
            cochesDAO.addCoche(new Coche(marca, modelo, cv, precio));
            System.out.println("Coche añadido a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al registrar el coche en la quaery");
        }
    }
    public void borrarCocheID(){
        scanner = new Scanner(System.in);
        System.out.println("Ingrese ID del coche que desea borrar");
        int id = scanner.nextInt();
        System.out.println("Usted quiere borrar el coche con ID = " + id);
        try {
            System.out.println("Indique si o no para realizar la operación");
            String opcion = scanner.next();
            if (opcion.equalsIgnoreCase("si")){
                cochesDAO.deleteCoche(id);
            }else {
                System.out.println("No se ha borrado");
            }
        } catch (SQLException e) {
            System.out.println("Error en la query al borrar el coche" + e);
        }

    }
    public void buscarCocheID(){
        scanner = new Scanner(System.in);
        System.out.println("Ingrese ID del coche que desea buscar");
        int id = scanner.nextInt();
        System.out.println("Estas buscando el coche con ID = " + id);
        try {
            Coche cohe = cochesDAO.buscarCocheID(id);
            if (cohe!=null){
                cohe.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("Error en la query al buscar el coche" + e);
        }
    }
    public void modificarCoche(){
        scanner = new Scanner(System.in);
        System.out.println("Introduzca el id del coche que quiere modificar");
        int id = scanner.nextInt();
        try {
            cochesDAO.buscarCocheID(id);
            System.out.println("¿Este es el coche que deseas modificar? indica SI o NO");
            String opcion = scanner.next();
            if (opcion.equalsIgnoreCase("si")){
                System.out.println("Introduza marca ");
                String marca = scanner.next();
                System.out.println("Introduzca modelo ");
                String modelo = scanner.next();
                System.out.println("Introduzca cv ");
                int cv = scanner.nextInt();
                System.out.println("Introduzca precio ");
                int precio = scanner.nextInt();
                cochesDAO.modificarCoche(new Coche(id,marca,modelo,cv,precio));
            }
        } catch (SQLException e) {
            System.out.println("Error en la query de actualización de datos");
        }
    }
    public void obtenerCoches(){
        try {
            ArrayList<Coche> coches = cochesDAO.obtenerCoches();
            for (Coche coche: coches){
                coche.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("Error en la query de mostrar coches");
        }
    }
    public void agregarPasajero(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese nombre del pasajero");
            String nombre = scanner.next();
            System.out.println("Ingrese edad del pasajero");
            int edad = scanner.nextInt();
            System.out.println("Ingrese peso del pasajero ");
            double peso = scanner.nextDouble();
            pasajeroDAO.addPasajero(new Pasajero(nombre,edad,peso));
            System.out.println("Pasajero añadido a la base de datos");

        } catch (SQLException e) {
            System.out.println("Error en la query añadido");
        }
    }
    public void borrarPasajero(){
        scanner = new Scanner(System.in);
        System.out.println("Introduzca el ID del pasajero que desea borrar");
        int id = scanner.nextInt();
        System.out.println("Estas seguro que deseas borrar al pasajero con id = " + id + " SI o NO");
        String opcion = scanner.next();
        if (opcion.equalsIgnoreCase("si")){
            try {
                pasajeroDAO.borrarPasajero(id);
            } catch (SQLException e) {
                System.out.println("Error en el borrado");
            }
        }
    }
    public void consultarPasajeroID(){
        scanner = new Scanner(System.in);
        System.out.println("Ingrese ID del pasajero que desea buscar");
        int id = scanner.nextInt();
        System.out.println("Estas buscando el pasajero con ID = " + id);
        try {
            Pasajero pasajero = pasajeroDAO.consultarPasajeroID(id);
            if (pasajero!=null){
                pasajero.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("Error en la query al buscar el Pasajero " + e);
        }
    }
    public void obtenerPasajeros(){
        try {
            ArrayList<Pasajero> pasajeros = pasajeroDAO.obtenerPasajeros();
            for (Pasajero pasajero: pasajeros){
                pasajero.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("Error en la query al buscar el Pasajeros ");
        }
    }
    public void mostrarCochesDisponibles(){
        cochePasajeDAO = new CochePasajeDAO();
        try {
            ArrayList<Coche> coches = cochePasajeDAO.mostrarCochesValidos();
            for (Coche coche:coches){
                coche.mostrarDatos2();
            }

        } catch (SQLException e) {
            System.out.println("Error en la query" + e);
        }
    }
    public void asignarCoche(){
        scanner = new Scanner(System.in);
        System.out.println("LISTA DE COCHES DISPONIBLES ");
        mostrarCochesDisponibles();
        System.out.println("INDIQUE EL ID DEL PASAJERO Y EL ID DEL COCHE");
        System.out.println("INDICA EL ID PASAJERO");
        int idPa = scanner.nextInt();
        System.out.println("INDICA EL ID DEL COCHE");
        int idCo = scanner.nextInt();
        try {
            cochePasajeDAO.asignarCoche(idPa,idCo);
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }

    }
    public void eliminarPasajero() {
        // Mostrar los coches y sus pasajeros asociados
        try {
            cochePasajeDAO.mostrarCochesYPasajeros();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el ID del pasajero a eliminar: ");
            int idPasajero = scanner.nextInt();
            System.out.print("Introduce el ID del coche del que se eliminará el pasajero: ");
            int idCoche = scanner.nextInt();

            // Eliminar la relación entre el pasajero y el coche
            cochePasajeDAO.eliminarPasajeroDeCoche(idPasajero, idCoche);
        } catch (SQLException e) {
            System.out.println("error en la query" + e);
        }
    }
    public void buscarPasajerosCoche(){
        scanner = new Scanner(System.in);
        System.out.println("Introduzca id del coche");
        int id = scanner.nextInt();
        int cantidad = 0;
        try {
            ArrayList<Pasajero> pasajerosCoche= cochePasajeDAO.buscarPasajeroIDCoche(id);
            for (Pasajero pasajero: pasajerosCoche){
                cantidad++;
                pasajero.mostrarDatos();
            }
            System.out.println("En el coche con ID " + id+ " van un total de : "+cantidad+" pasajeros");
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }
    }
}

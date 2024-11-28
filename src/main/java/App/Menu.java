package App;

import controller.Concesionario;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Concesionario concesionario;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.concesionario = new Concesionario();
    }
    public void pintarMenu() {
        int opcion;
        do {
            // Dibuja la parte superior del recuadro
            System.out.println("╔══════════════════════════════════════════════════════════════╗");

            // Imprime el contenido del menú dentro del recuadro
            System.out.println("║                         MENÚ PRINCIPAL                       ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.println("║ 1---> Añadir nuevo coche                                     ║");
            System.out.println("║ 2---> Borrar coche por ID                                    ║");
            System.out.println("║ 3---> Consultar coche por ID                                 ║");
            System.out.println("║ 4---> Modificar coche por ID                                 ║");
            System.out.println("║ 5---> Listar coches                                          ║");
            System.out.println("║ 6---> ABRIR MENU PASAJEROS                                   ║");
            System.out.println("║ 7---> Terminar Programa                                      ║");


            // Dibuja la parte inferior del recuadro
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                              ║");
            System.out.println("║                       SELECCIONA OPCIÓN                      ║");
            System.out.println("║                                                              ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            // Recibe la opción del usuario
            opcion = scanner.nextInt();
            scanner.nextLine();

            // Maneja la opción seleccionada
            manejarOpcion(opcion);

        } while (opcion != 7);

        // Mensaje de finalización
        System.out.println("☻☻☻FIN DEL PROGRAMA☻☻☻");
        scanner.close();
    }

    private void manejarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                añadirCoche();
                break;
            case 2:
                borrarCoche();
                break;
            case 3:
                consultarCocheID();
                break;
            case 4:
                modificarCocheID();
                break;
            case 5:
                concesionario.obtenerCoches();
                break;
            case 6 :
                pintarSubMenu();
                break;
            case 7 :
                System.out.println("    ☻☻☻☻☻☻☻☻☻☻☻☻");
                break;
            default:
                System.out.println("→←OPCION NO VALIDA←→.");
                break;
        }
    }

    public void pintarSubMenu() {
        int opcion;
        do {
            // Dibuja la parte superior del recuadro
            System.out.println("╔═══════════════════════════════════════════════════════════════════╗");

            // Imprime el contenido del submenú dentro del recuadro
            System.out.println("║                            MENÚ PASAJEROS                         ║");
            System.out.println("║ 1---> Añadir pasajero                                             ║");
            System.out.println("║ 2---> Eliminar pasajero                                           ║");
            System.out.println("║ 3---> Consultar pasajero por ID                                   ║");
            System.out.println("║ 4---> Listar todos los pasajeros                                  ║");
            System.out.println("║ 5---> Añadir pasajero a coche, se asigna ID pasajero a ID coche   ║");
            System.out.println("║ 6---> Eliminar pasajero de un coche por ID pasajero e ID coche    ║");
            System.out.println("║ 7---> Listar todos los pasajeros de un coche por ID del coche     ║");
            System.out.println("║ 8---> Para Volver al menú Principal teclee 8                      ║");

            // Dibuja la parte inferior del recuadro

            System.out.println("║                                                                   ║");
            System.out.println("║                       SELECCIONA OPCIÓN                           ║");
            System.out.println("║                                                                   ║");
            System.out.println("╚═══════════════════════════════════════════════════════════════════╝");

            // Recibe la opción del usuario
            opcion = scanner.nextInt();
            scanner.nextLine();

            // Maneja la opción seleccionada
            manejarOpcionSubMenu(opcion);

        } while (opcion != 8);

        // Mensaje de vuelta al menú principal
        System.out.println("☻☻☻Vuelta al menú Principal☻☻☻");
    }

    private void manejarOpcionSubMenu(int opcion) {
        switch (opcion) {
            case 1:
                añadirPasajero();
                break;
            case 2:
                borrarPasajero();
                break;
            case 3:
                consultarPasajeroID();
                break;
            case 4:
                obtenerPasajeros();
                break;
            case 5:
                asignarCoche();
                break;
            case 6:
                eliminarPasajeroCoche();
                break;
            case 7:
                listarPasajerosDeUnCoche();
                break;
            default:
                System.out.println("→←OPCION NO VALIDA←→.");
                break;
        }
    }

    private void listarPasajerosDeUnCoche() {
        concesionario.buscarPasajerosCoche();
    }

    private void eliminarPasajeroCoche() {
        concesionario.eliminarPasajero();
    }

    private void asignarCoche() {
        concesionario.asignarCoche();
    }

    private void obtenerPasajeros() {
        concesionario.obtenerPasajeros();
    }

    private void consultarPasajeroID() {
        concesionario.consultarPasajeroID();
    }

    private void borrarPasajero() {
        concesionario.borrarPasajero();
    }

    private void añadirPasajero() {
        concesionario.agregarPasajero();
        
    }

    private void modificarCocheID() {
        concesionario.modificarCoche();
    }

    private void consultarCocheID() {
        concesionario.buscarCocheID();
    }

    private void borrarCoche() {
        concesionario.borrarCocheID();
    }

    private void añadirCoche() {
        concesionario.agregarCoche();
    }

}

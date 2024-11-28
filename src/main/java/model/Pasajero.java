package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Pasajero {

    private String nombre;
    private int edad;
    private double peso;
    private int id;
    private int idcoche;


    public Pasajero(String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public void mostrarDatos(){
        System.out.println("Id: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Peso: " + peso);
        System.out.println("Coche: " + idcoche);
        System.out.println("----------------------------");
    }
}

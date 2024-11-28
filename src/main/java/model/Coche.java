package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Coche {
    private int id;
    private String  marca, modelo;
    private int cv, precio;

    public Coche(String marca, String modelo, int cv, int precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
        this.precio = precio;
    }

    public Coche(int id, String marca, String modelo, int cv) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
    }

    public void mostrarDatos (){
        System.out.println("ID :" + id);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("CV: " + cv);
        System.out.println("Precio: " + precio + "\n\t");
    }
    public void mostrarDatos2 (){
        System.out.println("ID :" + id);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("CV: " + cv);
        System.out.println("\n\t");
    }
}

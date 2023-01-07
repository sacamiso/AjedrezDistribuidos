// CREADO POR: SARA CAMISÓN PERAITA Y FIDEL RUIZ ALCORTA.

package AjedrezAplicacion;

import java.io.Serializable;

public class Pieza implements Serializable{
    private String nombre;
    private String color;

    public Pieza(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;

    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

}

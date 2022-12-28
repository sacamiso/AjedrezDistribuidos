package AjedrezAplicacion;

public class Pieza {
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

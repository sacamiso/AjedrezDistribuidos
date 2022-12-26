package AjedrezAplicacion.Figuras;

public class Pieza {
    private String nombre;
    private String color;
    private boolean movido;

    public Pieza(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.movido = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    public boolean getMovido() {
        return movido;
    }

    public void setMovido(boolean movido) {
        this.movido = movido;
    }

    public boolean movimientoValido(int x0, int y0, int x1, int y1) {
        switch (this.nombre) {
            case "Alfil":

                break;
            case "Caballo":

                break;
            case "Peon":

                break;
            case "Reina":

                break;
            case "Rey":

                break;
            case "Torre":

                break;
                

        }
        return false;
    }

}

package AjedrezAplicacion.Figuras;

public class Reina implements Pieza{

    private String nombre;
    private String color;

    public Reina(String col) {
        this.color = col;
        this.nombre = "REINA";
    }

    public Reina(){}

    @Override
    public boolean movimientoValido(int x0, int y0, int x1, int y1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getNombre() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getColor() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
}

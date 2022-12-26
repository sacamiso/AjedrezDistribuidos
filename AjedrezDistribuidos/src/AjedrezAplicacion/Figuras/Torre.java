package AjedrezAplicacion.Figuras;

public class Torre implements Pieza{
    
    private String nombre;

    public Torre(String nombre) {
        this.nombre = nombre;
    }

    public Torre(){}

    @Override
    public boolean movimientoValido(int x0, int y0, int x1, int y1) {
        // TODO Auto-generated method stub
        return false;
    }

   


}

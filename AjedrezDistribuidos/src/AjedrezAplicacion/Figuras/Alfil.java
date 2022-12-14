package AjedrezAplicacion.Figuras;

public class Alfil implements Pieza{

    private String nombre;

    public Alfil(String nombre) {
        this.nombre = nombre;
    }

    public Alfil(){}

    @Override
    public boolean movimientoValido(int[] actual, int[] futuro) {
        // TODO Auto-generated method stub
        return false;
    }

    
}

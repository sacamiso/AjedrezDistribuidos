package AjedrezAplicacion.Figuras;

public class Reina implements Pieza{

    private String nombre;

    public Reina(String nombre) {
        this.nombre = nombre;
    }

    public Reina(){}

    @Override
    public boolean movimientoValido(int[] actual, int[] futuro) {
        // TODO Auto-generated method stub
        return false;
    }

    
}

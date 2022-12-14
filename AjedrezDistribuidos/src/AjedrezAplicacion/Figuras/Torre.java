package AjedrezAplicacion.Figuras;

public class Torre implements Pieza{
    
    private String nombre;

    public Torre(String nombre) {
        this.nombre = nombre;
    }

    public Torre(){}

    @Override
    public boolean movimientoValido(int[] actual, int[] futuro) {
        // TODO Auto-generated method stub
        return false;
    }


}

package AjedrezAplicacion.Figuras;

public class Rey implements Pieza{

    private String nombre;

    public Rey(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean movimientoValido(int[] actual, int[] futuro) {
        // TODO Auto-generated method stub
        return false;
    }

}

package AjedrezAplicacion.Figuras;

public class Caballo  implements Pieza{
    
    private String nombre;

    public Caballo(String nombre) {
        this.nombre = nombre;
    }

    public Caballo(){}

    @Override
    public boolean movimientoValido(int[] actual, int[] futuro) {
        // TODO Auto-generated method stub
        return false;
    }


}

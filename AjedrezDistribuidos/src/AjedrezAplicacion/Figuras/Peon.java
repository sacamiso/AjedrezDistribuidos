package AjedrezAplicacion.Figuras;

public class Peon implements Pieza{

    private String nombre;
    private String valor;

    public Peon(String nombre) {
        this.nombre = nombre;
        this.valor = "Peon";
    }

    @Override
    public boolean movimientoValido(int[] actual, int[] futuro) {
        // TODO Auto-generated method stub
        if(this.valor.equals("Peon")){

        }else if(this.valor.equals("Reina")){
            return new Reina().movimientoValido(actual, futuro);
        }else if(this.valor.equals("Alfil")){
            return new Alfil().movimientoValido(actual, futuro);
        }else if(this.valor.equals("Caballo")){
            return new Caballo().movimientoValido(actual, futuro);
        }else{
            return new Torre().movimientoValido(actual, futuro);
        }
        return false;
    }

    
}

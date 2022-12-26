package AjedrezAplicacion.Figuras;

public class Peon implements Pieza{

    private String nombre;
    private String valor;
    private boolean movido;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean isMovido() {
        return movido;
    }

    public void setMovido(boolean movido) {
        this.movido = movido;
    }

    public Peon(String nombre) {
        this.nombre = nombre;
        this.valor = "Peon";
    }

    @Override
    public boolean movimientoValido(int x0, int y0, int x1, int y1) {
        // TODO Auto-generated method stub
        if(this.valor.equals("Peon")){
            if(this.movido!=true && ){

            }else{
                
            }
        }else if(this.valor.equals("Reina")){
            return new Reina().movimientoValido(x0,y0,x1,y1);
        }else if(this.valor.equals("Alfil")){
            return new Alfil().movimientoValido(x0,y0,x1,y1);
        }else if(this.valor.equals("Caballo")){
            return new Caballo().movimientoValido(x0,y0,x1,y1);
        }else{
            return new Torre().movimientoValido(x0,y0,x1,y1);
        }
        return false;
    }

    
}

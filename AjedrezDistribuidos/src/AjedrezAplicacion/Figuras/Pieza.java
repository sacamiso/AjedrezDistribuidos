package AjedrezAplicacion.Figuras;

public interface Pieza {
    
    public boolean movimientoValido(int x0, int y0,int x1,int y1);
    public String getNombre();
    public String getColor();
}

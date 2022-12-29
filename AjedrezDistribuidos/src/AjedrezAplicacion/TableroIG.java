package AjedrezAplicacion;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class TableroIG implements ActionListener {
    private JFrame interfaz;
    private JPanel panel;
    private static Component comp;

    private JButton[][] botones;
    private Pieza[][] piezas;
    private Pieza[][] piezasFuturas;
    private String turno;
    private String jugador;

    private boolean enroqueNegras;
    private boolean enroqueBlancas;

    private boolean jaqueMate;
    private boolean tablas;

    private String posicionAntigua = null;
    private String posicionNueva = null;
    private String posicionActual;

    // Para usar en caso de que un peon llegue al final
    public static Pieza fichaElegida;
    public static ImageIcon imagenElegida;

    private boolean enroqueIzquierdaNegras;
    private boolean enroqueDerechaBlancas;
    private boolean enroqueDerechaNegras;
    private boolean enroqueIzquierdaBlancas;

    private int tamY;
    private int tamX;
    private int diffTamY;
    private int diffTamX;

    public TableroIG(String player) {
        // Empiezan jugando las blancas
        turno = "Blanco";
        jugador = player;
        // Situamos condiciones iniciales:
        // Blancas y negras pueden hacer enroque
        // Los reyes ni las torres no se han movido
        // No hay tablas ni jaque mate al iniciar la partida
        enroqueNegras = true;
        enroqueBlancas = true;

        jaqueMate = false;
        tablas = false;

        enroqueIzquierdaNegras = true;
        enroqueDerechaBlancas = true;
        enroqueDerechaNegras = true;
        enroqueIzquierdaBlancas = true;
        this.piezas = new Pieza[8][8];

        // CREAMOS EL TABLERO
        // PASO 1. CREACIÓN DE CASILLAS
        botones = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String s = "" + i + j;
                botones[i][j] = new JButton();
                botones[i][j].addActionListener(this);

                if ((i + j + 1) % 2 == 0) {
                    // al botón le pones el color negro como en tu condición
                    botones[i][j].setBackground(new Color(185, 122, 87));
                } else {
                    // o le pones el color blanco aquí tu logica es practicamente la misma
                    botones[i][j].setBackground(new Color(228, 205, 190));
                }
            }
        }

        // PASO 2. INCLUIMOS EN LAS CASILLAS LAS IMAGENES DE LAS PIEZAS QUE COMIENZAN LA
        // PARTIDA EN LAS MISMAS
        int ancho = 85;
        int alto = 85;

        // TORRES
        ImageIcon imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/TorreNegra.png");

        ImageIcon imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][0].setIcon(imag2);
        botones[0][7].setIcon(imag2);

        piezas[0][0] = new Pieza("Torre", "Negro");
        piezas[0][7] = new Pieza("Torre", "Negro");

        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/Torre.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][0].setIcon(imag2);
        botones[7][7].setIcon(imag2);

        piezas[7][0] = new Pieza("Torre", "Blanco");
        piezas[7][7] = new Pieza("Torre", "Blanco");

        // CABALLOS
        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/CaballoNegro.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][1].setIcon(imag2);
        botones[0][6].setIcon(imag2);

        piezas[0][1] = new Pieza("Caballo", "Negro");
        piezas[0][6] = new Pieza("Caballo", "Negro");

        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/Caballo.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][1].setIcon(imag2);
        botones[7][6].setIcon(imag2);

        piezas[7][1] = new Pieza("Caballo", "Blanco");
        piezas[7][6] = new Pieza("Caballo", "Blanco");

        // ALFILES
        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/AlfilNegro.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][2].setIcon(imag2);
        botones[0][5].setIcon(imag2);

        piezas[0][2] = new Pieza("Alfil", "Negro");
        piezas[0][5] = new Pieza("Alfil", "Negro");

        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/Alfil.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][2].setIcon(imag2);
        botones[7][5].setIcon(imag2);

        piezas[7][2] = new Pieza("Alfil", "Blanco");
        piezas[7][5] = new Pieza("Alfil", "Blanco");

        // REINAS
        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/ReinaNegra.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][3].setIcon(imag2);

        piezas[0][3] = new Pieza("Reina", "Negro");


        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/Reina.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][3].setIcon(imag2);

        piezas[7][3] = new Pieza("Reina", "Blanco");

        // REYES
        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/ReyNegro.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][4].setIcon(imag2);

        piezas[0][4] = new Pieza("Rey", "Negro");

        imag = new ImageIcon(

                "AjedrezDistribuidos/src/Imagenes/Rey.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][4].setIcon(imag2);

        piezas[7][4] = new Pieza("Rey", "Blanco");

        // PEONES
        for (int i = 0; i < 8; i++) {

            imag = new ImageIcon(

                    "AjedrezDistribuidos/src/Imagenes/Ficha PeonNegra.png");

            imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

            botones[1][i].setIcon(imag2);

            piezas[1][i] = new Pieza("Peon", "Negro");
        }

        for (int i = 0; i < 8; i++) {

            imag = new ImageIcon(

                    "AjedrezDistribuidos/src/Imagenes/Ficha Peon.png");

            imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

            botones[6][i].setIcon(imag2);

            piezas[6][i] = new Pieza("Peon", "Blanco");
        }

        // PASO 3.Creamos el JPanel que pasara a ser nuestro tablero en forma de
        // INTERFAZ.
        JPanel tableroOriginal = new JPanel();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                tableroOriginal.add(botones[i][j]);
                if(this.piezas[i][j]== null){
                    this.piezas[i][j] = new Pieza("Vacio","Neutro");
                }
            }
        }

        tableroOriginal.setLayout(new GridLayout(8, 8));
        comp = tableroOriginal;
        panel = tableroOriginal;
        // PASO 4.Iniciamos la interfaz
        interfaz = new JFrame("Tablero de Ajedrez");
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        interfaz.setLayout(new GridLayout(1, 1));

        interfaz.add(comp);

        interfaz.setLocationRelativeTo(null);
        interfaz.setPreferredSize(new Dimension(600, 600));
        interfaz.setResizable(false);
        interfaz.pack();
        interfaz.setVisible(true);
        resolverTamaños();
        // Ahora creamos las piezas en si mismas y las añadimos a la matriz de las
        // mismas.

        

        // PRUEBAS

        System.out.println(getBotonPosicionString(botones[7][5]));
        System.out.println(getBotonPosicionString(botones[0][5]));
        // System.out.println(getBotonPosicionString(boton("52")));
        // System.out.println(botones[0][3].getY());
        System.out.println(botones[0][4].getY());
        System.out.println(botones[0][0].getX());
        System.out.println(botones[0][1].getX());
        System.out.println(botones[0][2].getX());
    }

    public String getTurno() {
        return turno;
    }

    // PERMITE AL CLIENTE DEL OPONENTE OBTENER EL ESTADO DE LA PARTIDA.
    public Component getComp() {
        return comp;
    }

    // PERMITE AL CLIENTE DEL JUGADOR OBTENER EL ESTADO DE LA PARTIDA.
    public void setComp(Component comp) {
        TableroIG.comp = comp;
    }

    // ACTUALIZAMOS EL TABLERO
    public void actualizarTablero(TableroIG nuevoTablero) {
        this.interfaz.remove(comp);
        this.panel = (JPanel) nuevoTablero.getComp();
        this.setComp(nuevoTablero.getComp());
        this.interfaz.add(comp);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        JButton s = (JButton) ae.getSource();

        this.posicionActual = getBotonPosicionString(s);
        comprobarJaqueMate();

        if (comprobarColorTurno(this.posicionActual)) {
            this.posicionAntigua = this.posicionActual;
        } else if (this.posicionAntigua != null) {
            this.posicionNueva = this.posicionActual;
            if (esPosibleEsteMovimiento(this.piezas, this.posicionAntigua, this.posicionNueva)) {
                cambiarFichas(posicionAntigua, posicionNueva);
                this.posicionNueva = null;
                this.posicionAntigua = null;
             
                
                if (this.turno.equals("Blanco")) {
                    this.turno = "Negro";
                } else {
                    this.turno = "Blanco";
                }
                //bloquear();              
                
            }
        }

    }

    private String getBotonPosicionString(JButton boton) {
        String dev = "" + ((boton.getY()-this.diffTamY) / this.tamY) + ((boton.getX() - this.diffTamX) / this.tamX);
        return dev;
    }

    private boolean comprobarColorTurno(String posicion) {
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        if (!this.piezas[y][x].getNombre().equals("Vacio")) {
            return (this.piezas[y][x].getColor().equals(this.turno)) ? true : false;
        }
        return false;
    }

    public boolean esPosibleEsteMovimiento(Pieza[][] tablero, String posicionAntigua, String posicionNueva) {
        Pieza aux[][] = tablero;
        String[] posicionesPosibles;
        posicionesPosibles = movimientosModificados(tablero, posicionAntigua);

        if (posicionesPosibles != null) {
            for (int i = 0; i < posicionesPosibles.length; i++) {
                if (posicionesPosibles[i].equals(posicionNueva)) {
                    comprobarAcercaDeEnrroque(posicionAntigua);
                    return true;
                }
            }
        }
        return false;
    }

    private void cambiarFichas(String posAntigua, String posNueva) {
        cambiarEnMatriz(posAntigua, posNueva);
        cambiarEnPantalla(posAntigua, posNueva);
        comprobarPeonEnUltimaFila();
        comprobarEnroque(posAntigua, posNueva);
    }

    private void cambiarEnMatriz(String posAntigua, String posNueva) {
        int xA = Character.getNumericValue(posAntigua.charAt(1));
        int yA = Character.getNumericValue(posAntigua.charAt(0));

        int xN = Character.getNumericValue(posNueva.charAt(1));
        int yN = Character.getNumericValue(posNueva.charAt(0));

        this.piezas[yN][xN] = this.piezas[yA][xA];
        this.piezas[yA][xA] = new Pieza("Vacio","Neutro");
    }

    private void cambiarEnPantalla(String posAntigua, String posNueva) {
        boton(posNueva).setIcon(boton(posAntigua).getIcon());
        boton(posAntigua).setIcon(null);
    }

    private JButton boton(String posicion) {
        return this.botones[Character.getNumericValue(posicion.charAt(0))][Character
                .getNumericValue(posicion.charAt(1))];
    }

    private void comprobarPeonEnUltimaFila() {
        for (int i = 0; i < 8; i++) {
            if (this.piezas[0][i].getNombre().equals("Peon")) {
                // Se mostrara la tabla de eleccion de ficha
                eleccionDePeon();
                this.piezas[0][i] = fichaElegida;
                String posicion = "0" + i;
                boton(posicion).setIcon(imagenElegida);
            }

            if (this.piezas[7][i].getNombre().equals("Peon")) {
                eleccionDePeon();
                this.piezas[7][i] = fichaElegida;
                String posicion = "7" + i;
                boton(posicion).setIcon(imagenElegida);
            }
        }
    }

    private void eleccionDePeon() {
        EleccionFicha ventanaElec = new EleccionFicha(null, true, this.turno);
        ventanaElec.setVisible(true);
    }

    private void comprobarEnroque(String posAntigua, String posNueva) {
        int xN = Character.getNumericValue(posNueva.charAt(1));
        int yN = Character.getNumericValue(posNueva.charAt(0));

        int xA = Character.getNumericValue(posAntigua.charAt(1));
        int yA = Character.getNumericValue(posAntigua.charAt(0));

        if ((this.piezas[yN][xN].getNombre().equals("Rey") && this.piezas[yN][xN].getColor().equals("Blanco"))
                || (this.piezas[yN][xN].getNombre().equals("Rey") && this.piezas[yN][xN].getColor().equals("Negro"))) {
            if (xA + 2 == xN) {
                // Derecha
                this.piezas[yN][xN - 1] = this.piezas[yN][7];
                this.piezas[yN][xN + 1] = new Pieza("Vacio","Neutro");

                String posicionTorreAntigua = "" + yN + 7;
                String posicionTorreNueva = "" + yN + (xN - 1);

                boton(posicionTorreNueva).setIcon(boton(posicionTorreAntigua).getIcon());
                boton(posicionTorreAntigua).setIcon(null);
            } else if (xA - 2 == xN) {
                // Izquierda
                this.piezas[yN][xA - 1] = this.piezas[yN][0];
                this.piezas[yN][0] = new Pieza("Vacio","Neutro");

                String posicionTorreAntigua = "" + yN + 0;
                String posicionTorreNueva = "" + yN + (xA - 1);

                boton(posicionTorreNueva).setIcon(boton(posicionTorreAntigua).getIcon());
                boton(posicionTorreAntigua).setIcon(null);
            }
        }
    }

    private void comprobarJaqueMate(){
        this.jaqueMate = true;
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                String posicion = ""+i+""+j;
                if(colorFichaDeLaCasilla(this.piezas, i, j).equals(this.turno)){
                    String[] movimientosF = movimientosModificados(this.piezas, posicion);
                    if(movimientosF!=null && !movimientosF[0].equals("")){
                        this.jaqueMate = false;
                        break;
                    }
                }
            }
        }
        System.out.println(jaqueMate);
        if(jaqueMate == true){
            Mate ventana = new Mate(null, true);
            ventana.setVisible(true);
        }
        
    }

    public String[] movimientosModificados(Pieza[][] tableroM, String posicion) {

        return modificacionTodosMovimientos(tableroM, posicion, movimientosFichas(tableroM, posicion));
    }

    private void comprobarAcercaDeEnrroque(String posAntigua) {
        if (posAntigua.equals("74")) {
            enroqueBlancas = false;
        } else if (posAntigua.equals("70")) {
            enroqueIzquierdaBlancas = false;
        } else if (posAntigua.equals("77")) {
            enroqueDerechaBlancas = false;
        } else if (posAntigua.equals("04")) {
            enroqueNegras = false;
        } else if (posAntigua.equals("00")) {
            enroqueIzquierdaNegras = false;
        } else if (posAntigua.equals("07")) {
            enroqueDerechaNegras = false;
        }
    }

    public String[] movimientosFichas(Pieza[][] tableroM, String posicion) {
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        String ficha = fichaDeLaCasilla(tableroM, y, x);
        String color = colorFichaDeLaCasilla(tableroM, y, x);
        if (ficha.equals("Peon")) {
            return movimientoPeon(tableroM, y, x, color);
        } else if (ficha.equals("Torre")) {
            return movimientoTorre(tableroM, y, x, color);
        } else if (ficha.equals("Alfil")) {
            return movimientoAlfil(tableroM, y, x, color);
        } else if (ficha.equals("Caballo")) {
            return movimientoCaballo(tableroM, y, x, color);
        } else if (ficha.equals("Reina")) {
            return movimientoReina(tableroM, y, x, color);
        } else if (ficha.equals("Rey")) {
            return movimientoRey(tableroM, y, x, color);
        }

        return null;
    }

    private String[] movimientoPeon(Pieza[][] tableroM, int y, int x, String color) {
        String posicionesPosibles = "";
        if (color.equals("Negro")) {
            if (tableroM[y + 1][x].getNombre().equals("Vacio")) {
                posicionesPosibles += "" + (y + 1) + x + "_";
            }
            try {
                if (tableroM[y + 2][x].getNombre().equals("Vacio") && y == 1 && tableroM[y + 1][x].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y + 2) + (x) + "_";
                }
            } catch (Exception ex) {
            }

            try {
                if (colorFichaDeLaCasilla(tableroM, y + 1, x - 1).equals("Blanco")) {
                    posicionesPosibles += "" + (y + 1) + (x - 1) + "_";
                }
            } catch (Exception ex) {
            }

            try {
                if (colorFichaDeLaCasilla(tableroM, y + 1, x + 1).equals("Blanco")) {
                    posicionesPosibles += "" + (y + 1) + (x + 1) + "_";
                }

            } catch (Exception ex) {

            }
        } else {
            if (tableroM[y - 1][x].getNombre().equals("Vacio")) {
                posicionesPosibles += "" + (y - 1) + x + "_";
            }
            try {
                if (tableroM[y - 2][x].getNombre().equals("Vacio") && y == 6 && tableroM[y - 1][x].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y - 2) + (x) + "_";
                }
            } catch (Exception ex) {
            }

            try {
                if (colorFichaDeLaCasilla(tableroM, y - 1, x - 1).equals("Negro")) {
                    posicionesPosibles += "" + (y - 1) + (x - 1) + "_";
                }
            } catch (Exception ex) {
            }

            try {
                if (colorFichaDeLaCasilla(tableroM, y - 1, x + 1).equals("Negro")) {
                    posicionesPosibles += "" + (y - 1) + (x + 1) + "_";
                }

            } catch (Exception ex) {

            }
        }

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

    private String[] movimientoTorre(Pieza[][] tableroM, int y, int x, String color) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        // Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, i, x).equals(color)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (colorFichaDeLaCasilla(tableroM, i, x).equals(color)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        // Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, i, x).equals(color)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (colorFichaDeLaCasilla(tableroM, i, x).equals(color)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        // Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y, i).equals(color)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (colorFichaDeLaCasilla(tableroM, y, i).equals(color)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        // Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y, i).equals(color)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (colorFichaDeLaCasilla(tableroM, y, i).equals(color)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfil(Pieza[][] tableroM, int y, int x, String color) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y + i, x + i).equals(color)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y + i, x + i).equals(color)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y - i, x + i).equals(color)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y - i, x + i).equals(color)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoCaballo(Pieza[][] tableroM, int y, int x, String color) {
        String posicionesPosibles = "";

        try {
            // Movimiento arriba-iaquierda
            if (tableroM[y - 2][x - 1].getNombre().equals("Vacio") || !colorFichaDeLaCasilla(piezas, y - 2, x - 1).equals(color)) {
                posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento arriba-derecha
            if (tableroM[y - 2][x + 1].getNombre().equals("Vacio")|| !colorFichaDeLaCasilla(piezas, y - 2, x + 1).equals(color)) {
                posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento abajo-izquierda
            if (tableroM[y + 2][x - 1].getNombre().equals("Vacio") || !colorFichaDeLaCasilla(piezas, y + 2, x - 1).equals(color)) {
                posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento abajo-derecha
            if (tableroM[y + 2][x + 1].getNombre().equals("Vacio")|| !colorFichaDeLaCasilla(piezas, y + 2, x + 1).equals(color)) {
                posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento izquierda-arriba
            if (tableroM[y - 1][x - 2].getNombre().equals("Vacio") || !colorFichaDeLaCasilla(piezas, y - 1, x - 2).equals(color)) {
                posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento izquierda-abajo
            if (tableroM[y + 1][x - 2].getNombre().equals("Vacio") || !colorFichaDeLaCasilla(piezas, y + 1, x - 2).equals(color)) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento derecha-arriba
            if (tableroM[y - 1][x + 2].getNombre().equals("Vacio") || !colorFichaDeLaCasilla(piezas, y - 1, x + 2).equals(color)) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento derecha-abajo
            if (tableroM[y + 1][x + 2].getNombre().equals("Vacio") || !colorFichaDeLaCasilla(piezas, y + 1, x + 2).equals(color)) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoReina(Pieza[][] tableroM, int y, int x, String color) {
        String[] movimientoDiagonal = movimientoAlfil(tableroM, y, x, color);
        String[] movimientoRecto = movimientoTorre(tableroM, y, x, color);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++) {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++) {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

    private String[] movimientoRey(Pieza[][] tableroM, int y, int x, String color) {

        String posicionesPosibles = "";

        try {
            if (!colorFichaDeLaCasilla(tableroM, y - 1, x - 1).equals(color)) {
                posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (!colorFichaDeLaCasilla(tableroM, y - 1, x).equals(color)) {
                posicionesPosibles += "" + (y - 1) + "" + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (!colorFichaDeLaCasilla(tableroM, y - 1, x + 1).equals(color)) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (!colorFichaDeLaCasilla(tableroM, y, x - 1).equals(color)) {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (!colorFichaDeLaCasilla(tableroM, y, x + 1).equals(color)) {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (!colorFichaDeLaCasilla(tableroM, y + 1, x - 1).equals(color)) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (!colorFichaDeLaCasilla(tableroM, y + 1, x).equals(color)) {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (!colorFichaDeLaCasilla(tableroM, y + 1, x + 1).equals(color)) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        // Enrroque
        if (color.equals("Blanco") && this.enroqueBlancas == true) {
            if (tableroM[7][1].getNombre().equals("Vacio")&& tableroM[7][2].getNombre().equals("Vacio") && tableroM[7][3].getNombre().equals("Vacio")
                    && this.enroqueIzquierdaBlancas == true) {
                posicionesPosibles += "" + "72" + "_";
            }
            if (tableroM[7][5].getNombre().equals("Vacio")&& tableroM[7][6].getNombre().equals("Vacio")
                    && this.enroqueDerechaBlancas == true) {
                posicionesPosibles += "" + "76" + "_";
            }
        } else if (color.equals("Negro") && this.enroqueNegras == true) {
            if (tableroM[0][1].getNombre().equals("Vacio") && tableroM[0][2].getNombre().equals("Vacio") && tableroM[0][3].getNombre().equals("Vacio")
                    && this.enroqueIzquierdaNegras == true) {
                posicionesPosibles += "" + "02" + "_";
            }
            if (tableroM[0][5].getNombre().equals("Vacio") && tableroM[0][6].getNombre().equals("Vacio")
                    && this.enroqueDerechaNegras == true) {
                posicionesPosibles += "" + "06" + "_";
            }
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] modificacionTodosMovimientos(Pieza[][] tableroFuturo, String posicionInicial,
            String[] posicionesFinales) {
        String posicionesDefinitivas = "";

        int xInicial = Character.getNumericValue(posicionInicial.charAt(1));
        int yInicial = Character.getNumericValue(posicionInicial.charAt(0));

        if (posicionesFinales != null) {
            for (int i = 0; i < posicionesFinales.length; i++) {
                try {
                    String posicionFinal = posicionesFinales[i];
                    int xFinal = Character.getNumericValue(posicionFinal.charAt(1));
                    int yFinal = Character.getNumericValue(posicionFinal.charAt(0));

                    Pieza tableroM[][] = new Pieza[8][8];

                    copiarTableroPrimeroAlSegundo(tableroFuturo, tableroM);

                    tableroM[yFinal][xFinal] = tableroM[yInicial][xInicial];
                    tableroM[yInicial][xInicial] = new Pieza("Vacio","Neutro");

                    if (reyEnJaque(colorFichaDeLaCasilla(this.piezas, yInicial, xInicial), tableroM) == false) {
                        posicionesDefinitivas += "" + yFinal + "" + xFinal + "_";
                    }

                } catch (Exception ex) {
                }

            }

            String[] arrayPosiciones = posicionesDefinitivas.split("_");
            return arrayPosiciones;
        }
        return null;
    }

    private void copiarTableroPrimeroAlSegundo(Pieza[][] tableroOrigen, Pieza[][] tableroCopia) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tableroCopia[i][j] = tableroOrigen[i][j];
            }
        }
    }

    private boolean reyEnJaque(String color, Pieza[][] tablero) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fichaDeLaCasilla(tablero, i, j).equals("Rey")
                        && colorFichaDeLaCasilla(tablero, i, j).equals(color)) {
                    String posicionRey = "" + i + "" + j;
                    String[] movimientosEnemigos;

                    if (color.equals("Blanco")) {
                        movimientosEnemigos = todosLosMovimientosFichas("Negro", tablero);
                    } else {
                        movimientosEnemigos = todosLosMovimientosFichas("Blanco", tablero);
                    }

                    for (int x = 0; x < movimientosEnemigos.length; x++) {
                        if (movimientosEnemigos[x].equals(posicionRey)) {
                            return true;
                        }
                    }
                    return false;

                }
            }
        }
        return false;
    }

    public String[] todosLosMovimientosFichas(String color, Pieza[][] tablero) {

        String posicionesTotales = "";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String ficha = fichaDeLaCasilla(tablero, i, j);
                String colorFich = colorFichaDeLaCasilla(tablero, i, j);
                if (ficha.equals("Torre") && colorFich.equals(color)) {
                    posicionesTotales += transformarApalabra(movimientoTorreSoloAtaque(tablero, i, j, color));
                } else if (ficha.equals("Alfil") && colorFich.equals(color)) {
                    posicionesTotales += transformarApalabra(movimientoAlfilSoloAtaque(tablero, i, j, color));
                } else if (ficha.equals("Reina") && colorFich.equals(color)) {
                    posicionesTotales += transformarApalabra(movimientoReinaSoloAtaque(tablero, i, j, color));
                } else if (ficha.equals("Caballo") && colorFich.equals(color)) {
                    posicionesTotales += transformarApalabra(movimientoCaballoSoloAtaque(tablero, i, j, color));
                } else if (ficha.equals("Rey") && colorFich.equals(color)) {
                    posicionesTotales += transformarApalabra(movimientoReySoloAtaque(tablero, i, j, color));
                } else if (ficha.equals("Peon") && colorFich.equals(color)) {
                    posicionesTotales += transformarApalabra(movimientoPeonSoloAtaque(tablero, i, j, color));
                }

            }
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    private String transformarApalabra(String[] array) {
        String palabra = "";
        for (int i = 0; i < array.length; i++) {
            palabra += array[i] + "_";
        }
        return palabra;
    }

    private String fichaDeLaCasilla(Pieza[][] tableroM, int y, int x) {
        return tableroM[y][x].getNombre();
    }

    private String colorFichaDeLaCasilla(Pieza[][] tableroM, int y, int x) {
        return tableroM[y][x].getColor();
    }

    private String[] movimientoTorreSoloAtaque(Pieza[][] tableroM, int y, int x, String color) {

        String posicionesPosibles = "";
        int i;
        boolean seguir;

        // Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, i, x).equals(color)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (colorFichaDeLaCasilla(tableroM, i, x).equals(color)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        // Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, i, x).equals(color)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (colorFichaDeLaCasilla(tableroM, i, x).equals(color)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        // Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y, i).equals(color)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (colorFichaDeLaCasilla(tableroM, y, i).equals(color)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        // Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y, i).equals(color)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (colorFichaDeLaCasilla(tableroM, y, i).equals(color)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

    private String[] movimientoAlfilSoloAtaque(Pieza[][] tableroM, int y, int x, String color) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y + i, x + i).equals(color)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y + i, x + i).equals(color)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y - i, x + i).equals(color)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].getNombre().equals("Vacio")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (!colorFichaDeLaCasilla(tableroM, y + i, x + i).equals(color)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoReinaSoloAtaque(Pieza[][] tableroM, int y, int x, String color) {
        String[] movimientoDiagonal = movimientoAlfilSoloAtaque(tableroM, y, x, color);
        String[] movimientoRecto = movimientoTorreSoloAtaque(tableroM, y, x, color);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++) {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++) {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

    private String[] movimientoCaballoSoloAtaque(Pieza[][] tableroM, int y, int x, String color) {
        String posicionesPosibles = "";

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x - 1].getNombre();
            posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x + 1].getNombre();
            posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x - 1].getNombre();
            posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x + 1].getNombre();
            posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x - 2].getNombre();
            posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x - 2].getNombre();
            posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x + 2].getNombre();
            posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x + 2].getNombre();
            posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoReySoloAtaque(Pieza[][] tableroM, int y, int x, String color) {

        String posicionesPosibles = "";

        if (y > 0 && x > 0) {
            posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
        }

        try {
            if (y > 0 && x < 7) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {

        }

        try {
            if (y > 0) {
                posicionesPosibles += "" + (y - 1) + "" + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (x > 0) {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (x < 7) {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7 && x > 0) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7) {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }

        } catch (Exception ex) {

        }

        try {
            if (y < 7 && x < 7) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }

        } catch (Exception ex) {

        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoPeonSoloAtaque(Pieza[][] tableroM, int y, int x, String color) {
        String posicionesPosibles = "";
        if (color.equals("Negro")) {

            try {
                if (colorFichaDeLaCasilla(tableroM, y + 1, x - 1).equals("Blanco")) {
                    posicionesPosibles += "" + (y + 1) + (x - 1) + "_";
                }
            } catch (Exception ex) {
            }

            try {
                if (colorFichaDeLaCasilla(tableroM, y + 1, x + 1).equals("Blanco")) {
                    posicionesPosibles += "" + (y + 1) + (x + 1) + "_";
                }

            } catch (Exception ex) {

            }
        } else {

            try {
                if (colorFichaDeLaCasilla(tableroM, y - 1, x - 1).equals("Negro")) {
                    posicionesPosibles += "" + (y - 1) + (x - 1) + "_";
                }
            } catch (Exception ex) {
            }

            try {
                if (colorFichaDeLaCasilla(tableroM, y - 1, x + 1).equals("Negro")) {
                    posicionesPosibles += "" + (y - 1) + (x + 1) + "_";
                }

            } catch (Exception ex) {

            }
        }

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

    private void bloquear(){
        for (Component a: this.panel.getComponents()){
            a.setEnabled(false);
        }
    }

    public void desbloquear(){
        for (Component a: this.panel.getComponents()){
            a.setEnabled(true);
        }
    }

    public void resolverTamaños(){
        
        //Tamaño Y
        this.diffTamY= botones[0][0].getY();
        this.tamY = botones[1][0].getY() -botones[0][0].getY();

        //Tamaño X
        this.diffTamX= botones[0][0].getY();
        this.tamX = botones[0][1].getY() -botones[0][0].getY();
    }
}

package AjedrezAplicacion;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import AjedrezAplicacion.Figuras.Pieza;

public class TableroIG implements ActionListener{
    private JFrame interfaz;

    private static Component comp;

    private JButton[][] botones;
    private Pieza[][] piezas;
    private String turno;

    private boolean enroqueNegras;
    private boolean enroqueBlancas;

    private boolean movTorreNIzq;
    private boolean movTorreNDer;

    private boolean movTorreIzq;
    private boolean movTorreDer;

    private boolean movReyNegras;
    private boolean movReyBlancas;

    private boolean jaqueMate;
    private boolean tablas;

    public TableroIG() {
        // Empiezan jugando las blancas
        turno = "Blanco";

        // Situamos condiciones iniciales:
        // Blancas y negras pueden hacer enroque
        // Los reyes ni las torres no se han movido
        // No hay tablas ni jaque mate al iniciar la partida
        enroqueNegras = true;
        enroqueBlancas = true;

        movTorreNIzq = false;
        movTorreNDer = false;

        movTorreIzq = false;
        movTorreDer = false;

        movReyNegras = false;
        movReyBlancas = false;

        jaqueMate = false;
        tablas = false;

        // CREAMOS EL TABLERO
        // PASO 1. CREACIÓN DE CASILLAS
        botones = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

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

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/TorreNegra.png");

        ImageIcon imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][0].setIcon(imag2);
        botones[0][7].setIcon(imag2);

        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/Torre.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][0].setIcon(imag2);
        botones[7][7].setIcon(imag2);

        // CABALLOS
        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/CaballoNegro.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][1].setIcon(imag2);
        botones[0][6].setIcon(imag2);

        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/Caballo.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][1].setIcon(imag2);
        botones[7][6].setIcon(imag2);

        // ALFILES
        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/AlfilNegro.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][2].setIcon(imag2);
        botones[0][5].setIcon(imag2);

        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/Alfil.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][2].setIcon(imag2);
        botones[7][5].setIcon(imag2);

        // REINAS
        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/ReinaNegra.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][3].setIcon(imag2);

        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/Reina.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][3].setIcon(imag2);

        // REYES
        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/ReyNegro.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[0][4].setIcon(imag2);

        imag = new ImageIcon(

                "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/Rey.png");

        imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

        botones[7][4].setIcon(imag2);

        // PEONES
        for (int i = 0; i < 8; i++) {

            imag = new ImageIcon(

                    "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/Ficha PeonNegra.png");

            imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

            botones[1][i].setIcon(imag2);
        }

        for (int i = 0; i < 8; i++) {

            imag = new ImageIcon(

                    "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/Ficha Peon.png");

            imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

            botones[6][i].setIcon(imag2);
        }

        // PASO 3.Creamos el JPanel que pasara a ser nuestro tablero en forma de
        // INTERFAZ.
        JPanel tableroOriginal = new JPanel();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                tableroOriginal.add(botones[i][j]);

            }
        }

        tableroOriginal.setLayout(new GridLayout(8, 8));
        comp = tableroOriginal;

        // PASO 4.Iniciamos la interfaz
        interfaz = new JFrame("Tablero de Ajedrez");
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        interfaz.setLayout(new GridLayout(1, 1));

        interfaz.add(comp);

        interfaz.setLocationRelativeTo(null);
        interfaz.setPreferredSize(new Dimension(1000, 1000));
        interfaz.pack();
        interfaz.setVisible(true);

        // Ahora creamos las piezas en si mismas y las añadimos a la matriz de las
        // mismas.

        this.piezas = new Pieza[8][8];

        



        System.out.println(botones[0][3].getX());
        System.out.println(botones[0][4].getX());
        System.out.println(botones[0][5].getX());
        System.out.println(botones[0][6].getX());
        System.out.println(botones[0][7].getX());
    }

    public String getTurno() {
        return turno;
    }

    // INDICA SI UNA CASILLA YA ESTA OCUPADA POR OTRA FICHA
    public boolean estaOcupado(int x, int y) {

        return (!this.piezas[x][y].equals(null));
    }

    // INDICA SI UNA CASILLA YA HA SIDO SELECCIONADA
    public boolean yaSeleccionado() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (botones[i][j].isSelected()) {
                    return true;
                }
            }
        }
        return false;
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
        this.setComp(nuevoTablero.getComp());
        this.interfaz.add(comp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        int i = 0;
        int j = 0;
        while(i < 8 && !botones[i][j].isSelected()) {
            while(j < 8 && !botones[i][j].isSelected()) {
                j++;
            }
            i++;
        }
        e.
        
    }

    
}


//Y = Fila   X = Columna
// 0 = 0
// 1 = 123
// 2 = 246
// 3 = 369
// 4 = 492 
// 5 = 615
// 6 = 738 
// 7 = 861
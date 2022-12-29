
package Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import AjedrezAplicacion.*;

public class Main {

    private static int Numfilas = 8;
    private static Component comp;
    private static Component[][] botones;

    public static void main(String[] args) {


        TableroIG tab = new TableroIG("Blanco");
        // botones = new Component[8][8];
        
        // JFrame Ventana = new JFrame("Tablero de Ajedrez");
        // Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JPanel PanelTableroAjedrez = TableroAjedrez2();
        // comp = PanelTableroAjedrez;
        // Ventana.setLayout(new GridLayout(1, 1));
        
        // Ventana.add(comp);

        // Ventana.setLocationRelativeTo(null);
        // Ventana.setPreferredSize(new Dimension(1000, 1000));
        // Ventana.pack();
        // Ventana.setVisible(true);
        
        // actualizar(Ventana);
        
    }

    // (Controlador controlador = new Controlador();

    // public static void mostrarTablero() {
    // int i = 0;
    // int j = 0;
    // if (Numfilas == 4) {
    // System.out.println("_________");
    // while (i <Numfilas) {
    // while (j < Numfilas) {
    // System.out.print("|" + "P");
    // j++;
    // }
    // System.out.print("|" + "\n");
    // i++;
    // j = 0;
    // }
    // System.out.println("_________");
    // } else {
    // System.out.println("___________________");
    // while (i < Numfilas) {
    // while (j < Numfilas) {
    // System.out.print("|" + "P");
    // j++;
    // }
    // System.out.print("|" + "\n");
    // i++;
    // j = 0;
    // }
    // System.out.println("___________________");
    // }
    // }

    public static JPanel TableroAjedrez() {
        JPanel PanelTableroAjedrez = new JPanel();
        String[][] Casillas = new String[8][8];

        
        for (int y = 0; y < Casillas.length; y++) {
            for (int x = 0; x < Casillas[y].length; x++) {
                // en lugar de hacer el add a new JButton
                // primero declaras una variable asignandole lo que ya pasabas
                // en el metodo add
                final JButton jButton = new JButton(Casillas[y][x]);

                PanelTableroAjedrez.add(jButton);
                if ((y + x + 1) % 2 == 0) {
                    // al botón le pones el color negro como en tu condición
                    
                    jButton.setBackground(new Color(185,122,87));
                } else {
                    // o le pones el color blanco aquí tu logica es practicamente la misma
                    jButton.setBackground(new Color(228,205,190));
                }

                ImageIcon imag = new ImageIcon(
                        "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/Reina.png");

                int ancho = 85;
                int alto = 85;

                ImageIcon imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                jButton.setIcon(imag2);
            }
        }
        
        PanelTableroAjedrez.setLayout(new GridLayout(8, 8));

        return PanelTableroAjedrez;
    }


    public static JPanel TableroAjedrez2() {
        JPanel PanelTableroAjedrez = new JPanel();
        String[][] Casillas = new String[8][8];
        for (int y = 0; y < Casillas.length; y++) {
            for (int x = 0; x < Casillas[y].length; x++) {
                // en lugar de hacer el add a new JButton
                // primero declaras una variable asignandole lo que ya pasabas
                // en el metodo add
                final JButton jButton = new JButton(Casillas[y][x]);

                PanelTableroAjedrez.add(jButton);
                if ((y + x + 1) % 2 == 0) {
                    // al botón le pones el color negro como en tu condición
                    jButton.setBackground(Color.BLACK);
                } else {
                    // o le pones el color blanco aquí tu logica es practicamente la misma
                    jButton.setBackground(Color.WHITE);
                }

                ImageIcon imag = new ImageIcon(
                        "C:/Users/fidel/AjedrezDistribuidos/AjedrezDistribuidos-1/AjedrezDistribuidos/src/Imagenes/AlfilNegro.png");

                int ancho = 85;
                int alto = 85;

                ImageIcon imag2 = new ImageIcon(imag.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

                jButton.setIcon(imag2);
            }
        }

        PanelTableroAjedrez.setLayout(new GridLayout(8, 8));

        return PanelTableroAjedrez;
    }

    public static void actualizar(JFrame ventana){
        while (true) {
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            ventana.remove(comp);
            if (i % 2 == 0) {
                comp = TableroAjedrez();
            } else {
                comp = TableroAjedrez2();
            }

            ventana.add(comp);

        }
    }

}

package AjedrezAplicacion;

import javax.swing.ImageIcon;

public class EleccionFicha extends javax.swing.JDialog {

    private String color;

    public EleccionFicha(java.awt.Frame parent, boolean modal, String c) {
        super(parent, modal);
        this.color = c;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        if (this.color.equals("Blanco")) {
            jButton1.setIcon(new javax.swing.ImageIcon("AjedrezDistribuidos/src/Imagenes/Reina.png")); 
        } else {
            jButton1.setIcon(new javax.swing.ImageIcon("AjedrezDistribuidos/src/Imagenes/ReinaNegra.png"));
        }
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        if (this.color.equals("Blanco")) {
            jButton2.setIcon(new javax.swing.ImageIcon("AjedrezDistribuidos/src/Imagenes/Alfil.png")); 
        } else {
            jButton2.setIcon(new javax.swing.ImageIcon("AjedrezDistribuidos/src/Imagenes/AlfilNegro.png"));
        }

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        if (this.color.equals("Blanco")) {
            jButton3.setIcon(new javax.swing.ImageIcon("AjedrezDistribuidos/src/Imagenes/Caballo.png")); 
        } else {
            jButton3.setIcon(new javax.swing.ImageIcon("AjedrezDistribuidos/src/Imagenes/CaballoNegro.png"));
        }

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        if (this.color.equals("Blanco")) {
            jButton4.setIcon(new javax.swing.ImageIcon("AjedrezDistribuidos/src/Imagenes/Torre.png")); 
        } else {
            jButton4.setIcon(new javax.swing.ImageIcon("AjedrezDistribuidos/src/Imagenes/TorreNegro.png"));
        }

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        AjedrezAplicacion.TableroIG.fichaElegida = new Pieza("Alfil", this.color);
        AjedrezAplicacion.TableroIG.imagenElegida = (ImageIcon) jButton2.getIcon();
        dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        AjedrezAplicacion.TableroIG.fichaElegida = new Pieza("Reina", this.color);
        AjedrezAplicacion.TableroIG.imagenElegida = (ImageIcon) jButton1.getIcon();
        dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        AjedrezAplicacion.TableroIG.fichaElegida = new Pieza("Caballo", this.color);
        AjedrezAplicacion.TableroIG.imagenElegida = (ImageIcon) jButton3.getIcon();
        dispose();
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        AjedrezAplicacion.TableroIG.fichaElegida = new Pieza("Torre", this.color);
        AjedrezAplicacion.TableroIG.imagenElegida = (ImageIcon) jButton4.getIcon();
        dispose();
    }

  
    // public static void main(String args[]) {

    //     try {
    //         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    //             if ("Nimbus".equals(info.getName())) {
    //                 javax.swing.UIManager.setLookAndFeel(info.getClassName());
    //                 break;
    //             }
    //         }
    //     } catch (ClassNotFoundException ex) {
    //         java.util.logging.Logger.getLogger(EleccionFicha.class.getName()).log(java.util.logging.Level.SEVERE, null,
    //                 ex);
    //     } catch (InstantiationException ex) {
    //         java.util.logging.Logger.getLogger(EleccionFicha.class.getName()).log(java.util.logging.Level.SEVERE, null,
    //                 ex);
    //     } catch (IllegalAccessException ex) {
    //         java.util.logging.Logger.getLogger(EleccionFicha.class.getName()).log(java.util.logging.Level.SEVERE, null,
    //                 ex);
    //     } catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //         java.util.logging.Logger.getLogger(EleccionFicha.class.getName()).log(java.util.logging.Level.SEVERE, null,
    //                 ex);
    //     }

    //     java.awt.EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             EleccionFicha dialog = new EleccionFicha(new javax.swing.JFrame(), true);
    //             dialog.addWindowListener(new java.awt.event.WindowAdapter() {
    //                 @Override
    //                 public void windowClosing(java.awt.event.WindowEvent e) {
    //                     System.exit(0);
    //                 }
    //             });
    //             dialog.setVisible(true);
    //         }
    //     });
    // }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sick_pls;

import devices.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Angel Miguel
 */
public class GUI extends javax.swing.JFrame {

    private static String puertoCamara, puertoPalantil, puertoLaser, puertoRobot, puertoRFID;
    private boolean iniciado;

    SICK laserInstance;
    Camara palantilInstance;
    NFC nfcInstance;
    rflex_driver robotInstance;

    /**
     * Creates new form GUI
     */
    public GUI() {
       
        initComponents();
        iniciado = false;
        File file=new File("conf.csv");
        if(file.exists())
        {
            try {
                Scanner sc=new Scanner(file);
                
                while(sc.hasNextLine())
                {
                    String linea=sc.nextLine();
                    String aux[]=linea.split(";");
                    
                    switch(aux[0])
                    {
                        case "video":
                            puertoCamara=aux[1];
                            TextFieldCamaraPort.setText(puertoCamara);
                            if(!"null".equals(aux[1]) && !"".equals(aux[1]))
                                CamaraCheckbox.setSelected(true);
                                
                            break;
                        case "palantil":
                            puertoPalantil=aux[1];
                            TextFieldPalantilPort.setText(puertoPalantil);
                            if(!"null".equals(aux[1]) && !"".equals(aux[1]))
                                PalantilCheckbox.setSelected(true);
                            break;
                        case "robot":
                            puertoRobot=aux[1];
                            TextFieldRobotPort.setText(puertoRobot);
                            if(!"null".equals(aux[1]) && !"".equals(aux[1]))
                                RobotCheckbox.setSelected(true);
                            break;
                        case "nfc":
                            puertoRFID=aux[1];
                            TextFieldRFIDPort.setText(puertoRFID);
                            if(!"null".equals(aux[1]) && !"".equals(aux[1]))
                                RFIDCheckbox.setSelected(true);
                            break;
                        case "laser":
                            puertoLaser=aux[1];
                            TextFieldLaserPort.setText(puertoLaser);
                            if(!"null".equals(aux[1]) && !"".equals(aux[1]))
                                LaserCheckbox.setSelected(true);
                            break;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Pinta las medidas recibidas del laser en las barras de progreso.
     *
     * @param medida
     * @param medidas
     */
    public static synchronized void setMeasures(int medida, int pos) {
        if (pos == 0) {
            LaserDistance1.setValue(medida);
            LaserDistance1.setToolTipText(medida + " cm.");
            LaserDistance1.update(LaserDistance1.getGraphics());
        }
        if (pos == 1) {
            LaserDistance2.setValue(medida);
            LaserDistance2.setToolTipText(medida + " cm.");
            LaserDistance2.update(LaserDistance2.getGraphics());
        }
        if (pos == 2) {
            LaserDistance3.setValue(medida);
            LaserDistance3.setToolTipText(medida + " cm.");
            LaserDistance3.update(LaserDistance3.getGraphics());
        }
        if (pos == 3) {
            LaserDistance4.setValue(medida);
            LaserDistance4.setToolTipText(medida + " cm.");
            LaserDistance4.update(LaserDistance4.getGraphics());
        }
        if (pos == 4) {
            LaserDistance5.setValue(medida);
            LaserDistance5.setToolTipText(medida + " cm.");
            LaserDistance5.update(LaserDistance5.getGraphics());
        }
        if (pos == 5) {
            LaserDistance6.setValue(medida);
            LaserDistance6.setToolTipText(medida + " cm.");
            LaserDistance6.update(LaserDistance6.getGraphics());
        }
        if (pos == 6) {
            LaserDistance7.setValue(medida);
            LaserDistance7.setToolTipText(medida + " cm.");
            LaserDistance7.update(LaserDistance7.getGraphics());
        }
        if (pos == 7) {
            LaserDistance8.setValue(medida);
            LaserDistance8.setToolTipText(medida + " cm.");
            LaserDistance8.update(LaserDistance8.getGraphics());
        }
        if (pos == 8) {
            LaserDistance9.setValue(medida);
            LaserDistance9.setToolTipText(medida + " cm.");
            LaserDistance9.update(LaserDistance9.getGraphics());
        }
        if (pos == 9) {
            LaserDistance10.setValue(medida);
            LaserDistance10.setToolTipText(medida + " cm.");
            LaserDistance10.update(LaserDistance10.getGraphics());
        }
        if (pos == 10) {
            LaserDistance11.setValue(medida);
            LaserDistance11.setToolTipText(medida + " cm.");
            LaserDistance11.update(LaserDistance11.getGraphics());
        }
        if (pos == 11) {
            LaserDistance12.setValue(medida);
            LaserDistance12.setToolTipText(medida + " cm.");
            LaserDistance12.update(LaserDistance12.getGraphics());
        }
        if (pos == 12) {
            LaserDistance13.setValue(medida);
            LaserDistance13.setToolTipText(medida + " cm.");
            LaserDistance13.update(LaserDistance13.getGraphics());
        }
        if (pos == 13) {
            LaserDistance14.setValue(medida);
            LaserDistance14.setToolTipText(medida + " cm.");
            LaserDistance14.update(LaserDistance14.getGraphics());
        }
        if (pos == 14) {
            LaserDistance15.setValue(medida);
            LaserDistance15.setToolTipText(medida + " cm.");
            LaserDistance15.update(LaserDistance15.getGraphics());
        }
        if (pos == 15) {
            LaserDistance16.setValue(medida);
            LaserDistance16.setToolTipText(medida + " cm.");
            LaserDistance16.update(LaserDistance16.getGraphics());
        }
        if (pos == 16) {
            LaserDistance17.setValue(medida);
            LaserDistance17.setToolTipText(medida + " cm.");
            LaserDistance17.update(LaserDistance17.getGraphics());
        }
        if (pos == 17) {
            LaserDistance18.setValue(medida);
            LaserDistance18.setToolTipText(medida + " cm.");
            LaserDistance18.update(LaserDistance18.getGraphics());
        }

    }
    
    public static void printNFC(String text){
        NFCText.setText(text);
    }

    public static synchronized void printLaserStatus(String text) {
        LaserStatusText.setText(text);
    }

    public static synchronized void printStatusText(String text) {
        StatusText.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Controles = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        RobotArribaButton1 = new javax.swing.JButton();
        RobotAbajoButton1 = new javax.swing.JButton();
        RobotIzquierdaButton1 = new javax.swing.JButton();
        RobotDerechaButton1 = new javax.swing.JButton();
        FrenosRobotButton1 = new javax.swing.JToggleButton();
        jPanel7 = new javax.swing.JPanel();
        PalantilArribaButton1 = new javax.swing.JButton();
        PalantilAbajoButton1 = new javax.swing.JButton();
        PalantilIzquierdaButton1 = new javax.swing.JButton();
        PalantilDerechaButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        LaserDistance1 = new javax.swing.JProgressBar();
        LaserDistance2 = new javax.swing.JProgressBar();
        LaserDistance3 = new javax.swing.JProgressBar();
        LaserDistance4 = new javax.swing.JProgressBar();
        LaserDistance5 = new javax.swing.JProgressBar();
        LaserDistance6 = new javax.swing.JProgressBar();
        LaserDistance7 = new javax.swing.JProgressBar();
        LaserDistance8 = new javax.swing.JProgressBar();
        LaserDistance9 = new javax.swing.JProgressBar();
        LaserDistance10 = new javax.swing.JProgressBar();
        LaserDistance11 = new javax.swing.JProgressBar();
        LaserDistance12 = new javax.swing.JProgressBar();
        LaserDistance13 = new javax.swing.JProgressBar();
        LaserDistance14 = new javax.swing.JProgressBar();
        LaserDistance15 = new javax.swing.JProgressBar();
        LaserDistance16 = new javax.swing.JProgressBar();
        LaserDistance17 = new javax.swing.JProgressBar();
        LaserDistance18 = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        StatusText = new javax.swing.JLabel();
        LaserStatusText = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        NFCText = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        TextFieldPalantilPort = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextFieldRobotPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFieldCamaraPort = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        TextFieldLaserPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextFieldRFIDPort = new javax.swing.JTextField();
        LaserCheckbox = new javax.swing.JCheckBox();
        RobotCheckbox = new javax.swing.JCheckBox();
        RFIDCheckbox = new javax.swing.JCheckBox();
        PalantilCheckbox = new javax.swing.JCheckBox();
        CamaraCheckbox = new javax.swing.JCheckBox();

        Controles.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        Controles.setTitle("Controles");
        Controles.setBackground(new java.awt.Color(204, 153, 0));
        Controles.setMinimumSize(new java.awt.Dimension(825, 390));
        Controles.setResizable(false);
        Controles.setType(java.awt.Window.Type.UTILITY);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles Robot"));

        RobotArribaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sick_pls/Arriba.png"))); // NOI18N
        RobotArribaButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RobotArribaButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RobotArribaButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RobotArribaButton1MouseExited(evt);
            }
        });
        RobotArribaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RobotArribaButton1ActionPerformed(evt);
            }
        });

        RobotAbajoButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sick_pls/Abajo.png"))); // NOI18N
        RobotAbajoButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RobotAbajoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RobotAbajoButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RobotAbajoButton1MouseExited(evt);
            }
        });
        RobotAbajoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RobotAbajoButton1ActionPerformed(evt);
            }
        });

        RobotIzquierdaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sick_pls/Izquierda.png"))); // NOI18N
        RobotIzquierdaButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RobotIzquierdaButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RobotIzquierdaButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RobotIzquierdaButton1MouseExited(evt);
            }
        });
        RobotIzquierdaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RobotIzquierdaButton1ActionPerformed(evt);
            }
        });

        RobotDerechaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sick_pls/Derecha.png"))); // NOI18N
        RobotDerechaButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RobotDerechaButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RobotDerechaButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RobotDerechaButton1MouseExited(evt);
            }
        });
        RobotDerechaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RobotDerechaButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(RobotArribaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(RobotIzquierdaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RobotAbajoButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RobotDerechaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RobotArribaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RobotDerechaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RobotAbajoButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RobotIzquierdaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FrenosRobotButton1.setSelected(true);
        FrenosRobotButton1.setText("Breaks ON");
        FrenosRobotButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FrenosRobotButton1ItemStateChanged(evt);
            }
        });
        FrenosRobotButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FrenosRobotButton1ActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles Palantil"));

        PalantilArribaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sick_pls/Arriba.png"))); // NOI18N
        PalantilArribaButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PalantilArribaButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PalantilArribaButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PalantilArribaButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PalantilArribaButton1MouseExited(evt);
            }
        });
        PalantilArribaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PalantilArribaButton1ActionPerformed(evt);
            }
        });

        PalantilAbajoButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sick_pls/Abajo.png"))); // NOI18N
        PalantilAbajoButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PalantilAbajoButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PalantilAbajoButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PalantilAbajoButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PalantilAbajoButton1MouseExited(evt);
            }
        });
        PalantilAbajoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PalantilAbajoButton1ActionPerformed(evt);
            }
        });

        PalantilIzquierdaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sick_pls/Izquierda.png"))); // NOI18N
        PalantilIzquierdaButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PalantilIzquierdaButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PalantilIzquierdaButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PalantilIzquierdaButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PalantilIzquierdaButton1MouseExited(evt);
            }
        });
        PalantilIzquierdaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PalantilIzquierdaButton1ActionPerformed(evt);
            }
        });

        PalantilDerechaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sick_pls/Derecha.png"))); // NOI18N
        PalantilDerechaButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PalantilDerechaButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PalantilDerechaButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PalantilDerechaButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PalantilDerechaButton1MouseExited(evt);
            }
        });
        PalantilDerechaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PalantilDerechaButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(PalantilArribaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(PalantilIzquierdaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PalantilAbajoButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PalantilDerechaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PalantilArribaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PalantilDerechaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PalantilAbajoButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PalantilIzquierdaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Laser Info"));

        LaserDistance1.setMaximum(1000);
        LaserDistance1.setOrientation(1);
        LaserDistance1.setToolTipText("[0 - 10]º");
        LaserDistance1.setValue(1000);

        LaserDistance2.setMaximum(1000);
        LaserDistance2.setOrientation(1);
        LaserDistance2.setToolTipText("[10-20]º");
        LaserDistance2.setValue(1000);

        LaserDistance3.setMaximum(1000);
        LaserDistance3.setOrientation(1);
        LaserDistance3.setToolTipText("[20-30]º");
        LaserDistance3.setValue(1000);

        LaserDistance4.setMaximum(1000);
        LaserDistance4.setOrientation(1);
        LaserDistance4.setToolTipText("[30-40]º");
        LaserDistance4.setValue(1000);

        LaserDistance5.setMaximum(1000);
        LaserDistance5.setOrientation(1);
        LaserDistance5.setToolTipText("[40-50]º");
        LaserDistance5.setValue(1000);

        LaserDistance6.setMaximum(1000);
        LaserDistance6.setOrientation(1);
        LaserDistance6.setToolTipText("[50-60]º");
        LaserDistance6.setValue(1000);

        LaserDistance7.setMaximum(1000);
        LaserDistance7.setOrientation(1);
        LaserDistance7.setToolTipText("[60-70]º");
        LaserDistance7.setValue(1000);

        LaserDistance8.setMaximum(1000);
        LaserDistance8.setOrientation(1);
        LaserDistance8.setToolTipText("[70-80]º");
        LaserDistance8.setValue(1000);

        LaserDistance9.setMaximum(1000);
        LaserDistance9.setOrientation(1);
        LaserDistance9.setToolTipText("[80-90]º");
        LaserDistance9.setValue(1000);

        LaserDistance10.setMaximum(1000);
        LaserDistance10.setOrientation(1);
        LaserDistance10.setToolTipText("[90-100]º");
        LaserDistance10.setValue(1000);

        LaserDistance11.setMaximum(1000);
        LaserDistance11.setOrientation(1);
        LaserDistance11.setToolTipText("[100-110]º");
        LaserDistance11.setValue(1000);

        LaserDistance12.setMaximum(1000);
        LaserDistance12.setOrientation(1);
        LaserDistance12.setToolTipText("[110-120]º");
        LaserDistance12.setValue(1000);

        LaserDistance13.setMaximum(1000);
        LaserDistance13.setOrientation(1);
        LaserDistance13.setToolTipText("[120-130]º");
        LaserDistance13.setValue(1000);

        LaserDistance14.setMaximum(1000);
        LaserDistance14.setOrientation(1);
        LaserDistance14.setToolTipText("[130-140]º");
        LaserDistance14.setValue(1000);

        LaserDistance15.setMaximum(1000);
        LaserDistance15.setOrientation(1);
        LaserDistance15.setToolTipText("[140-150]º");
        LaserDistance15.setValue(1000);

        LaserDistance16.setMaximum(1000);
        LaserDistance16.setOrientation(1);
        LaserDistance16.setToolTipText("[150-160]º");
        LaserDistance16.setValue(1000);

        LaserDistance17.setMaximum(1000);
        LaserDistance17.setOrientation(1);
        LaserDistance17.setToolTipText("[160-170]º");
        LaserDistance17.setValue(1000);

        LaserDistance18.setMaximum(1000);
        LaserDistance18.setOrientation(1);
        LaserDistance18.setToolTipText("[170-180]º");
        LaserDistance18.setValue(1000);

        jLabel7.setText("Izq.                                  Centro                               Der.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(114, 114, 114))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LaserDistance18, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(LaserDistance17, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(LaserDistance16, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance15, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance14, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance13, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance12, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance11, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance10, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance9, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance8, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance6, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(LaserDistance2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserDistance1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(LaserDistance2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LaserDistance5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LaserDistance17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LaserDistance1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel7)
                .addContainerGap())
        );

        StatusText.setText("TEXTO DE ESTADO: ");

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("NFC Info"));

        NFCText.setColumns(20);
        NFCText.setRows(5);
        jScrollPane1.setViewportView(NFCText);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ControlesLayout = new javax.swing.GroupLayout(Controles.getContentPane());
        Controles.getContentPane().setLayout(ControlesLayout);
        ControlesLayout.setHorizontalGroup(
            ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(StatusText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ControlesLayout.createSequentialGroup()
                        .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ControlesLayout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LaserStatusText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ControlesLayout.createSequentialGroup()
                                .addComponent(FrenosRobotButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        ControlesLayout.setVerticalGroup(
            ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlesLayout.createSequentialGroup()
                .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ControlesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FrenosRobotButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControlesLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LaserStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(StatusText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GUI Robot - Práctica Inteligencia Ambiental 2014/2015");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(395, 300));
        setResizable(false);

        jLabel1.setText("Puerto del Laser:");

        TextFieldPalantilPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextFieldPalantilPortMouseClicked(evt);
            }
        });
        TextFieldPalantilPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldPalantilPortKeyReleased(evt);
            }
        });

        jLabel2.setText("Puerto de la cámara:");

        TextFieldRobotPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextFieldRobotPortMouseClicked(evt);
            }
        });
        TextFieldRobotPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldRobotPortKeyReleased(evt);
            }
        });

        jLabel3.setText("Puerto del palantil de la cámara:");

        TextFieldCamaraPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextFieldCamaraPortMouseClicked(evt);
            }
        });
        TextFieldCamaraPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldCamaraPortKeyReleased(evt);
            }
        });

        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TextFieldLaserPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextFieldLaserPortMouseClicked(evt);
            }
        });
        TextFieldLaserPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldLaserPortActionPerformed(evt);
            }
        });
        TextFieldLaserPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldLaserPortKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldLaserPortKeyReleased(evt);
            }
        });

        jLabel4.setText("Puerto del robot:");

        jLabel5.setText("Puerto del RFID:");

        TextFieldRFIDPort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextFieldRFIDPortMouseClicked(evt);
            }
        });
        TextFieldRFIDPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldRFIDPortKeyReleased(evt);
            }
        });

        LaserCheckbox.setText("Enabled");

        RobotCheckbox.setText("Enabled");

        RFIDCheckbox.setText("Enabled");

        PalantilCheckbox.setText("Enabled");

        CamaraCheckbox.setText("Enabled");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextFieldRFIDPort, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addGap(42, 42, 42))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TextFieldLaserPort, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextFieldRobotPort, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextFieldPalantilPort, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextFieldCamaraPort, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LaserCheckbox)
                    .addComponent(RFIDCheckbox)
                    .addComponent(RobotCheckbox)
                    .addComponent(PalantilCheckbox)
                    .addComponent(CamaraCheckbox))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TextFieldLaserPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LaserCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TextFieldCamaraPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CamaraCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TextFieldPalantilPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PalantilCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TextFieldRobotPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RobotCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TextFieldRFIDPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RFIDCheckbox))
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        File file=new File("conf.csv");
        String laser = TextFieldLaserPort.getText();
        String camara = TextFieldCamaraPort.getText();
        String palantil = TextFieldPalantilPort.getText();
        String robot = TextFieldRobotPort.getText();
        String RFID = TextFieldRFIDPort.getText();
        
            
            try {
                PrintWriter pw=new PrintWriter(file);
                
                pw.println("laser;"+laser);
                pw.println("video;"+camara);
                pw.println("palantil;"+palantil);
                pw.println("robot;"+robot);
                pw.println("nfc;"+RFID);
                pw.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
       
        PuertoSerie checkPort = new PuertoSerie();

        boolean error = false;

        // Si no hay ningún checkbox seleccionado no hacemos nada
        if (!LaserCheckbox.isSelected() && !PalantilCheckbox.isSelected() && !CamaraCheckbox.isSelected() && !RobotCheckbox.isSelected() && !RFIDCheckbox.isSelected()) {
            return;
        }

        // Comprobar que los campos seleccionados no están en blanco
        // En caso contrario, pone el campo en rojo para avisar dónde está el error
        if (laser.isEmpty() && LaserCheckbox.isSelected()) {
            TextFieldLaserPort.setBackground(Color.red);
            error = true;
        }
        if (camara.isEmpty() && CamaraCheckbox.isSelected()) {
            TextFieldCamaraPort.setBackground(Color.red);
            error = true;
        }
        if (palantil.isEmpty() && PalantilCheckbox.isSelected()) {
            TextFieldPalantilPort.setBackground(Color.red);
            error = true;
        }
        if (robot.isEmpty() && RobotCheckbox.isSelected()) {
            TextFieldRobotPort.setBackground(Color.red);
            error = true;
        }

        if (RFID.isEmpty() && RFIDCheckbox.isSelected()) {
            TextFieldRFIDPort.setBackground(Color.red);
            error = true;
        }

        if (error) {
            return;
        }

        // Inicializar parámetros  
        puertoCamara = camara;
        puertoLaser = laser;
        puertoPalantil = palantil;
        puertoRobot = robot;
        puertoRFID = RFID;

        // Verificar que cada puerto es correcto.
        // Si un puerto no es correcto o no se establece comunicación
        // se pone de color NARANJA.
        if (LaserCheckbox.isSelected()) {
            if (!checkPort.verifyPort(puertoLaser)) {
                TextFieldLaserPort.setBackground(Color.ORANGE);
                return;
            }
        }

//        if (CamaraCheckbox.isSelected()) {
//            if (!checkPort.verifyPort(puertoCamara)) {
//                TextFieldCamaraPort.setBackground(Color.ORANGE);
//                return;
//            }
//        }

        if (PalantilCheckbox.isSelected()) {
            if (!checkPort.verifyPort(puertoPalantil)) {
                TextFieldPalantilPort.setBackground(Color.ORANGE);
                return;
            }
        }

        if (RFIDCheckbox.isSelected()) {
            if (!checkPort.verifyPort(puertoRFID)) {
                TextFieldRFIDPort.setBackground(Color.ORANGE);
                return;
            }
        }

        if (RobotCheckbox.isSelected()) {
            if (!checkPort.verifyPort(puertoRobot)) {
                TextFieldRobotPort.setBackground(Color.ORANGE);
                return;
            }
        }

        // Inicializar el pool de hilos (Todos los puertos son correctos, empezamos la comunicación)    
        ExecutorService executor = Executors.newCachedThreadPool();

        /* AQUI TENÉIS QUE PONER UNA INSTANCIA DE VUESTRA CLASE, INICIALIZADA CON EL PUERTO Y VELOCIDAD
         CORRESPONDIENTES
         */
        // Instancia del laser

        laserInstance = new SICK(puertoLaser, 9600);

          // Instancia del robot
        robotInstance = new rflex_driver();
        robotInstance.initialize(puertoRobot, 115200);

        // Instancia del palantil

          // Instancia del RFID
        nfcInstance = new NFC(puertoRFID, 9600);


        // Lanzamos los hilos
        if (LaserCheckbox.isSelected()) {
            executor.execute(laserInstance);
        }
        if(RobotCheckbox.isSelected())
        {
            
            executor.execute(robotInstance);
        }
   
        if (PalantilCheckbox.isSelected()) {
            palantilInstance = new Camara(puertoPalantil, 9600);
            executor.execute(palantilInstance);
        }
         if(RFIDCheckbox.isSelected()) 
         {
             executor.execute(nfcInstance);
         }
         
         if(CamaraCheckbox.isSelected())
         {
             Video video=new Video(Integer.parseInt(puertoCamara));
             executor.execute(video);
         }

        // Mostramos el otro panel
        this.setVisible(false);
        Controles.setVisible(true);
        Controles.setAlwaysOnTop(true);
        Controles.setLocationRelativeTo(null);
        if (LaserCheckbox.isSelected()) {
            iniciado = true;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TextFieldLaserPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldLaserPortMouseClicked
        // TODO add your handling code here:
        TextFieldLaserPort.setBackground(Color.WHITE);
    }//GEN-LAST:event_TextFieldLaserPortMouseClicked

    private void TextFieldCamaraPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldCamaraPortMouseClicked
        // TODO add your handling code here:
        TextFieldCamaraPort.setBackground(Color.WHITE);
    }//GEN-LAST:event_TextFieldCamaraPortMouseClicked

    private void TextFieldPalantilPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldPalantilPortMouseClicked
        // TODO add your handling code here:
        TextFieldPalantilPort.setBackground(Color.WHITE);
    }//GEN-LAST:event_TextFieldPalantilPortMouseClicked

    private void TextFieldRobotPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldRobotPortMouseClicked
        // TODO add your handling code here:
        TextFieldRobotPort.setBackground(Color.WHITE);
    }//GEN-LAST:event_TextFieldRobotPortMouseClicked

    private void TextFieldRFIDPortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldRFIDPortMouseClicked
        // TODO add your handling code here:
        TextFieldRFIDPort.setBackground(Color.WHITE);
    }//GEN-LAST:event_TextFieldRFIDPortMouseClicked

    private void TextFieldLaserPortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldLaserPortKeyPressed

    }//GEN-LAST:event_TextFieldLaserPortKeyPressed

    private void RobotArribaButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotArribaButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_RobotArribaButton1MouseEntered

    private void RobotArribaButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotArribaButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_RobotArribaButton1MouseExited

    private void RobotAbajoButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotAbajoButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_RobotAbajoButton1MouseEntered

    private void RobotAbajoButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotAbajoButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_RobotAbajoButton1MouseExited

    private void RobotIzquierdaButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotIzquierdaButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_RobotIzquierdaButton1MouseEntered

    private void RobotIzquierdaButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotIzquierdaButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_RobotIzquierdaButton1MouseExited

    private void RobotDerechaButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotDerechaButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_RobotDerechaButton1MouseEntered

    private void RobotDerechaButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RobotDerechaButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_RobotDerechaButton1MouseExited

    private void FrenosRobotButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FrenosRobotButton1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_FrenosRobotButton1ItemStateChanged

    private void FrenosRobotButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrenosRobotButton1ActionPerformed
        // TODO add your handling code here:
        if(FrenosRobotButton1.isSelected()){
         robotInstance.setBrakePower(false);
         FrenosRobotButton1.setText("BREAKS ON");
              
        } else {
            robotInstance.setBrakePower(true);
         FrenosRobotButton1.setText("BREAKS OFF");
            
        }
    }//GEN-LAST:event_FrenosRobotButton1ActionPerformed

    private void PalantilArribaButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilArribaButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilArribaButton1MouseClicked

    private void PalantilArribaButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilArribaButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilArribaButton1MouseEntered

    private void PalantilArribaButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilArribaButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilArribaButton1MouseExited

    private void PalantilAbajoButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilAbajoButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilAbajoButton1MouseClicked

    private void PalantilAbajoButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilAbajoButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilAbajoButton1MouseEntered

    private void PalantilAbajoButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilAbajoButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilAbajoButton1MouseExited

    private void PalantilIzquierdaButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilIzquierdaButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilIzquierdaButton1MouseClicked

    private void PalantilIzquierdaButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilIzquierdaButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilIzquierdaButton1MouseEntered

    private void PalantilIzquierdaButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilIzquierdaButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilIzquierdaButton1MouseExited

    private void PalantilDerechaButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilDerechaButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilDerechaButton1MouseClicked

    private void PalantilDerechaButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilDerechaButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilDerechaButton1MouseEntered

    private void PalantilDerechaButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PalantilDerechaButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PalantilDerechaButton1MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // Aqui se crea la funcion que se  llama cuando se pulsa el botón de cerrar. Util para cerrar puertos y demás

        if (iniciado) {
            laserInstance.serialPort.sendData(SICK.STOP_SENDING_DATA);
            if(nfcInstance!=null)
                nfcInstance.cerrar = true;
        }
        this.dispose();
        System.exit(0);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void PalantilArribaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PalantilArribaButton1ActionPerformed
        // TODO add your handling code here:
        palantilInstance.enviar(Camara._MOVER_ARRIBA);
    }//GEN-LAST:event_PalantilArribaButton1ActionPerformed

    private void PalantilAbajoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PalantilAbajoButton1ActionPerformed
        // TODO add your handling code here:
        palantilInstance.enviar(Camara._MOVER_ABAJO);
    }//GEN-LAST:event_PalantilAbajoButton1ActionPerformed

    private void PalantilIzquierdaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PalantilIzquierdaButton1ActionPerformed
        // TODO add your handling code here:
        palantilInstance.enviar(Camara._izquierda);
    }//GEN-LAST:event_PalantilIzquierdaButton1ActionPerformed

    private void PalantilDerechaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PalantilDerechaButton1ActionPerformed
        // TODO add your handling code here:
        palantilInstance.enviar(Camara._derecha);
    }//GEN-LAST:event_PalantilDerechaButton1ActionPerformed

    private void TextFieldLaserPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldLaserPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldLaserPortActionPerformed

    private void TextFieldLaserPortKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldLaserPortKeyReleased
        if (!TextFieldLaserPort.getText().isEmpty()) {
            LaserCheckbox.setSelected(true);
        } else {
            LaserCheckbox.setSelected(false);
        }
    }//GEN-LAST:event_TextFieldLaserPortKeyReleased

    private void TextFieldCamaraPortKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldCamaraPortKeyReleased
        if (!TextFieldCamaraPort.getText().isEmpty()) {
            CamaraCheckbox.setSelected(true);
        } else {
            CamaraCheckbox.setSelected(false);
        }
    }//GEN-LAST:event_TextFieldCamaraPortKeyReleased

    private void TextFieldPalantilPortKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldPalantilPortKeyReleased
        if (!TextFieldPalantilPort.getText().isEmpty()) {
            PalantilCheckbox.setSelected(true);
        } else {
            PalantilCheckbox.setSelected(false);
        }
    }//GEN-LAST:event_TextFieldPalantilPortKeyReleased

    private void TextFieldRobotPortKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldRobotPortKeyReleased
        if (!TextFieldRobotPort.getText().isEmpty()) {
            RobotCheckbox.setSelected(true);
        } else {
            RobotCheckbox.setSelected(false);
        }
    }//GEN-LAST:event_TextFieldRobotPortKeyReleased

    private void TextFieldRFIDPortKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldRFIDPortKeyReleased
        if (!TextFieldRFIDPort.getText().isEmpty()) {
            RFIDCheckbox.setSelected(true);
        } else {
            RFIDCheckbox.setSelected(false);
        }
    }//GEN-LAST:event_TextFieldRFIDPortKeyReleased

    private void RobotArribaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RobotArribaButton1ActionPerformed
        // TODO add your handling code here:
        robotInstance.setVelocity(50000, 0, 100000);
    }//GEN-LAST:event_RobotArribaButton1ActionPerformed

    private void RobotIzquierdaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RobotIzquierdaButton1ActionPerformed
        // TODO add your handling code here:
        robotInstance.setVelocity(0, 50000, 100000);
    }//GEN-LAST:event_RobotIzquierdaButton1ActionPerformed

    private void RobotAbajoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RobotAbajoButton1ActionPerformed
        // TODO add your handling code here:
        robotInstance.setVelocity(-50000, 0, 100000);
    }//GEN-LAST:event_RobotAbajoButton1ActionPerformed

    private void RobotDerechaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RobotDerechaButton1ActionPerformed
        // TODO add your handling code here:
        robotInstance.setVelocity(0, -50000, 100000);
    }//GEN-LAST:event_RobotDerechaButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CamaraCheckbox;
    private javax.swing.JFrame Controles;
    private javax.swing.JToggleButton FrenosRobotButton1;
    private javax.swing.JCheckBox LaserCheckbox;
    private static javax.swing.JProgressBar LaserDistance1;
    private static javax.swing.JProgressBar LaserDistance10;
    private static javax.swing.JProgressBar LaserDistance11;
    private static javax.swing.JProgressBar LaserDistance12;
    private static javax.swing.JProgressBar LaserDistance13;
    private static javax.swing.JProgressBar LaserDistance14;
    private static javax.swing.JProgressBar LaserDistance15;
    private static javax.swing.JProgressBar LaserDistance16;
    private static javax.swing.JProgressBar LaserDistance17;
    private static javax.swing.JProgressBar LaserDistance18;
    private static javax.swing.JProgressBar LaserDistance2;
    private static javax.swing.JProgressBar LaserDistance3;
    private static javax.swing.JProgressBar LaserDistance4;
    private static javax.swing.JProgressBar LaserDistance5;
    private static javax.swing.JProgressBar LaserDistance6;
    private static javax.swing.JProgressBar LaserDistance7;
    private static javax.swing.JProgressBar LaserDistance8;
    private static javax.swing.JProgressBar LaserDistance9;
    private static javax.swing.JLabel LaserStatusText;
    private static javax.swing.JTextArea NFCText;
    private javax.swing.JButton PalantilAbajoButton1;
    private javax.swing.JButton PalantilArribaButton1;
    private javax.swing.JCheckBox PalantilCheckbox;
    private javax.swing.JButton PalantilDerechaButton1;
    private javax.swing.JButton PalantilIzquierdaButton1;
    private javax.swing.JCheckBox RFIDCheckbox;
    private javax.swing.JButton RobotAbajoButton1;
    private javax.swing.JButton RobotArribaButton1;
    private javax.swing.JCheckBox RobotCheckbox;
    private javax.swing.JButton RobotDerechaButton1;
    private javax.swing.JButton RobotIzquierdaButton1;
    private static javax.swing.JLabel StatusText;
    private javax.swing.JTextField TextFieldCamaraPort;
    private javax.swing.JTextField TextFieldLaserPort;
    private javax.swing.JTextField TextFieldPalantilPort;
    private javax.swing.JTextField TextFieldRFIDPort;
    private javax.swing.JTextField TextFieldRobotPort;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

package Administrador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administrador extends JFrame{
    private JPanel Principal;
    private JPanel rutasAct;
    private JPanel estuReg;
    private JPanel alertPen;
    private JPanel eficienciaPro;
    private JTextArea rutastextArea1;
    private JPanel Cards;
    private JButton gestionDeRutasButton;
    private JPanel Dashboard;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JPanel Registrar_rutas;
    private JPanel Barra_navegacion;
    private JButton dashboardButton;
    private JButton estudiantesButton;
    private JButton conductoresButton;
    private JButton reportesButton;
    private JButton alertasButton;

    public Administrador(){
        setContentPane(Principal);
        setTitle("Administrador");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        Cards.setLayout(new CardLayout());
        Cards.add(Dashboard, "dashboard");
        Cards.add(Registrar_rutas, "rutas");

        rutasAct.setBorder(new LineBorder(new Color(206,255,253), 2));
        estuReg.setBorder(new LineBorder(new Color(206,255,253), 2));
        alertPen.setBorder(new LineBorder(new Color(206,255,253), 2));
        eficienciaPro.setBorder(new LineBorder(new Color(206,255,253), 2));
        rutastextArea1.setBorder(new LineBorder(new Color(206,255,253), 2));

        gestionDeRutasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("rutas");
            }
        });
    }

    public void mostrarCarta(String nombreCarta) {
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, nombreCarta);
    }
}

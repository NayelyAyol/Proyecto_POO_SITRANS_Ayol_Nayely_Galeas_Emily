package Administrador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Administrador extends JFrame{
    private JPanel Principal;
    private JPanel rutasAct;
    private JPanel estuReg;
    private JPanel alertPen;
    private JPanel eficienciaPro;
    private JTextArea rutastextArea1;
    private JButton dashboardButton;
    private JButton gestiónDeRutasButton;
    private JButton estudiantesButton;
    private JButton conductoresButton;
    private JButton reportesButton;
    private JButton alertasButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JPanel Cards;
    private JPanel Resgistrar;

    public Administrador(){
        setContentPane(Cards);
        setTitle("Administrador");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        rutasAct.setBorder(new LineBorder(new Color(206,255,253), 2));
        estuReg.setBorder(new LineBorder(new Color(206,255,253), 2));
        alertPen.setBorder(new LineBorder(new Color(206,255,253), 2));
        eficienciaPro.setBorder(new LineBorder(new Color(206,255,253), 2));
        rutastextArea1.setBorder(new LineBorder(new Color(206,255,253), 2));


    }

    public void mostrarCarta(String nombreCarta) {
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, String.valueOf(Principal));
    }
}

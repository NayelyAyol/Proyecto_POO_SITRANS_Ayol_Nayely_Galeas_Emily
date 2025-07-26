package Administrador;

import Login.Login;

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
    private JTextField nombreRuta;
    private JTextField origenRuta;
    private JTextField destinoRuta;
    private JComboBox estadoRutaCombo;
    private JPanel Registrar_rutas;
    private JPanel Barra_navegacion;
    private JButton dashboardButton;
    private JButton estudiantesButton;
    private JButton conductoresButton;
    private JButton reportesButton;
    private JButton alertasButton;
    private JPanel nuevaRuta;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JTextField textField3;
    private JComboBox comboBox3;
    private JPanel configuracionExtra;
    private JPanel encabezado;
    private JButton sesionButton;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JPanel registrarEstudiantes;
    private JPanel datosPersonales;
    private JPanel informacionContacto;
    private JPanel listaEstudiantes;
    private JTextField textField2;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox4;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextArea listaEstudiantestextArea;
    private JButton eliminarButtonLista;
    private JButton registrarButtonEtd;
    private JButton limpiarButtonEstd;
    private JPanel lista;

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
        Cards.add(registrarEstudiantes, "estudiantes");

        Barra_navegacion.setBorder(new LineBorder(new Color(0,0,0),1));
        encabezado.setBorder(new LineBorder(new Color(0,0,0),1));
        rutasAct.setBorder(new LineBorder(new Color(206,255,253), 4));
        estuReg.setBorder(new LineBorder(new Color(206,255,253), 4));
        alertPen.setBorder(new LineBorder(new Color(206,255,253), 4));
        eficienciaPro.setBorder(new LineBorder(new Color(206,255,253), 4));
        rutastextArea1.setBorder(new LineBorder(new Color(206,255,253), 4));
        nuevaRuta.setBorder(new LineBorder(new Color(206,255,253),4));
        configuracionExtra.setBorder(new LineBorder(new Color(206,255,253),4));
        sesionButton.setBorder(null);
        guardarButton.setBorder(new LineBorder(new Color(0,0,0),2));
        limpiarButton.setBorder(new LineBorder(new Color(0,0,0),2));
        registrarButtonEtd.setBorder(new LineBorder(new Color(0,0,0),2));
        limpiarButtonEstd.setBorder(new LineBorder(new Color(0,0,0),2));
        eliminarButtonLista.setBorder(new LineBorder(new Color(0,0,0),2));
        datosPersonales.setBorder(new LineBorder(new Color(206,255,253),4));
        informacionContacto.setBorder(new LineBorder(new Color(206,255,253),4));
        listaEstudiantestextArea.setBorder(new LineBorder(new Color(0,0,0),1));

        gestionDeRutasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("rutas");
            }
        });
        sesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null,  "¿Desea cerrar sesión?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                );
                if (opcion == JOptionPane.YES_OPTION){
                    new Login();
                    dispose();
                }
            }
        });
        estudiantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("estudiantes");
            }
        });
    }

    public void mostrarCarta(String nombreCarta) {
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, nombreCarta);
    }
}

package MonitorDeRuta;

import Login.Login;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitorDeRuta extends JFrame{
    private JPanel barraNavegacion;
    private JButton rutaAsignadaButton;
    private JButton estudiantesRegistradosButton;
    private JButton enviarAlertaButton;
    private JPanel Cards;
    private JPanel rutaAsignada;
    private JPanel encabezado;
    private JButton cerrarSesionButton;
    private JPanel Principal;
    private JPanel estudiantesRegistradosPane;
    private JPanel formularioAlertaPanel;
    private JPanel enviarAlertaPanel;
    private JComboBox nombreRutacomboBox;
    private JTextField horaSalidatextField;
    private JTextField horaLlegadatextField;
    private JButton buscarButton;
    private JTable table1;
    private JComboBox tipoAlertaComboBox;
    private JTextArea descripcionTextArea;
    private JButton enviarButton;

    public MonitorDeRuta() {
        setContentPane(Principal);
        setTitle("Monitor de Ruta");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        Cards.add(rutaAsignada, "rutas");
        cerrarSesionButton.setBorder(null);
        encabezado.setBorder(new LineBorder(new Color(0,0,0),1));

        barraNavegacion.setBorder(new LineBorder(new Color(0,0,0),1));
        rutaAsignadaButton.setBorder(null);
        estudiantesRegistradosButton.setBorder(null);
        enviarAlertaButton.setBorder(null);


        Cards.add(estudiantesRegistradosPane, "estudiantes");

        Cards.add(enviarAlertaPanel, "alerta");
        formularioAlertaPanel.setBorder(new LineBorder(new Color(206,255,253),4));

        rutaAsignadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("rutas");
            }
        });
        estudiantesRegistradosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("estudiantes");
            }
        });
        enviarAlertaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("alerta");
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null,
                        "¿Desea cerrar sesión?","Confirmación", JOptionPane.YES_NO_OPTION);

                if (opcion==JOptionPane.YES_OPTION){
                    new Login();
                    dispose();
                }
            }
        });
    }

    public void mostrarCarta(String nombreCarta){
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, nombreCarta);
    }
}

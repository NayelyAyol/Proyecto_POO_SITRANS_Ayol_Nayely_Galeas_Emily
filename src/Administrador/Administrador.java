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
    private JPanel Cards;
    private JButton gestionDeRutasButton;
    private JPanel Dashboard;
    private JTextField nombreRutaTextField;
    private JTextField origenRutaTextField;
    private JTextField destinoRutaTextField;
    private JComboBox estadoRutaComboBox;
    private JPanel registrarRutas;
    private JPanel Barra_navegacion;
    private JButton dashboardButton;
    private JButton estudiantesButton;
    private JButton registroDeConductoresButton;
    private JButton reportesButton;
    private JButton alertasButton;
    private JPanel nuevaRuta;
    private JComboBox conductoComboBox;
    private JComboBox vehiculoComboBox;
    private JTextField capacidadTextField;
    private JTextField horaLlegadaTextField;
    private JComboBox diaComboBox;
    private JPanel configuracionExtra;
    private JPanel encabezado;
    private JButton sesionButton;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JPanel registrarEstudiantes;
    private JPanel datosPersonales;
    private JPanel informacionContacto;
    private JPanel listaEstudiantes;
    private JTextField nombresTextField;
    private JTextField apellidosTextField;
    private JTextField cedulaTextField;
    private JComboBox generoComboBox;
    private JTextField cursoTextField;
    private JTextField telefonoTextField;
    private JTextField correoTextField;
    private JTextField direccionTextField;
    private JButton eliminarListaButton;
    private JButton registrarEtdButton;
    private JButton limpiarEtdButton;
    private JPanel lista;
    private JLabel rutasActivasLabel;
    private JLabel alertasPendientesLabel;
    private JLabel estudiantesRegistradosLabel;
    private JLabel edadPromedioLabel;
    private JPanel dashboardPanel;
    private JPanel registrarConductores;
    private JComboBox tipoSangreComboBox;
    private JTextField numeroLicenciaTextField;
    private JTextField correoConductorTextField;
    private JTextField telefonoConductorTextField;
    private JTextField cedulaConductorTextField;
    private JTextField apellidosConductorTextField;
    private JTextField nombresConductorTextField;
    private JPasswordField conductorContraseniaPasswordField;
    private JPanel formularioRegistroConductoresPanel;
    private JButton registrarConductorButton;
    private JButton limpiarConductorButton;
    private JPanel listaConductoresPanel;
    private JButton eliminarConductorButton;
    private JPanel reportesPanel;
    private JTextField buscarEtdtextField;
    private JPanel totalEstudiantesPanel;
    private JPanel rutasActivasPanel;
    private JPanel asistenciaPromedioPanel;
    private JPanel buscarEstudiantesPanel;
    private JPanel cuadrosInfoPanel;
    private JPanel alertas;
    private JButton atendidaButton;
    private JPanel alertasPanel;
    private JTextField horaSalidaTextField;
    private JTable rutasDashboardTable;
    private JTable listaEstudiantestable;
    private JScrollPane listaScroll;
    private JPanel listaEstudiantesPanel;
    private JTable listaConductoresTable;
    private JScrollPane listaConductoresScroll;
    private JPanel listaConductores;
    private JTable reportesRutaTable;
    private JTable table3;
    private JPanel rutasTableScrollPane;
    private JScrollPane rutasDashScrolll;
    private JPanel reportesRutasPanel;
    private JPanel cuadroReportePanel;
    private JScrollPane reportesScroll;
    private JTable alertasTable;
    private JScrollPane alertasScroll;
    private JPanel alertasSinAtenderPanel;
    private JTextField nRutaTextField;

    public Administrador(){
        setContentPane(Principal);
        setTitle("Administrador");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        Cards.setLayout(new CardLayout());
        Cards.add(Dashboard, "dashboard");
        Cards.add(registrarRutas, "rutas");
        Cards.add(registrarEstudiantes, "estudiantes");
        Cards.add(registrarConductores, "conductores");
        Cards.add(reportesPanel, "reportes");
        Cards.add(alertas,"alertas");

        Barra_navegacion.setBorder(new LineBorder(new Color(0,0,0),1));
        encabezado.setBorder(new LineBorder(new Color(0,0,0),1));
        rutasAct.setBorder(new LineBorder(new Color(206,255,253), 4));
        estuReg.setBorder(new LineBorder(new Color(206,255,253), 4));
        alertPen.setBorder(new LineBorder(new Color(206,255,253), 4));
        eficienciaPro.setBorder(new LineBorder(new Color(206,255,253), 4));
        nuevaRuta.setBorder(new LineBorder(new Color(206,255,253),4));
        configuracionExtra.setBorder(new LineBorder(new Color(206,255,253),4));
        sesionButton.setBorder(null);
        guardarButton.setBorder(new LineBorder(new Color(0,0,0),2));
        limpiarButton.setBorder(new LineBorder(new Color(0,0,0),2));
        registrarEtdButton.setBorder(new LineBorder(new Color(0,0,0),2));
        limpiarEtdButton.setBorder(new LineBorder(new Color(0,0,0),2));
        eliminarListaButton.setBorder(new LineBorder(new Color(0,0,0),2));
        datosPersonales.setBorder(new LineBorder(new Color(206,255,253),4));
        informacionContacto.setBorder(new LineBorder(new Color(206,255,253),4));
        listaConductoresPanel.setBorder(new LineBorder(new Color(206,255,253),4));
        formularioRegistroConductoresPanel.setBorder(new LineBorder(new Color(206,255,253),4));
        registrarConductorButton.setBorder(new LineBorder(new Color(0,0,0),2));
        limpiarConductorButton.setBorder(new LineBorder(new Color(0,0,0),2));
        eliminarConductorButton.setBorder(new LineBorder(new Color(0,0,0),2));

        //Reportes
        totalEstudiantesPanel.setBorder(new LineBorder(new Color(0,0,0),2));
        rutasActivasPanel.setBorder(new LineBorder(new Color(0,0,0),2));
        asistenciaPromedioPanel.setBorder(new LineBorder(new Color(0,0,0),2));
        buscarEstudiantesPanel.setBorder(new LineBorder(new Color(0,0,0),2));


        //Alertas
        alertasPanel.setBorder(new LineBorder(new Color(206,255,253),4));

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
        registroDeConductoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("conductores");
            }
        });
        reportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("reportes");
            }
        });
        alertasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("alertas");
            }
        });
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("dashboard");
            }
        });

        // boton registrar estudiante
        registrarEtdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void mostrarCarta(String nombreCarta) {
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, nombreCarta);
    }

    public void registrarEstudiante(){

    }
}

package MonitorDeRuta;

import Login.Login;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private JTable tableEstudiantesRegistradosRuta;
    private JComboBox tipoAlertaComboBox;
    private JTextArea descripcionTextArea;
    private JButton enviarButton;

    public MonitorDeRuta(int monitorID) {
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

        // cargar datos
        cargarRutasMonitor(monitorID);

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

        // ACCIONES

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // este boton busca y llena los campos: hora de salida y hora de llegada segun el id del combo box con el id y el nombre de la ruta, en el combo box aparecen todas las rutas que se le ha asignado al monitor con este ID
                String seleccion = (String) nombreRutacomboBox.getSelectedItem();
                if (seleccion == null || seleccion.equals("Seleccione una ruta")) return;

                int rutaID = Integer.parseInt(seleccion.split(" - ")[0]);

                try (Connection conn = ConexionMySql.ConexionDB.getConnection();
                     PreparedStatement ps = conn.prepareStatement("SELECT hora_salida, hora_llegada FROM rutas WHERE id = ?")) {

                    ps.setInt(1, rutaID);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        horaSalidatextField.setText(rs.getString("hora_salida"));
                        horaLlegadatextField.setText(rs.getString("hora_llegada"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró información de la ruta.");
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al obtener horas de la ruta: " + ex.getMessage());
                }

                listarEstudiantes();
            }
        });
    }

    public void cargarRutasMonitor(int monitorID) {
        String query = "SELECT id, nombre_ruta FROM rutas WHERE monitor_id = ?";
        try (Connection conn = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, monitorID);
            ResultSet rs = ps.executeQuery();

            nombreRutacomboBox.removeAllItems();
            nombreRutacomboBox.addItem("Seleccione una ruta");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreRuta = rs.getString("nombre_ruta");
                nombreRutacomboBox.addItem(id + " - " + nombreRuta);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar rutas: " + e.getMessage());
        }
    }


    public void mostrarCarta(String nombreCarta){
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, nombreCarta);
    }

    // void para llenar la tabla tableEstudiantesRegistradosRuta segun los estudiantes en la ruta seleccionada en el combo box
    public void listarEstudiantes() {
        String seleccion = (String) nombreRutacomboBox.getSelectedItem();
        if (seleccion == null || seleccion.equals("Seleccione una ruta")) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una ruta");
            return;
        }

        int rutaID = Integer.parseInt(seleccion.split(" - ")[0]);

        String[] columnas = {"ID", "Nombres", "Apellidos", "Curso", "Teléfono"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String query = "SELECT id, nombres, apellidos, curso, telefono FROM estudiantes WHERE ruta_id = ?";

        try (Connection conn = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, rutaID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                        rs.getInt("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("curso"),
                        rs.getString("telefono")
                };
                modelo.addRow(fila);
            }

            tableEstudiantesRegistradosRuta.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al listar estudiantes: " + ex.getMessage());
        }
    }

    public void enviarAlerta(){
        String tipo = (String) tipoAlertaComboBox.getSelectedItem();
        String descripcion = descripcionTextArea.getText();

        if (tipo == null || tipo.equals("Seleccione un tipo de alerta")){
            JOptionPane.showMessageDialog(null, "Por favor escoja un tipo de alerta");
            return;
        }

        if (descripcion.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo de descripción debe ser llenado");
        }

        String query = "insert into alertas (tipo, descripcion) values (?, ?)";

        try (Connection conn = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, tipo);
            ps.setString(2, descripcion);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, "Alerta enviada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo enviar la alerta.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al enviar la alerta: " + ex.getMessage());
        }
    }
}

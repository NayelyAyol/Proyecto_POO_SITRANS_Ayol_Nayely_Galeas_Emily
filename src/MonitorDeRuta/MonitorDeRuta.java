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

/**
 * Ventana principal para el monitor de ruta.
 */
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
    private JComboBox rutaAlertaComboBox;
    private JButton limpiarAlertaButton;


    /**
     * Constructor que inicializa la ventana del monitor.
     *
     * @param monitorID ID del monitor que inicia sesión
     */
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


        /**
         * Accion de los diferentes botones para mostrar las cartas que componen la interfaz
         */
        //Botón para mostrar la carta denominada rutas
        rutaAsignadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("rutas");
            }
        });

        //Botón para mostrar la carta denominada estudiantes
        estudiantesRegistradosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("estudiantes");
            }
        });

        //Botón para mostrar la carta denominada alerta
        enviarAlertaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("alerta");
            }
        });

        //Botón cerrar el panel y volver al Login
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null,
                        "¿Desea cerrar sesión?","Confirmación", JOptionPane.YES_NO_OPTION);

                if (opcion==JOptionPane.YES_OPTION){
                    new Login(); //Se llama a la ventana del login
                    dispose(); //Se cierra la ventana actual
                }
            }
        });

        // ACCIONES

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Este botón busca y llena los campos: hora de salida y hora de llegada segun el id del combo box con el id y el nombre de la ruta, en el combo box aparecen todas las rutas que se le ha asignado al monitor con este ID
                String seleccion = (String) nombreRutacomboBox.getSelectedItem();
                if (seleccion == null || seleccion.equals("Seleccione una ruta")){
                    // Se limpian los campos
                    horaSalidatextField.setText("");
                    horaLlegadatextField.setText("");
                    String[] columnas = {"ID", "Nombres", "Apellidos", "Curso", "Teléfono"};
                    DefaultTableModel modelo = new DefaultTableModel(null, columnas);
                    tableEstudiantesRegistradosRuta.setModel(modelo);
                    return;
                }

                // Se extrae el ID de la ruta seleccionada del combo box
                int rutaID = Integer.parseInt(seleccion.split(" - ")[0]);

                try (Connection conn = ConexionMySql.ConexionDB.getConnection();
                     PreparedStatement ps = conn.prepareStatement("SELECT hora_salida, hora_llegada FROM rutas WHERE id = ?")) {
                    // Se establece el parámetro de la consulta
                    ps.setInt(1, rutaID);
                    ResultSet rs = ps.executeQuery();

                    // Si encuentra la ruta, llenar los campos de hora
                    if (rs.next()) {
                        horaSalidatextField.setText(rs.getString("hora_salida"));
                        horaLlegadatextField.setText(rs.getString("hora_llegada"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró información de la ruta.");
                    }

                } catch (SQLException ex) {
                    //Mensajes de error
                    JOptionPane.showMessageDialog(null, "Error al obtener horas de la ruta: " + ex.getMessage());
                }

                // Cargar la lista de estudiantes de la ruta seleccionada
                listarEstudiantes();
            }
        });

        // Botón que llama al método enviar las alertas al administrador
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarAlerta();
            }
        });

        // Botón que llama al método para limpiar campos
        limpiarAlertaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposAlerta();
            }
        });
    }

    /**
     * Carga las rutas asignadas al monitor en los combo boxes.
     *
     * @param monitorID ID del monitor
     */
    public void cargarRutasMonitor(int monitorID) {
        String query = "SELECT id, nombre_ruta FROM rutas WHERE monitor_id = ?";
        try (Connection conn = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, monitorID);
            ResultSet rs = ps.executeQuery();

            // cargar rutas al ver la informacion de la ruta
            nombreRutacomboBox.removeAllItems();
            nombreRutacomboBox.addItem("Seleccione una ruta");

            // cargar rutas para enviar alerta
            rutaAlertaComboBox.removeAllItems();
            rutaAlertaComboBox.addItem("Seleccione una ruta");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreRuta = rs.getString("nombre_ruta");
                nombreRutacomboBox.addItem(id + " - " + nombreRuta);
                rutaAlertaComboBox.addItem(id + " - " + nombreRuta);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar rutas: " + e.getMessage());
        }
    }


    /**
     * Cambia entre las diferentes pantallas del sistema.
     *
     * @param nombreCarta Nombre de la pantalla a mostrar
     */
    public void mostrarCarta(String nombreCarta){
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, nombreCarta);
    }


    /**
     * Llena la tabla con estudiantes de la ruta seleccionada.
     */
    public void listarEstudiantes() {
        // Obtener la ruta seleccionada del combo box
        String seleccion = (String) nombreRutacomboBox.getSelectedItem();

        // Se valida que se haya seleccionado una ruta válida
        if (seleccion == null || seleccion.equals("Seleccione una ruta")) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una ruta");
            return;
        }

        // Se extrae el ID de la ruta de la selección
        int rutaID = Integer.parseInt(seleccion.split(" - ")[0]);

        // Definicion de las columnas de la tabla
        String[] columnas = {"ID", "Nombres", "Apellidos", "Curso", "Teléfono"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        // Consulta SQL para obtener estudiantes de la ruta
        String query = "SELECT id, nombres, apellidos, curso, telefono FROM estudiantes WHERE ruta_id = ?";

        try (Connection conn = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Establecer el parámetro de la consulta
            ps.setInt(1, rutaID);
            ResultSet rs = ps.executeQuery();

            // Recorrer resultados y agregar filas al modelo
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

            // Asignar el modelo a la tabla
            tableEstudiantesRegistradosRuta.setModel(modelo);

        } catch (SQLException ex) {
            //Se manejan los errores
            JOptionPane.showMessageDialog(this, "Error al listar estudiantes: " + ex.getMessage());
        }
    }

    /**
     * Envía una alerta a la base de datos.
     */
    public void enviarAlerta(){
        // Obtener valores de los campos del formulario
        String tipo = (String) tipoAlertaComboBox.getSelectedItem();
        String descripcion = descripcionTextArea.getText();
        String ruta = (String) rutaAlertaComboBox.getSelectedItem();

        // Se valida que se hayan seleccionado tipo de alerta y ruta
        if (tipo == null || tipo.equals("Seleccione un tipo de alerta") || ruta.equals("Seleccione una ruta")){
            JOptionPane.showMessageDialog(null, "Por favor escoja un tipo de alerta");
            return;
        }
        // Se extrae el ID de la ruta en el combo box
        int rutaID = Integer.parseInt(ruta.split(" - ")[0]);

        // Se valida que la descripción no esté vacía
        if (descripcion.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo de descripción debe ser llenado");
        }

        // Consulta SQL para insertar la alerta
        String query = "insert into alertas (tipo, descripcion, ruta_id) values (?, ?, ?)";

        try (Connection conn = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Establecer los parámetros de la consulta
            ps.setString(1, tipo);
            ps.setString(2, descripcion);
            ps.setInt(3,rutaID);

            // Se ejecuta la inserción
            int resultado = ps.executeUpdate();

            // Se verifica si la inserción fue exitosa
            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, "Alerta enviada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo enviar la alerta.");
            }

        } catch (SQLException ex) {
            //Se manejan lso errores
            JOptionPane.showMessageDialog(this, "Error al enviar la alerta: " + ex.getMessage());
        }
    }

    /**
     * Limpia los campos del formulario de alerta.
     */
    public void limpiarCamposAlerta(){
        tipoAlertaComboBox.setSelectedIndex(0);
        rutaAlertaComboBox.setSelectedIndex(0);
        descripcionTextArea.setText("");
    }
}

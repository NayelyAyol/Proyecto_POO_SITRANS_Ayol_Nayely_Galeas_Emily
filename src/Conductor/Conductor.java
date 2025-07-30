package Conductor;

import ConexionMySql.ConexionDB;
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

public class Conductor extends JFrame {
    private JPanel Principal;
    private JButton cerrarSesionButton;
    private JTabbedPane conductorPanel;
    private JComboBox rutaAsignadaComboBox;
    private JComboBox estadoRutacomboBox;
    private JButton actualizarButton;
    private JTextField zonatextField;
    private JTextField capacidadtextField;
    private JTextField asignadostextField;
    private JTextField horaSalidatextField;
    private JTextField horaEstimadatextField;
    private JTable table1;
    private JScrollPane listaEstudiantesTable;
    private JPanel encabezado;

    public Conductor(int conductorID) {
        setContentPane(Principal);
        setTitle("Conductor");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        //Estilos para el panel
        cerrarSesionButton.setBorder(null);
        encabezado.setBorder(new LineBorder(new Color(0,0,0),1));

        //Configuracion de los JTextField para el panel de Ruta
        zonatextField.setEditable(false);
        capacidadtextField.setEditable(false);
        asignadostextField.setEditable(false);
        horaSalidatextField.setEditable(false);
        horaEstimadatextField.setEditable(false);


        // Cargar datos
        cargarRutas();

        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION){
                    new Login();
                    dispose();
                }
            }
        });

        // listener que funciona cada vez que se selecciona una ruta disponible
        rutaAsignadaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarDatos();
            }
        });

        // actualizar el estado de la ruta
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstadoRuta();
            }
        });
    }
    // permite cargar la información de la base de datos para escoger en el comboBox la ruta deseada
    public void cargarRutas(){
        try (Connection conexion = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement("SELECT id, nombre_ruta FROM rutas");
             ResultSet rs = ps.executeQuery()) {

            rutaAsignadaComboBox.removeAllItems();
            rutaAsignadaComboBox.addItem("");

            while(rs.next()){
                // se extrae el id y nombre_ruta de la consulta del query para mostrarlo en el combo box de rutas asignadas
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_ruta");
                rutaAsignadaComboBox.addItem(id + " - " + nombre);
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error al cargar rutas: " + e.getMessage());
        }
    }

    public void llenarDatos() {
        String seleccion = (String) rutaAsignadaComboBox.getSelectedItem();

        if (seleccion == null || seleccion.isEmpty()){
            limpiarCampos();
            return;
        }

        // Obtener solo el ID de la ruta (antes del guión)
        int rutaID = Integer.parseInt(seleccion.split(" - ")[0]);

        try (Connection conexion = ConexionMySql.ConexionDB.getConnection()) {

            String queryRuta = "SELECT nombre_ruta, capacidad_max, hora_salida, hora_llegada FROM rutas WHERE id = ?";
            // Obtener datos de la ruta
            PreparedStatement psRuta = conexion.prepareStatement(queryRuta);
            psRuta.setInt(1, rutaID);
            ResultSet rsRuta = psRuta.executeQuery();

            if (rsRuta.next()) {
                zonatextField.setText(rsRuta.getString("nombre_ruta"));
                capacidadtextField.setText(rsRuta.getString("capacidad_max"));
                horaSalidatextField.setText(rsRuta.getString("hora_salida"));
                horaEstimadatextField.setText(rsRuta.getString("hora_llegada"));
            }

            String queryEstudiantes = "SELECT COUNT(*) FROM estudiantes WHERE ruta_id = ?";
            // Obtener cantidad de estudiantes asignados a esa ruta
            PreparedStatement psEstudiantes = conexion.prepareStatement(queryEstudiantes);
            psEstudiantes.setInt(1, rutaID);
            ResultSet rsEstudiantes = psEstudiantes.executeQuery();

            if (rsEstudiantes.next()) {
                int cantidad = rsEstudiantes.getInt(1);
                asignadostextField.setText(String.valueOf(cantidad));
            }

            listarEstudiantes(rutaID);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al llenar los datos: " + ex.getMessage());
        }
    }

    public void actualizarEstadoRuta(){
        String seleccion = (String) rutaAsignadaComboBox.getSelectedItem();

        if (seleccion == null || seleccion.isEmpty()){
            limpiarCampos();
            JOptionPane.showMessageDialog(null,"Escoge una ruta para actualizar su estado.");
            return;
        }

        // Obtener solo el ID de la ruta (antes del guión)
        int rutaID = Integer.parseInt(seleccion.split(" - ")[0]);

        String nuevoEstadoRuta = (String) estadoRutacomboBox.getSelectedItem();

        if (nuevoEstadoRuta == null || nuevoEstadoRuta.isEmpty()){
            JOptionPane.showMessageDialog(null,"Escoge un estado para actualizar la ruta.");
            return;
        }
        try (Connection conexion = ConexionMySql.ConexionDB.getConnection()) {
            String query = "UPDATE rutas SET estado_actual = ? WHERE id = ?";
            PreparedStatement psRuta = conexion.prepareStatement(query);
            psRuta.setString(1,nuevoEstadoRuta);
            psRuta.setInt(2, rutaID);
            int filasActualizadas = psRuta.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Estado de la ruta actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la ruta para actualizar.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al llenar los datos: " + ex.getMessage());
        }
    }

    public void limpiarCampos(){
        zonatextField.setText("");
        capacidadtextField.setText("");
        horaSalidatextField.setText("");
        horaEstimadatextField.setText("");
        asignadostextField.setText("");
    }

    // SECCION LISTA ESTUDIANTES A CARGO

    public void listarEstudiantes(int rutaID){
        try (Connection conexion = ConexionMySql.ConexionDB.getConnection()) {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Estudiante");
            modelo.addColumn("Dirección");
            modelo.addColumn("Teléfono");

            String queryEstudiante = "SELECT concat(nombres, ' ', apellidos) AS nombre, direccion, telefono FROM estudiantes WHERE ruta_id = ?";
            // Obtener datos de la ruta
            PreparedStatement psRuta = conexion.prepareStatement(queryEstudiante);
            psRuta.setInt(1, rutaID);
            ResultSet rsRuta = psRuta.executeQuery();

            while (rsRuta.next()) {
                Object[] fila = new Object[3];
                fila[0] = rsRuta.getString("nombre");
                fila[1] = rsRuta.getString("direccion");
                fila[2] = rsRuta.getString("telefono");

                modelo.addRow(fila);
            }
            table1.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al llenar los datos: " + ex.getMessage());
        }

    }


}

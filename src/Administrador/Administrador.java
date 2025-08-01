package Administrador;

import ConexionMySql.ConexionDB;
import Login.Login;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase principal del módulo de administración del sistema de transporte escolar.
 * Permite gestionar rutas, estudiantes, conductores, reportes y alertas.
 * Implementa la interfaz gráfica usando Java Swing y se conecta con MySQL.
 *
 * @author Ayol Nayely, Galeas Emily
 */
public class Administrador extends JFrame{
    /**
     * Componentes de la interfaz
     */
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
    private JComboBox conductorComboBox;
    private JTextField placaVehiculoTextField;
    private JTextField capacidadTextField;
    private JTextField horaSalidaTextField;
    private JComboBox diaComboBox;
    private JPanel configuracionExtra;
    private JPanel encabezado;
    private JButton sesionButton;
    private JButton guardarRutasButton;
    private JButton limpiarRutasButton;
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
    private JLabel conductoresRegistradosLabel;
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
    private JTextField horaLlegadaTextField;
    private JTable rutasDashboardTable;
    private JTable listaEstudiantestable;
    private JScrollPane listaScroll;
    private JPanel listaEstudiantesPanel;
    private JTable listaConductoresTable;
    private JScrollPane listaConductoresScroll;
    private JPanel listaConductores;
    private JTable reportesRutaTable;
    private JTable buscarEstudiantesTable;
    private JPanel rutasTableScrollPane;
    private JScrollPane rutasDashScrolll;
    private JPanel reportesRutasPanel;
    private JPanel cuadroReportePanel;
    private JScrollPane reportesScroll;
    private JTable alertasTable;
    private JScrollPane alertasScroll;
    private JPanel alertasSinAtenderPanel;
    private JComboBox nRutaComboBox;
    private JComboBox monitorComboBox;
    private JLabel totalEstudiantesLabelReportes;
    private JLabel rutasActivasLabelReportes;
    private JLabel totalConductoresLabelReportes;
    private JButton buscarButton;

    /**
     * Constructor principal de la interfaz de administración.
     */
    public Administrador(){
        setContentPane(Principal);
        setTitle("Administrador - Sistema de Transporte Escolar");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Maximiza la ventana
        setLocationRelativeTo(null);
        setVisible(true);

        /*
         * Se configura el CardLayout para manejar las diferentes pantallas.
         * Cada "carta" representa una funcionalidad diferente del sistema.
         */
        Cards.setLayout(new CardLayout());
        Cards.add(Dashboard, "dashboard");
        Cards.add(registrarRutas, "rutas");
        Cards.add(registrarEstudiantes, "estudiantes");
        Cards.add(registrarConductores, "conductores");
        Cards.add(reportesPanel, "reportes");
        Cards.add(alertas,"alertas");

        /*
         * Configuración de bordes para mejorar el diseño.
         * Distintos colores para distintas secciones
         */

        /**
         * Aplica estilos a la barra de navegación.
         */
        Barra_navegacion.setBorder(new LineBorder(new Color(0,0,0),1));

        /**
         * Aplica estilos al encabezado.
         */
        encabezado.setBorder(new LineBorder(new Color(0,0,0),1));
        sesionButton.setBorder(null);

        /**
         * Aplica estilos a los componentes del Dashboard.
         */
        rutasAct.setBorder(new LineBorder(new Color(206,255,253), 4));
        estuReg.setBorder(new LineBorder(new Color(206,255,253), 4));
        alertPen.setBorder(new LineBorder(new Color(206,255,253), 4));
        eficienciaPro.setBorder(new LineBorder(new Color(206,255,253), 4));

        /**
         * Aplica estilos a la sección de rutas.
         */
        nuevaRuta.setBorder(new LineBorder(new Color(206,255,253),4));
        configuracionExtra.setBorder(new LineBorder(new Color(206,255,253),4));
        guardarRutasButton.setBorder(new LineBorder(new Color(0,0,0),2));
        limpiarRutasButton.setBorder(new LineBorder(new Color(0,0,0),2));

        /**
         * Aplica estilos a la sección de estudiantes.
         */
        registrarEtdButton.setBorder(new LineBorder(new Color(0,0,0),2));
        limpiarEtdButton.setBorder(new LineBorder(new Color(0,0,0),2));
        eliminarListaButton.setBorder(new LineBorder(new Color(0,0,0),2));
        datosPersonales.setBorder(new LineBorder(new Color(206,255,253),4));
        informacionContacto.setBorder(new LineBorder(new Color(206,255,253),4));

        /**
         * Aplica estilos a la sección de conductores.
         */
        listaConductoresPanel.setBorder(new LineBorder(new Color(206,255,253),4));
        formularioRegistroConductoresPanel.setBorder(new LineBorder(new Color(206,255,253),4));
        registrarConductorButton.setBorder(new LineBorder(new Color(0,0,0),2));
        limpiarConductorButton.setBorder(new LineBorder(new Color(0,0,0),2));
        eliminarConductorButton.setBorder(new LineBorder(new Color(0,0,0),2));

        /**
         * Aplica estilos a la sección de reportes.
         */
        totalEstudiantesPanel.setBorder(new LineBorder(new Color(0,0,0),2));
        rutasActivasPanel.setBorder(new LineBorder(new Color(0,0,0),2));
        asistenciaPromedioPanel.setBorder(new LineBorder(new Color(0,0,0),2));
        buscarEstudiantesPanel.setBorder(new LineBorder(new Color(0,0,0),2));

        /**
         * Aplica estilos a la sección de alertas.
         */
        alertasPanel.setBorder(new LineBorder(new Color(206,255,253),4));

        /*
         * Carga los datos iniciales necesarios para el funcionamiento del sistema.
         */
        cargarRutasDashboard();
        actualizarEstadisticas();

        /*
         * Configuración de todos los ActionListener para el boton del cierre se sesion (encabezado).
         * Cada botón tiene su funcionalidad específica definida.
         */
        //Botón para cerrar sesión
        sesionButton.addActionListener(new ActionListener() {
            /**
             * Muestra confirmación para cerrar sesión y regresa al login.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null,  "¿Desea cerrar sesión?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                );
                if (opcion == JOptionPane.YES_OPTION){
                    new Login(); //Abre ventana de login
                    dispose(); //Cierra ventana actual
                }
            }
        });

        /*
         * Configuración de todos los ActionListener para los botones de la barra de navegación.
         * Cada botón tiene su funcionalidad específica definida.
         */
        gestionDeRutasButton.addActionListener(new ActionListener() {
            /**
             * Cambia la vista a la pantalla de gestión de rutas.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarMonitoresRutas(); // carga los monitores disponibles en el Combo Box para asginar un monitor a la ruta
                cargarConductoresRutas(); // Carga los conductores disponibles en el Combo Box para asignar rutas
                mostrarCarta("rutas");
            }
        });

        //Botón para la navegación a la carta registrar estudiantes
        estudiantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarEstudiantes(); //Actualiza la tabla de estudiantes
                cargarRutasEstudiantes(); // carga las rutas disponibles en el Combo Box para el registro
                mostrarCarta("estudiantes");
            }
        });

        //Botón para la navegación a la carta de registrar conductores
        registroDeConductoresButton.addActionListener(new ActionListener() {
            /**
             * Carga la lista de conductores y cambia a la vista de conductores.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarConductoresRegistro(); //Actualiza la tabla de conductores
                mostrarCarta("conductores");
            }
        });

        //Botón para la navegación a la carta de reportes
        reportesButton.addActionListener(new ActionListener() {
            /**
             * Cambia la vista a la pantalla de reportes.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCarta("reportes");
                listarRutas(); // lista las rutas con la información detallada
            }
        });

        //Botón para la navegación a la carta alertas
        alertasButton.addActionListener(new ActionListener() {
            /**
             * Cambia la vista a la pantalla de alertas.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAlertas();
                mostrarCarta("alertas");
            }
        });

        //Botón para la navegación a la carta de Dashboard
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarRutasDashboard();
                actualizarEstadisticas();
                mostrarCarta("dashboard");
            }
        });

        /*
         * Configuración de todos los ActionListener para los botones de la Carta Registrar_Estudiantes.
         * Cada botón tiene su funcionalidad específica definida.
         */

        //Botón para registrar estudiante
        registrarEtdButton.addActionListener(new ActionListener() {
            /**
             * Ejecuta el registro de un nuevo estudiante y actualiza la lista.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
                cargarEstudiantes(); //Actualiza la tabla después del registro
            }
        });

        //Botón para eliminar un estudiante
        eliminarListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarRegistroEstudiante();
                cargarEstudiantes();
            }
        });

        //Botón para limpiar campos de estudiantes
        limpiarEtdButton.addActionListener(new ActionListener() {
            /**
             * Limpia todos los campos del formulario de estudiantes y
             * resetea los ComboBox a su estado inicial.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //Limpia todos los campos de texto
                nombresTextField.setText("");
                apellidosTextField.setText("");
                cedulaTextField.setText("");
                cursoTextField.setText("");
                telefonoTextField.setText("");
                correoTextField.setText("");
                direccionTextField.setText("");

                //Devuelve los ComboBox al primer elemento (vacío)
                generoComboBox.setSelectedIndex(0);
                nRutaComboBox.setSelectedIndex(0);
            }
        });

        /*
         * Configuración de todos los ActionListener para los botones de la Carta Gestión de rutas.
         * Cada botón tiene su funcionalidad específica definida.
         */

        //Botón para guardar ruta
        guardarRutasButton.addActionListener(new ActionListener() {
            /**
             * Ejecuta el registro de una nueva ruta.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarRutas();
            }
        });

        //Botón para limpiar campos de rutas
        limpiarRutasButton.addActionListener(new ActionListener() {
            /**
             * Limpia todos los campos del formulario de rutas.
             * Este listener maneja tanto el botón de rutas como el general.
             * @param e evento de acción del botón
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //Se limpian los campos de texto
                nombreRutaTextField.setText("");
                origenRutaTextField.setText("");
                destinoRutaTextField.setText("");
                placaVehiculoTextField.setText("");
                capacidadTextField.setText("");
                horaLlegadaTextField.setText("");
                horaSalidaTextField.setText("");

                //Se resetea el ComboBox
                estadoRutaComboBox.setSelectedIndex(0);
                diaComboBox.setSelectedIndex(0);
                conductorComboBox.setSelectedIndex(0);
                monitorComboBox.setSelectedIndex(0);
            }
        });

        // SECCION CONDUCTORES
        registrarConductorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarConductores();
            }
        });

        eliminarConductorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarRegistroConductor();
                cargarConductoresRegistro();
            }
        });

        limpiarConductorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombresConductorTextField.setText("");
                apellidosConductorTextField.setText("");
                cedulaConductorTextField.setText("");
                telefonoConductorTextField.setText("");
                correoConductorTextField.setText("");
                conductorContraseniaPasswordField.setText("");
                numeroLicenciaTextField.setText("");
                tipoSangreComboBox.setSelectedIndex(0);
            }
        });

        atendidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atenderAlerta();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiantes();
            }
        });
    }

    /**
     * Muestra la carta especificada
     * @param nombreCarta el nombre de la carta a mostrar
     */
    public void mostrarCarta(String nombreCarta) {
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, nombreCarta);
    }

    // SECCIÓN CONDUCTORES

    /**
     * Carga los conductores registrados en la tabla de la interfaz.
     */
     public void cargarConductoresRegistro(){
        String[] columnas = {"ID","Nombre","Teléfono","Correo","Licencia"};
        DefaultTableModel modelo = new DefaultTableModel(null,columnas);

        String query = "SELECT id, concat(nombres, ' ', apellidos) as nombre, telefono, correo, n_licencia from conductores";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("telefono");
                fila[3] = rs.getString("correo");
                fila[4] = rs.getString("n_licencia");
                modelo.addRow(fila);
            }

            listaConductoresTable.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar conductores: " + e.getMessage());
        }
    }

    /**
     * Elimina el conductor seleccionado. Solicita confirmación al usuario.
     * Verifica si tiene rutas asignadas antes de eliminar.
     */
    public void eliminarRegistroConductor() {
        int filaSeleccionada = listaConductoresTable.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un registro para eliminar.");
            return;
        }

        // Se extrae el ID del conductor desde la tabla
        int conductorID = (int) listaConductoresTable.getValueAt(filaSeleccionada, 0);

        // Verifica si el conductor tiene rutas asignadas
        String consultaVerificacion = "SELECT COUNT(*) AS total FROM rutas WHERE conductor_id = ?";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement psVerificar = conexion.prepareStatement(consultaVerificacion)) {

            psVerificar.setInt(1, conductorID);
            ResultSet rs = psVerificar.executeQuery();

            if (rs.next() && rs.getInt("total") > 0) {
                JOptionPane.showMessageDialog(null,
                        "No se puede eliminar el conductor porque tiene rutas asignadas.\n" +
                                "Debe reasignarlas o eliminarlas primero.",
                        "Conductor en uso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de eliminar este registro?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                PreparedStatement psEliminar = conexion.prepareStatement("DELETE FROM conductores WHERE id = ?");
                psEliminar.setInt(1, conductorID);
                int resultado = psEliminar.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Conductor eliminado correctamente.");
                    actualizarEstadisticas(); // Si tienes este método en tu clase
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el conductor.");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: " + ex.getMessage());
        }
    }


    // SECCIÓN ESTUDIANTES

    /**
     * Carga las rutas disponibles en el ComboBox para registro de estudiantes.
     */
     public void cargarRutasEstudiantes(){
        try (Connection conexion = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement("SELECT id, nombre_ruta FROM rutas");
             ResultSet rs = ps.executeQuery()) {

            nRutaComboBox.removeAllItems();
            nRutaComboBox.addItem("");

            while(rs.next()){
                // agrega el texto con id y nombre para que se vea y poder extraer el id de la ruta en la base de datos
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre_ruta");
                nRutaComboBox.addItem(id + " - " + nombre);
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error al cargar rutas: " + e.getMessage());
        }
    }

    /**
     * Registra un nuevo estudiante en la base de datos.
     * Valida que todos los campos estén completos para proceder con el registro.
     * Extrae el ID de la ruta seleccionada y ejecuta la inserción en la base de datos.
     */
    public void registrarEstudiante(){
        //Se obtiene y limpia los datos de los campos de texto
        String nombres = nombresTextField.getText().trim();
        String apellidos = apellidosTextField.getText().trim();
        String cedula = cedulaTextField.getText().trim();
        String genero = (String) generoComboBox.getSelectedItem();
        String curso = cursoTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();
        String correo = correoTextField.getText().trim();
        String direccion = direccionTextField.getText();

        //Validación de campos completos
        if(nombres.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || genero.isEmpty() || curso.isEmpty()
                || telefono.isEmpty() || correo.isEmpty() || direccion.isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        } else if(cedula.length() != 10){
            JOptionPane.showMessageDialog(null,"Ingrese un número de cédula válido,");
            return;
        }

        //Validación de selección de ruta
        if (nRutaComboBox.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null,"Seleccione una ruta válida.");
            return;
        }

        //Se extrae el ID de la ruta del formato "ID - Nombre"
        String ruta = (String) nRutaComboBox.getSelectedItem();
        int rutaID = Integer.parseInt(ruta.split(" - ")[0]);

        //Query SQL para insertar el nuevo estudiante
        String query = "INSERT INTO estudiantes (nombres, apellidos, cedula, genero, curso, telefono, correo, direccion, ruta_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(query)){
            //Se asignan los parámetros al PreparedStatement
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, cedula);
            ps.setString(4, genero);
            ps.setString(5, curso);
            ps.setString(6, telefono);
            ps.setString(7, correo);
            ps.setString(8, direccion);
            ps.setInt(9, rutaID);

            //Ejecuta la inserción
            int resultado = ps.executeUpdate();

            //Verifica el resultado
            if (resultado>0){
                JOptionPane.showMessageDialog(null,"Estudiante registrado exitosamente.");
                actualizarEstadisticas();
            }else {
                JOptionPane.showMessageDialog(null,"Error al registrar estudiante.");
            }
        }catch (SQLException e){
            //Manejo de errores de base de datos
            JOptionPane.showMessageDialog(null,"Error en la base de datos " + e.getMessage());
        }
    }

    /**
     * Elimina el estudiante seleccionado. Solicita confirmación.
     */
    public void eliminarRegistroEstudiante(){
        int filaSeleecionada = listaEstudiantestable.getSelectedRow();

        if (filaSeleecionada == -1){
            JOptionPane.showMessageDialog(null,"Selecciona un registro para eliminar.");
            return;
        }

        // se extrae el id del estudiante de la fila seleccionada y la columna en donde se encuentra su ID
        int estudianteID = (int) listaEstudiantestable.getValueAt(filaSeleecionada,0);

        int confirmacion = JOptionPane.showConfirmDialog(null,"¿Estás seguro de eliminar este registro?","Confirmar",JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try (Connection conexion = ConexionDB.getConnection();
                 PreparedStatement ps = conexion.prepareStatement("DELETE FROM estudiantes WHERE id = ?")) {

                ps.setInt(1, estudianteID);
                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente.");
                    actualizarEstadisticas(); //Se actualizan las estadisticas del dashboard
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el estudiante.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error de base de datos: " + ex.getMessage());
            }
        }
    }

    // SECCION RUTAS

    /**
     * Carga conductores disponibles en ComboBox para asignar a rutas.
     */
    public void cargarConductoresRutas(){
        try (Connection conexion = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement("SELECT id, concat(nombres, ' ', apellidos) as nombre FROM conductores");
             ResultSet rs = ps.executeQuery()) {

            conductorComboBox.removeAllItems();
            conductorComboBox.addItem("");

            while(rs.next()){
                // se extrae el id y nombre de la consulta del query para mostrarlo en el combo box de conductores
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                conductorComboBox.addItem(id + " - " + nombre);
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error al cargar conductores: " + e.getMessage());
        }
    }

    /**
     * Carga monitores disponibles en ComboBox para asignar a rutas.
     */
    public void cargarMonitoresRutas(){
        try (Connection conexion = ConexionMySql.ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement("SELECT id, concat(nombres, ' ', apellidos) as nombre FROM monitores");
             ResultSet rs = ps.executeQuery()) {

            monitorComboBox.removeAllItems();
            monitorComboBox.addItem("");

            while(rs.next()){
                // se extrae el id y nombre de la consulta del query para mostrarlo en el combo box de monitores
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                monitorComboBox.addItem(id + " - " + nombre);
            }

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error al cargar monitores: " + e.getMessage());
        }
    }

    /**
     * Registra una nueva ruta en la base de datos del sistema de transporte escolar.
     * Este método valida todos los campos obligatorios del formulario
     */
    public void registrarRutas(){
        //Se obtiene y limpia los datos de los campos
        String nombre_ruta = nombreRutaTextField.getText().trim();
        String origen = origenRutaTextField.getText().trim();
        String destino = destinoRutaTextField.getText().trim();
        String conductor = (String) conductorComboBox.getSelectedItem();
        String monitor = (String) monitorComboBox.getSelectedItem();
        String placa = placaVehiculoTextField.getText();
        String estado = (String) estadoRutaComboBox.getSelectedItem();
        String capacidad_max = capacidadTextField.getText();
        String dia = (String) diaComboBox.getSelectedItem();
        String hora_salida = horaSalidaTextField.getText();
        String hora_llegada = horaLlegadaTextField.getText();

        //Validacion para que los campos esten completos
        if(nombre_ruta.isEmpty() || origen.isEmpty() || destino.isEmpty() || conductor.isEmpty() || monitor.isEmpty() || placa.isEmpty()
                || estado.isEmpty() || capacidad_max.isEmpty() || dia.isEmpty() || hora_salida.isEmpty() || hora_llegada.isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

        int capacidad;
        try {
            //Se convierte la capacidad de String a entero
            capacidad = Integer.parseInt(capacidad_max);
        }catch (NumberFormatException ex){
            //Captura de errores cuando el usuario ingresa texto no numérico
            JOptionPane.showMessageDialog(null,"La capacidad debe ser un número entero.");
            return;
        }

        //Validación del límite máximo de capacidad
        if (capacidad>30){
            JOptionPane.showMessageDialog(null,"El transporte no debe exceder 30 usuarios de capacidad.");
            return;
        }

        //Validación de la capacidad mínima
        if (capacidad<0) {
            JOptionPane.showMessageDialog(null,"La capacidad no puede ser negativa.");
            return;
        }

        // Validación de las horas reales con su formato correcto


        if (!validarHora(hora_salida, hora_llegada)) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese horas válidas en formato HH:mm.\n" +
                            "Deben estar entre 05:00 y 21:00 y la llegada debe ser después de la salida.");
            return;
        }

        //Se extrae el ID del monitor para agregarlo correctamente en la base.
        int monitorID = Integer.parseInt(monitor.split(" - ")[0]);

        //Se extrae el ID del conductor para agregarlo correctamente en la base.
        int conductorID = Integer.parseInt(conductor.split(" - ")[0]);

        //Query para insertar una nueva ruta
        String query = "INSERT INTO rutas (nombre_ruta, placas_vehiculo, origen, destino, capacidad_max, dia, hora_salida, hora_llegada, monitor_id, conductor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(query)){
            //Asignación de parámetros
            ps.setString(1, nombre_ruta);
            ps.setString(2,placa);
            ps.setString(3, origen);
            ps.setString(4, destino);
            ps.setInt(5, capacidad);
            ps.setString(6, dia);
            ps.setString(7, hora_salida);
            ps.setString(8, hora_llegada);
            ps.setInt(9,monitorID);
            ps.setInt(10, conductorID);

            //Un valor > 0 indica que la inserción fue exitosa
            int resultado = ps.executeUpdate();

            //Se informa al usuario que la ruta fue registrada correctamente en el sistema
            if (resultado>0){
                JOptionPane.showMessageDialog(null,"Ruta registrada exitosamente.");
                actualizarEstadisticas();
            }else {
                JOptionPane.showMessageDialog(null,"Error al registrar ruta.");
            }
        }catch (SQLException e){
            //Manejo de errores
            JOptionPane.showMessageDialog(null,"Error en la base de datos " + e.getMessage());
        }
    }

    // Metodo para validar la hora al registrar ruta

    /**
     * Valida que la hora de salida y llegada estén en formato válido (HH:mm),
     * en el rango 05:00 a 21:00, y que la llegada sea posterior a la salida.
     *
     * @param salida hora de salida
     * @param llegada hora de llegada
     * @return true si las horas son válidas y en orden correcto; false si no
     */
    public boolean validarHora(String salida, String llegada) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime horaSalida = LocalTime.parse(salida, formatter);
            LocalTime horaLlegada = LocalTime.parse(llegada, formatter);

            LocalTime inicio = LocalTime.of(5, 0);
            LocalTime fin = LocalTime.of(21, 0);

            boolean salidaValida = !horaSalida.isBefore(inicio) && !horaSalida.isAfter(fin);
            boolean llegadaValida = !horaLlegada.isBefore(inicio) && !horaLlegada.isAfter(fin);
            boolean ordenCorrecto = horaLlegada.isAfter(horaSalida);

            return salidaValida && llegadaValida && ordenCorrecto;

        } catch (DateTimeParseException e) {
            return false;
        }
    }


    // SECCION DASHBOARD
    /**
     * Carga rutas en la tabla del dashboard principal.
     */
     public void cargarRutasDashboard(){
        String[] columnas = {"Ruta", "Origen","Destino","Dia","Hora Salida","Hora Llegada", "Estado Actual"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String query = "SELECT nombre_ruta, origen, destino, dia, hora_salida, hora_llegada, estado_actual from rutas";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getString("nombre_ruta");
                fila[1] = rs.getString("origen");
                fila[2] = rs.getString("destino");
                fila[3] = rs.getString("dia");
                fila[4] = rs.getString("hora_salida");
                fila[5] = rs.getString("hora_llegada");
                fila[6] = rs.getString("estado_actual");
                modelo.addRow(fila);
            }

            rutasDashboardTable.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar rutas: " + e.getMessage());
        }
    }

    /**
     * Carga lista completa de estudiantes con información de rutas.
     */
    public void cargarEstudiantes() {

        String[] columnas = {"ID", "Nombres", "Apellidos","Cédula", "Género","Curso","Teléfono","Correo","Dirección","Ruta"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String query = "SELECT e.id, e.nombres, e.apellidos, e.cedula, e.genero, e.curso, e.telefono, e.correo, e.direccion, r.nombre_ruta " +
                "FROM estudiantes e INNER JOIN rutas r ON e.ruta_id = r.id";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[10];
                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("nombres");
                fila[2] = rs.getString("apellidos");
                fila[3] = rs.getString("cedula");
                fila[4] = rs.getString("genero");
                fila[5] = rs.getString("curso");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getString("correo");
                fila[8] = rs.getString("direccion");
                fila[9] = rs.getString("nombre_ruta");

                modelo.addRow(fila);
            }

            listaEstudiantestable.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar estudiantes: " + e.getMessage());
        }
    }

    /**
     * Registra un nuevo conductor en la base de datos.
     * Valida que todos los campos estén completos y ejecuta la inserción.
     * Después del registro actualiza la lista de conductores mostrada.
     */
    public void registrarConductores(){
        //Se obtiene y limpia los datos de los campos
        String nombres = nombresConductorTextField.getText().trim();
        String apellidos = apellidosConductorTextField.getText().trim();
        String cedula = cedulaConductorTextField.getText().trim();
        String telefono = telefonoConductorTextField.getText().trim();
        String correo = correoConductorTextField.getText().trim();
        String clave = String.valueOf(conductorContraseniaPasswordField.getPassword());
        String n_licencia = numeroLicenciaTextField.getText().trim();
        String tipo_sangre = (String) tipoSangreComboBox.getSelectedItem();

        //Validación para que los campos esten completos
        if (nombres.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || correo.isEmpty() || clave.isEmpty() || n_licencia.isEmpty() || tipo_sangre.isEmpty()){
            JOptionPane.showMessageDialog(null,"Por favor, llene los campos.");
            return;
        }

        //Query SQL para insertar el nuevo conductor
        String query = "INSERT INTO conductores (nombres, apellidos, cedula, telefono, correo, clave, n_licencia, tipo_sangre ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        //Se ejecuta la inserción
        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(query)){
            //Se asignan los parámetros
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, cedula);
            ps.setString(4, telefono);
            ps.setString(5, correo);
            ps.setString(6, clave);
            ps.setString(7, n_licencia);
            ps.setString(8, tipo_sangre);

            int resultado = ps.executeUpdate();

            //Se verifica el resultado
            if (resultado>0){
                JOptionPane.showMessageDialog(null,"Conductor registrado exitosamente.");
            }else {
                JOptionPane.showMessageDialog(null,"Error al registrar conductor.");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la base de datos " + e.getMessage());
        }
        //Se actualiza la lista de conductores después del registro
        cargarConductoresRegistro();
    }

    //Seccion Reportes
    /**
     * Lista todas las rutas con información detallada.
     */
    public void listarRutas(){
        String[] columnas =  {"ID","Ruta","Placas del vehículo","Destino","Día","Hora de salida","Hora de llegada", "Estado","Monitor","Conductor"};
        DefaultTableModel model = new DefaultTableModel(null, columnas);

        String query = "Select * from rutas";

        try(Connection conexion = ConexionDB.getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                Object[] fila = new Object[10];
                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("nombre_ruta");
                fila[2] = rs.getString("placas_vehiculo");
                fila[3] = rs.getString("destino");
                fila[4] = rs.getString("dia");
                fila[5] = rs.getString("hora_salida");
                fila[6] = rs.getString("hora_llegada");
                fila[7] = rs.getString("estado_actual");
                fila[8] = rs.getInt("monitor_id");
                fila[9] = rs.getInt("conductor_id");

                model.addRow(fila);
            }

            reportesRutaTable.setModel(model);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar rutas: " + e.getMessage());
        }
    }

    /**
     * Busca estudiantes por cédula usando coincidencia parcial.
     */
    public void buscarEstudiantes(){
        String cedula = buscarEtdtextField.getText().trim();

        if (cedula.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,"Ingrese la cédula del estudiante.");
            return;
        }else if (cedula.length() != 10){
            JOptionPane.showMessageDialog(null,"Ingrese un número de cédula válido.");
            return;
        }

        String[] columnas = {"ID", "Nombres","Apellidos", "Cédula", "Curso", "Dirección"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String query = "select * from estudiantes where cedula like ?";
        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(query)) {

            ps.setString(1, "%" + cedula + "%"); // Coincidencia parcial

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[6];
                    fila[0] = rs.getInt("id");
                    fila[1] = rs.getString("nombres");
                    fila[2] = rs.getString("apellidos");
                    fila[3] = rs.getString("cedula");
                    fila[4] = rs.getString("curso");
                    fila[5] = rs.getString("direccion");

                    modelo.addRow(fila);
                }

                buscarEstudiantesTable.setModel(modelo); //Se muestran los resultados
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar estudiante por cédula: " + e.getMessage());
        }

    }


    //Seccion Alertas
    /**
     * Carga y muestra todas las alertas del sistema.
     */
    public void mostrarAlertas(){
        String[] columnas = {"ID", "Ruta","Tipo de Alerta", "Fecha","Descripción", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String query = "Select a.id, a.tipo, a.fecha, a.descripcion, a.estado,r.nombre_ruta from alertas a join rutas r on a.ruta_id = r.id";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("nombre_ruta");
                fila[2] = rs.getString("tipo");
                fila[3] = rs.getString("fecha");
                fila[4] = rs.getString("descripcion");
                fila[5] = rs.getString("estado");
                modelo.addRow(fila);
            }
            alertasTable.setModel(modelo);

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos "+e.getMessage());
        }
    }

    /**
     * Marca la alerta seleccionada como "Atendida".
     */
    public void atenderAlerta(){
        int registro = alertasTable.getSelectedRow();

        if (registro == -1){
            JOptionPane.showMessageDialog(null,"Escoje una alerta para dar por atendida.");
            return;
        }

        int idAlerta = (int) alertasTable.getValueAt(registro, 0);
        String query = "update alertas set estado = 'Atendida' where id = ?";

        try(Connection conexion = ConexionDB.getConnection();
            PreparedStatement ps = conexion.prepareStatement(query)){

            ps.setInt(1, idAlerta);
            int filaSeleccionada = ps.executeUpdate();

            if (filaSeleccionada > 0){
                JOptionPane.showMessageDialog(null, "Alerta marcada como atendida");
                mostrarAlertas();
                actualizarEstadisticas();
            }
            else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la alerta");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar la alerta: "+ e.getMessage());
        }

    }


    /**
     * Actualiza todas las estadísticas del dashboard.
     */
    public void actualizarEstadisticas(){
        actualizarRutasActivas();
        actualizarAlertasPendientes();
        actualizarEstudiantesRegistrados();
        actualizarConductoresRegistrados();
    }

    /**
     * Cuenta y actualiza el número de rutas activas.
     */
    public void  actualizarRutasActivas(){
        String query = "select count(*) from rutas where estado_actual= 'En progreso'";
        try(Connection conexion = ConexionDB.getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){
            if (rs.next()){
                rutasActivasLabel.setText(rs.getString(1));
                rutasActivasLabelReportes.setText(rs.getString(1));
            }

        }catch (SQLException e){
            rutasActivasLabel.setText("0");
            rutasActivasLabelReportes.setText("0");
        }

    }

    /**
     * Cuenta y actualiza el número de alertas pendientes.
     */
    public void actualizarAlertasPendientes(){
        String query = "select count(*) from alertas where estado = 'Pendiente'";
        try(Connection conexion = ConexionDB.getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){

            if (rs.next()){
                alertasPendientesLabel.setText(rs.getString(1));
            }
        }catch (SQLException e){
            alertasPendientesLabel.setText("0");
        }
    }

    /**
     * Cuenta y actualiza el total de estudiantes registrados.
     */
    public void actualizarEstudiantesRegistrados(){
        String query = "select count(*) from estudiantes";
        try(Connection conexion = ConexionDB.getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){
            if (rs.next()){
                estudiantesRegistradosLabel.setText(rs.getString(1));
                totalEstudiantesLabelReportes.setText(rs.getString(1));
            }
        }catch (SQLException e){
            estudiantesRegistradosLabel.setText("0");
            totalEstudiantesLabelReportes.setText("0");
        }

    }

    /**
     * Cuenta y actualiza el total de conductores registrados.
     */
    public void actualizarConductoresRegistrados(){
        String query = "select count(*) from conductores";

        try (Connection conexion = ConexionDB.getConnection();
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){

            if (rs.next()){
                conductoresRegistradosLabel.setText(rs.getString(1));
                totalConductoresLabelReportes.setText(rs.getString(1));
            }

        }catch (SQLException e){
            conductoresRegistradosLabel.setText("0");
            totalConductoresLabelReportes.setText("0");
        }

    }
}
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
    private JTextField vehiculoTextField;
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
    private JComboBox nRutaComboBox;

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

        // Cargar datos
        cargarRutasEstudiantes();
        cargarRutasDashboard();

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
                cargarEstudiantes();
                mostrarCarta("estudiantes");
            }
        });
        registroDeConductoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarConductoresRegistro();
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
                cargarRutasDashboard();
                mostrarCarta("dashboard");
            }
        });

        // boton registrar estudiante
        registrarEtdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
                cargarEstudiantes();
            }
        });

        // boton limpiar campos en rutas
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreRutaTextField.setText("");
                origenRutaTextField.setText("");
                destinoRutaTextField.setText("");
                conductoComboBox.setSelectedIndex(0);
                vehiculoTextField.setText("");
                estadoRutaComboBox.setSelectedIndex(0);
            }
        });

        // boton limpiar campos en estudiantes
        limpiarEtdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombresTextField.setText("");
                apellidosTextField.setText("");
                cedulaTextField.setText("");
                cursoTextField.setText("");
                telefonoTextField.setText("");
                correoTextField.setText("");
                direccionTextField.setText("");
                generoComboBox.setSelectedIndex(0);
                nRutaComboBox.setSelectedIndex(0);
            }
        });

        registrarConductorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarConductores();
            }
        });
    }

    public void mostrarCarta(String nombreCarta) {
        CardLayout cl = (CardLayout) Cards.getLayout();
        cl.show(Cards, nombreCarta);
    }

    public void registrarEstudiante(){
        String nombres = nombresTextField.getText().trim();
        String apellidos = apellidosTextField.getText().trim();
        String cedula = cedulaTextField.getText().trim();
        String genero = (String) generoComboBox.getSelectedItem();
        String curso = cursoTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();
        String correo = correoTextField.getText().trim();
        String direccion = direccionTextField.getText();

        if(nombres.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || genero.isEmpty() || curso.isEmpty()
                || telefono.isEmpty() || correo.isEmpty() || direccion.isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

        if (nRutaComboBox.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null,"Seleccione una ruta válida.");
            return;
        }

        String ruta = (String) nRutaComboBox.getSelectedItem();

        int rutaID = Integer.parseInt(ruta.split(" - ")[0]);

        String query = "INSERT INTO estudiantes (nombres, apellidos, cedula, genero, curso, telefono, correo, direccion, ruta_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, cedula);
            ps.setString(4, genero);
            ps.setString(5, curso);
            ps.setString(6, telefono);
            ps.setString(7, correo);
            ps.setString(8, direccion);
            ps.setInt(9, rutaID);

            int resultado = ps.executeUpdate();

            if (resultado>0){
                JOptionPane.showMessageDialog(null,"Estudiante registrado exitosamente.");
            }else {
                JOptionPane.showMessageDialog(null,"Error al registrar estudiante.");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la base de datos " + e.getMessage());
        }

    }

    public void registrarConductores(){
        String nombres = nombresConductorTextField.getText().trim();
        String apellidos = apellidosConductorTextField.getText().trim();
        String cedula = cedulaConductorTextField.getText().trim();
        String telefono = telefonoConductorTextField.getText().trim();
        String correo = correoConductorTextField.getText().trim();
        String clave = String.valueOf(conductorContraseniaPasswordField.getPassword());
        String n_licencia = numeroLicenciaTextField.getText().trim();
        String tipo_sangre = (String) tipoSangreComboBox.getSelectedItem();

        if (nombres.isEmpty() || apellidos.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || correo.isEmpty() || clave.isEmpty() || n_licencia.isEmpty() || tipo_sangre.isEmpty()){
            JOptionPane.showMessageDialog(null,"Por favor, llene los campos.");
            return;
        }

        String query = "INSERT INTO conductores (nombres, apellidos, cedula, telefono, correo, clave, n_licencia, tipo_sangre ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, cedula);
            ps.setString(4, telefono);
            ps.setString(5, correo);
            ps.setString(6, clave);
            ps.setString(7, n_licencia);
            ps.setString(8, tipo_sangre);

            int resultado = ps.executeUpdate();

            if (resultado>0){
                JOptionPane.showMessageDialog(null,"Conductor registrado exitosamente.");
            }else {
                JOptionPane.showMessageDialog(null,"Error al registrar conductor.");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la base de datos " + e.getMessage());
        }
        cargarConductoresRegistro();
    }

    // cargar la lista de conductores
    public void cargarConductoresRegistro(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Correo");
        modelo.addColumn("Licencia");

        String query = "SELECT concat(nombres, ' ', apellidos) as nombre, telefono, correo, n_licencia from conductores";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("telefono");
                fila[2] = rs.getString("correo");
                fila[3] = rs.getString("n_licencia");
                modelo.addRow(fila);
            }

            listaConductoresTable.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar rutas: " + e.getMessage());
        }
    }

    // para cargar rutas al momento de registrar estudiantes
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

    public void cargarRutasDashboard(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Ruta");
        modelo.addColumn("Origen");
        modelo.addColumn("Destino");
        modelo.addColumn("Dia");
        modelo.addColumn("Hora Salida");
        modelo.addColumn("Hora Llegada");

        String query = "SELECT nombre_ruta, origen, destino, dia, hora_salida, hora_llegada from rutas";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("nombre_ruta");
                fila[1] = rs.getString("origen");
                fila[2] = rs.getString("destino");
                fila[3] = rs.getString("dia");
                fila[4] = rs.getString("hora_salida");
                fila[5] = rs.getString("hora_llegada");
                modelo.addRow(fila);
            }

            rutasDashboardTable.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar rutas: " + e.getMessage());
        }
    }

    public void cargarEstudiantes() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Cédula");
        modelo.addColumn("Género");
        modelo.addColumn("Curso");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Correo");
        modelo.addColumn("Dirección");
        modelo.addColumn("Ruta");

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

}

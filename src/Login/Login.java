package Login;

import Administrador.Administrador;
import Conductor.Conductor;
import ConexionMySql.ConexionDB;
import MonitorDeRuta.MonitorDeRuta;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame{
    private JPanel Login;
    private JTextField correoField;
    private JPasswordField contraseniaField;
    private JButton iniciarSesionButton;
    private JButton conductorButton;
    private JButton administradorButton;
    private JButton monitorDeRutaButton;
    private JPanel Fondo;

    private String rolSeleccionado = null; //variable para guardar el rol elegido

    public Login(){
        setVisible(true);
        setContentPane(Fondo);
        setTitle("Iniciar Sesión");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Login.setBorder(new LineBorder(new Color(206,255,253),4));


        // guarda la opción del botón seleccionado
        administradorButton.addActionListener(e -> seleccionarRol("Administrador"));
        conductorButton.addActionListener(e -> seleccionarRol("Conductor"));
        monitorDeRutaButton.addActionListener(e -> seleccionarRol("Monitor"));

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = correoField.getText();
                String clave = String.valueOf(contraseniaField.getPassword());
                iniciarSesion(correo, clave, rolSeleccionado)
            }
        });
    }

    // metodo para confirmar el rol seleccionado
    private void seleccionarRol(String rol) {
        this.rolSeleccionado = rol;
        JOptionPane.showMessageDialog(Login, "Rol seleccionado: " + rol);
    }

    // metodo para validar el login en la base de datos
    private boolean validarLogin(String correo, String clave, String rol){

        String query = "";
        if (rol.equals("Conductor")) {
            query = "SELECT id FROM conductores WHERE correo = ? AND clave = ?";
        } else if (rol.equals("Administrador")) {
            query = "SELECT id FROM administradores WHERE correo = ? AND clave = ?";
        } else if (rol.equals("Monitor")) {
            query = "SELECT id FROM monitores WHERE correo = ? AND clave = ?";
        }

        try(Connection conexion = ConexionDB.getConnection(); PreparedStatement stmt = conexion.prepareStatement(query)){
            // asocia los parámetros de la consulta con los valores ingresados
            stmt.setString(1, correo);
            stmt.setString(2, clave);
            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery();
            // Si hay resultados, significa que el usuario, contraseña y rol son válidos
            if (rs.next()) {
                return rs.getInt(1); // devuelve el ID correspondiente
            }

        } catch (SQLException e) {
            // Si ocurre un error en la conexión o la consulta, imprime el error y devuelve false
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }

    private void iniciarSesion(String correo, String clave, String rol){
        if (correo.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(Login, "Por favor, ingrese los datos solicitados.");
            return;
        }

        if (rolSeleccionado == null) {
            JOptionPane.showMessageDialog(Login, "Por favor, selecciona un rol.");
            return;
        }

        int id = validarLogin(correo, clave, rolSeleccionado);

        if (id != -1) {
            JOptionPane.showMessageDialog(Login, "Inicio de sesión exitoso como " + rolSeleccionado);

            switch (rolSeleccionado) {
                case "Administrador" -> {
                    Administrador admin = new Administrador();
                    admin.setVisible(true);
                }
                case "Conductor" -> {
                    new Conductor(id).setVisible(true);
                }
                case "Monitor" -> {
                    new MonitorDeRuta(id).setVisible(true);
                }
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas o rol inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

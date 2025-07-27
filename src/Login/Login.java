package Login;

import Administrador.Administrador;
import Conductor.Conductor;
import ConexionMySql.ConexionDB;

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
    private JTextField usuarioField;
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
                String usuario = usuarioField.getText();
                String clave = String.valueOf(contraseniaField.getPassword());

                // valida si el campo usuario o contraseña están vacíos
                if (usuario.trim().isEmpty() || clave.trim().isEmpty()){
                    JOptionPane.showMessageDialog(Login,"Por favor, ingrese los datos solicitados.");
                    return;
                }

                // valida si el usuario no ha seleccionado un rol
                if (rolSeleccionado == null){
                    JOptionPane.showMessageDialog(Login,"Por favor, selecciona un rol.");
                    return;
                }

                // validacion de los datos ingresados con la conexion en mysql
                if (validarLogin(usuario, clave, rolSeleccionado)){
                    JOptionPane.showMessageDialog(Login,"Inicio de sesión exitoso como " + rolSeleccionado);

                    // dependiendo del rol, se abre la ventana correspondiente
                    switch (rolSeleccionado){
                        case "Administrador":
                            Administrador admin = new Administrador();
                            admin.mostrarCarta("Dashboard");
                            admin.setVisible(true);
                            break;
                        case "Conductor":
                            new Conductor();
                            break;
                        case "Monitor":
                            break;
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales o rol seleccionado incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // metodo para confirmar el rol seleccionado
    private void seleccionarRol(String rol) {
        this.rolSeleccionado = rol;
        JOptionPane.showMessageDialog(Login, "Rol seleccionado: " + rol);
    }

    // metodo para validar el login en la base de datos
    private boolean validarLogin(String nombre, String clave, String rol){
        // Consulta SQL para verificar si existe un usuario con ese nombre de usuario y contraseña
        String query = "SELECT * FROM usuarios WHERE nombre = ? AND clave = ? AND rol = ?";
        try(Connection conexion = ConexionDB.getConnection(); PreparedStatement stmt = conexion.prepareStatement(query)){
            // asocia los parámetros de la consulta con los valores ingresados
            stmt.setString(1, nombre);
            stmt.setString(2, clave);
            stmt.setString(3,rol);
            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery();
            // Si hay resultados, significa que el usuario, contraseña y rol son válidos
            return rs.next();
        } catch (SQLException e) {
            // Si ocurre un error en la conexión o la consulta, imprime el error y devuelve false
            e.printStackTrace();
            return false;
        }
    }
}

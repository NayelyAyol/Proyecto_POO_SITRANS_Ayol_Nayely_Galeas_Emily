package Login;

import Administrador.Administrador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login extends JFrame{
    private JPanel Principal;
    private JTextField usuarioField;
    private JPasswordField contraseniaField;
    private JButton iniciarSesionButton;
    private JButton conductorButton;
    private JButton administradorButton;
    private JButton monitorDeRutaButton;

    private String rolSeleccionado = null; //variable para guardar el rol elegido

    public Login(){
        setVisible(true);
        setContentPane(Principal);
        setTitle("Iniciar Sesión");
        setSize(300, 470);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/bus.png")));

        // guarda la opción del botón seleccionado
        administradorButton.addActionListener(e -> seleccionarRol("Administrador"));
        conductorButton.addActionListener(e -> seleccionarRol("Conductor"));
        monitorDeRutaButton.addActionListener(e -> seleccionarRol("Monitor"));

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String password = String.valueOf(contraseniaField.getPassword());

                // valida si el campo usuario o contraseña están vacíos
                if (usuario.trim().isEmpty() || password.trim().isEmpty()){
                    JOptionPane.showMessageDialog(Principal,"Por favor, ingrese los datos solicitados.");
                    return;
                }

                // valida si el usuario no ha seleccionado un rol
                if (rolSeleccionado == null){
                    JOptionPane.showMessageDialog(Principal,"Por favor, selecciona un rol.");
                    return;
                }

                // se valida con usuarios y contraseñas de prueba para verificar que el botón de iniciar sesión funcione
                if (usuario.equals("admin") && password.equals("1234") && rolSeleccionado.equals("Administrador")) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como Administrador.");
                    Administrador admin = new Administrador();
                    admin.mostrarCarta("Dashboard");
                    admin.setVisible(true);
                    dispose();
                } else if (usuario.equals("conductor") && password.equals("abcd") && rolSeleccionado.equals("Conductor")) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como Conductor.");
                } else if (usuario.equals("monitor") && password.equals("xyz") && rolSeleccionado.equals("Monitor")) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como Monitor.");
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales o rol seleccionado incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // metodo para confirmar el rol seleccionado
    private void seleccionarRol(String rol) {
        this.rolSeleccionado = rol;
        JOptionPane.showMessageDialog(Principal, "Rol seleccionado: " + rol);
    }
}

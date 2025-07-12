package Login;

import javax.swing.*;
import java.util.Objects;

public class Login extends JFrame{
    private JPanel Principal;
    private JTextField textField1;
    private JTextField textField2;
    private JButton iniciarSesionButton;
    private JButton conductorButton;
    private JButton administradorButton;
    private JButton monitorDeRutaButton;

    public Login(){
        setVisible(true);
        setContentPane(Principal);
        setTitle("Iniciar Sesión");
        setSize(300, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/bus.png")));



    }
}

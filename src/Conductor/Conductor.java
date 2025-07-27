package Conductor;

import Login.Login;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Conductor extends JFrame {
    private JPanel Principal;
    private JButton cerrarSesionButton;
    private JTabbedPane conductorPanel;
    private JTextField rutaAsignadatextField;
    private JComboBox comboBox1;
    private JButton actualizarButton;
    private JTextField zonatextField;
    private JTextField capacidadtextField;
    private JTextField asignadostextField;
    private JTextField horaSalidatextField;
    private JTextField horaEstimadatextField;
    private JTable table1;
    private JScrollPane listaEstudiantesTable;
    private JPanel encabezado;

    public Conductor() {
        setContentPane(Principal);
        setTitle("Conductor");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        cerrarSesionButton.setBorder(null);
        encabezado.setBorder(new LineBorder(new Color(0,0,0),1));

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
    }
}

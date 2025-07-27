package ConexionMySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // declaramos constantes para los parámetros de conexión: URL, usuario y contraseña
    private static final String url = "jdbc:mysql://urywsguuenoyb3wv:T7HswA0Vb9UYI5cnviOo@bfjyjsrdkkqobybihmme-mysql.services.clever-cloud.com:3306/bfjyjsrdkkqobybihmme";
    private static final String usuario = "urywsguuenoyb3wv";
    private static final String clave = "T7HswA0Vb9UYI5cnviOo";

    public static Connection getConnection(){
        try {
            Connection conexion = DriverManager.getConnection(url,usuario,clave);

            if (conexion != null){
                System.out.println("Conexión exitosa a MySql - CleverCloud");
            }else {
                System.out.println("Conexión no disponible");
            }

            return conexion;
        }catch (SQLException e){
            System.out.println("No se ha podido establecer conexión con el servidor.");
            e.printStackTrace();
        }

        // retornar null si es que hubo un error en la conexión
        return null;
    }
}

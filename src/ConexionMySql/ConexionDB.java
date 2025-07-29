package ConexionMySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // declaramos constantes para los parámetros de conexión: URL, usuario y contraseña
    private static final String url = "jdbc:mysql://uuta6qoqasndu51a:6Mkwjg8zwGZddbo0uwIm@bye3meozep3t8sfrhixc-mysql.services.clever-cloud.com:3306/bye3meozep3t8sfrhixc";
    private static final String usuario = "uuta6qoqasndu51a";
    private static final String clave = "6Mkwjg8zwGZddbo0uwIm";

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
            System.out.println("Error al establecer la conexión: "+e.getMessage());
        }

        // retornar null si es que hubo un error en la conexión
        return null;
    }
}

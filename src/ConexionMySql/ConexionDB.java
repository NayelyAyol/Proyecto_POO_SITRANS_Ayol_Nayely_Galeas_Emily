package ConexionMySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para conectar con la base de datos MySQL.
 */
public class ConexionDB {

    /**Declaracion de constantes para los parámetros de conexión: URL, usuario y contraseña*/
    private static final String url = "jdbc:mysql://uuta6qoqasndu51a:6Mkwjg8zwGZddbo0uwIm@bye3meozep3t8sfrhixc-mysql.services.clever-cloud.com:3306/bye3meozep3t8sfrhixc";
    private static final String usuario = "uuta6qoqasndu51a";
    private static final String clave = "6Mkwjg8zwGZddbo0uwIm";

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return Conexión a la base de datos o null si hay error
     */
    public static Connection getConnection(){
        try {
            // Se intenta establecer la conexión usando DriverManager
            Connection conexion = DriverManager.getConnection(url,usuario,clave);

            //Se verifica si la conexión fue exitosa
            if (conexion != null){
                System.out.println("Conexión exitosa a MySql - CleverCloud");
            }else {
                System.out.println("Conexión no disponible");
            }

            return conexion;
        }catch (SQLException e){
            //Manejo de errores SQL
            System.out.println("Error al establecer la conexión: "+e.getMessage());
        }

        // Retorna null si es que hubo un error en la conexión
        return null;
    }
}

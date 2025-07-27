package ConexionMySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // declaramos constantes para los parámetros de conexión: URL, usuario y contraseña
    private static final String url = "jdbc:mysql://uerkn6um7tcezrku:iK0joXvqIaXk9MgMpxEV@bdidgpcn61qhjmtojryx-mysql.services.clever-cloud.com:3306/bdidgpcn61qhjmtojryx";
    private static final String usuario = "uerkn6um7tcezrku";
    private static final String clave = "iK0joXvqIaXk9MgMpxEV";

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

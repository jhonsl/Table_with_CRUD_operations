package conexion;

//importamos todas las librerias necesarias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//clase con la que generaremos la conexion de java con la base de datos
public class conexion 
{
    //creamos los parametros de la conexion
    public static final String URL = "jdbc:mysql://localhost:3306/reto_5";
    public static final String usuario = "root";
    public static final String clave = "Medellin2020";    
    public static Connection con= null;
    
    //creamos el metodo que nos permitira retornar la conexion en otras clases 
    public static Connection getConexion()
    {
        //encerramos todo en un try para manejar los errores
        try {
            con = DriverManager.getConnection(URL, usuario, clave);
            
            if(con != null)
            {
                System.out.println("--------------------------------------");
                System.out.println("Conexion realizada");
                System.out.println("--------------------------------------");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        //retornamos la conexion con la base de datos
        return con;
    }
}

import java.sql.*;
import java.sql.DriverManager;

public class ConexionSQL {

    private Connection conexion;
    private Statement st;

    //DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
    public boolean conectar(String host, String nombre_bd, String user, String pass) {

        try
        {
            System.out.println("Conectando a la Base de Datos...");

            conexion = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":1521/" + nombre_bd, user, pass);

            st = conexion.createStatement();
            System.out.print("-Conexion establecida :)- \n");
            return true;
        }
        catch(Exception e){
            System.out.print("-Conexion fallida :(- \n");
            e.printStackTrace();
            return false;
        }
    }

    public void desconectar() {
        try {
            if (st != null) {
                st.close();
            }

            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

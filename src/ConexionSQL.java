import java.sql.*;
import java.sql.DriverManager;

public class ConexionSQL {

    private Connection conexion;
    private Statement st;

    //DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );

    /**
     * Intenta conectarse a la Base de Datos
     *
     * @param host      host de la BD
     * @param nombre_bd nombre de la BD
     * @param user      usuario de la BD
     * @param pass      contraseña del user
     * @return True si se establece la conexión. False si no
     */
    public boolean conectar(String host, String nombre_bd, String user, String pass) {

        try {
            System.out.println("Conectando a la Base de Datos...");

            conexion = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":1521/" + nombre_bd, user, pass);

            st = conexion.createStatement();
            System.out.println("-Conexion establecida :)-");
            return true;
        } catch (Exception e) {
            System.out.println("-Conexion fallida :(-");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Se desconecta de la Base de Datos al finalizar.
     */
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

    public Connection getConexion() {
        return conexion;
    }

    public Statement getSt() {
        return st;
    }
}

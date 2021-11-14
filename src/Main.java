import java.sql.*;

public class Main {

    static final String host = "oracle0.ugr.es";
    static final String nombre_bd = "practbd.oracle0.ugr.es";
    static final String user = "x1786855";
    static final String pass = "x1786855";

    public static void main(String[] args) throws SQLException {

        System.out.println("Bienvenido a eVents Stock Manager: " +
                "La aplicación creada por el grupo eVents para el Seminario 1 de DDSI.");

        //CONEXIÓN A LA BASE DE DATOS
        ConexionSQL conexionSQL = new ConexionSQL();

        if (conexionSQL.conectar(host, nombre_bd, user, pass)) {

            conexionSQL.getConexion().setAutoCommit(false);
            //LANZAMIENTO DEL MENÚ
            Menu menu = new Menu(conexionSQL); //creamos el menú con la conexión y el statement

            menu.iniciarMenu();

        }

        //DESCONEXIÓN DE LA BASE DE DATOS
        conexionSQL.desconectar();

        System.out.println("\nFinalizando programa... ¡Gracias por usar nuestra aplicación!");
    }
}

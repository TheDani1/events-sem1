public class Main {

    static final String host = "oracle0.ugr.es";
    static final String nombre_bd = "practbd.oracle0.ugr.es";
    static final String user = "x0231215";
    static final String pass = "x0231215";

    public static void main(String[] args) {

        System.out.println("Bienvenido a eVents Stock Manager: " +
                "La aplicación creada por el grupo eVents para el Seminario 1 de DDSI.");

        //CONEXIÓN A LA BASE DE DATOS
        ConexionSQL conexionSQL = new ConexionSQL();

        if (true/*conexionSQL.conectar(host, nombre_bd, user, pass)*/) { //todo quitar para comprobar que se establece la conexión

            //LANZAMIENTO DEL MENÚ
            Menu menu = new Menu(conexionSQL); //creamos el menú con la conexión y el statement

            menu.iniciarMenu();

        }

        //DESCONEXIÓN DE LA BASE DE DATOS
        conexionSQL.desconectar();

        System.out.println("\nFinalizando programa... ¡Gracias por usar nuestra aplicación!");
    }
}

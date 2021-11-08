public class Main {

    public static void main(String[] args) {

        System.out.println("Bienvenido a eVents Stock Manager: " +
                "La aplicación creada por el grupo eVents para el Seminario 1 de DDSI.");

        //CONEXIÓN A LA BASE DE DATOS
        final String user = "x0231215";
        final String pass = "x0231215";

        ConexionSQL conexionSQL = new ConexionSQL();

        if (conexionSQL.conectar("oracle0.ugr.es", "practbd.oracle0.ugr.es", user, pass)){

            //LANZAMIENTO DEL MENÚ
            Menu menu = new Menu();

            menu.iniciarMenu();

        }


        //DESCONEXIÓN DE LA BASE DE DATOS
        conexionSQL.desconectar();
    }
}

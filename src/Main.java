public class Main {

    static final String user = "x0231215";
    static final String pass = "x0231215";
    //static boolean conectado = false; //todo usar una variable que controle si hay conexión a la base de datos?

    public static void main(String[] args) {

        System.out.println("Bienvenido a eVents Stock Manager: " +
                "La aplicación creada por el grupo eVents para el Seminario 1 de DDSI.");

        //CONEXIÓN A LA BASE DE DATOS
        ConexionSQL conexionSQL = new ConexionSQL();

        if (true/*conexionSQL.conectar("oracle0.ugr.es", "practbd.oracle0.ugr.es", user, pass)*/){

            //LANZAMIENTO DEL MENÚ
            Menu menu = new Menu(conexionSQL); //creamos el menú con la conexión y el statement

            menu.iniciarMenu();

        }

        //DESCONEXIÓN DE LA BASE DE DATOS
        conexionSQL.desconectar();
    }
}

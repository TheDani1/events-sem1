public class Main {

    public static void main(String[] args) {

        final String user = "x0231215";
        final String pass = "x0231215";

        ConexionSQL conexionSQL = new ConexionSQL();
        conexionSQL.conectar("oracle0.ugr.es", "practbd.oracle0.ugr.es", user, pass);








        conexionSQL.desconectar();

    }
}

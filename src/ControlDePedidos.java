
public class ControlDePedidos {

    public static void main(String[] args) {
        ConexionSQL conexionSQL = new ConexionSQL();
        conexionSQL.conectar("oracle0.ugr.es", "practbd.oracle0.ugr.es", "x0231215", "x0231215");








        conexionSQL.desconectar();

    }


}

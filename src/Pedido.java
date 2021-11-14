import java.sql.*;
import java.util.Scanner;

public class Pedido {

    /*todo revisar, esto tiene que hacer la consulta y preguntar qué detalles se quieren meter, por lo que va a necesitar más parámetros de entrada
        por ejemplo: número del pedido, por lo que, por cada pedido que se haga, habrá que hacer un contador que sume cada pedido hecho
        y lo inserte en la base de datos*/


    private String Ccliente; //debería incrementarse cada vez que se hace un pedido
    private String Cpedido;  //debería recoger una ID de usuario del user de la base de datos?
    private String fecha; //debería coger la fecha y la hora de hoy, sería algo de java.time.LocalDate.now()
    private Savepoint empieza_pedido;
    private Savepoint empieza_detalle;

    /**
     * Empieza a crear un nuevo pedido, el usuario debe introducir los datos del pedido
     * (Cpedido, Ccliente, fecha), creamos dos savepoint para que el usuario
     * puede cancelar el pedido o quitar los detalles que ha insertado.
     *
     * @param conex Instancia de la conexión a la BD
     */
    public void inicialNuevoPedido(ConexionSQL conex) throws SQLException {
        empieza_pedido= conex.getConexion().setSavepoint("Empieza");

        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el codigo del pedido");
        entrada = new Scanner(System.in);
        Cpedido = entrada.nextLine();
        System.out.println("Introduzca el codigo del cliente");
        entrada = new Scanner(System.in);
        Ccliente = entrada.nextLine();
        System.out.println("Introduzca la fecha con el siguiente formato YYYY/MM/DD");
        fecha = entrada.nextLine();

        conex.getSt().executeUpdate("INSERT INTO Pedido (Cpedido,Ccliente,Fechapedido) VALUES ("+Cpedido+", "+Ccliente+", TO_DATE('"+fecha+"','YYYY-MM-DD'))");
        empieza_detalle= conex.getConexion().setSavepoint("Empieza_detalles");
    }

    /**
     * Intenta llamar a la consultaSQL que añade los detalles de artículo y cantidad
     * al pedido y actualiza el Stock, si hay. En caso de no poder, imprime en terminal
     * un mensaje de rechazo.
     *
     * @param conex Instancia de la conexión a la BD
     */
    public void anadirDetalles(ConexionSQL conex) {
        try {
            ConsultasSQL.anadirDetalle(conex, this);

        } catch (SQLException e) {
            System.out.println("No se han podido añadir los detalles :c");
            e.printStackTrace();
        }
    }

    public String getCpedido(){ return Cpedido; }

    /**
     * Intenta llamar a la ConsultaSQL que elimina los detalles de pedido que
     * se han insertado. En caso de no poder, imprime en terminal
     * un mensaje de rechazo.
     *
     * @param conex Instancia de la conexión a la BD
     */
    public void eliminarDetalles(ConexionSQL conex) {
        try {
            ConsultasSQL.eliminarDetalles(conex, empieza_detalle);

        } catch (SQLException e) {
            System.out.println("No se han podido eliminar los detalles :c");
            e.printStackTrace();
        }
    }

    /**
     * Intenta llamar a la ConsultaSQL que cancela el pedido.
     * En caso de no poder, imprime en terminal un mensaje de rechazo.
     *
     * @param conex Instancia de la conexión a la BD
     */
    public void cancelarPedido(ConexionSQL conex) {
        try {
            ConsultasSQL.cancelarPedido(conex, empieza_pedido);
        } catch (SQLException e) {
            System.out.println("No se han podido cancelar el pedido :c");
            e.printStackTrace();
        }
    }

    /**
     * Intenta llamar a la ConsultaSQL que confirma y finaliza el pedido.
     * En caso de no poder, imprime en terminal un mensaje de rechazo.
     *
     * @param conex Instancia de la conexión a la BD
     */
    public void finalizarPedido(ConexionSQL conex) {
        try {
            ConsultasSQL.finalizarPedido(conex);

        } catch (SQLException e) {
            System.out.println("No se han podido finalozar el pedido :c");
            e.printStackTrace();
        }
    }
}

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pedido {

    /*todo revisar, esto tiene que hacer la consulta y preguntar qué detalles se quieren meter, por lo que va a necesitar más parámetros de entrada
        por ejemplo: número del pedido, por lo que, por cada pedido que se haga, habrá que hacer un contador que sume cada pedido hecho
        y lo inserte en la base de datos*/


    private int Ccliente; //debería incrementarse cada vez que se hace un pedido
    private int Cpedido;  //debería recoger una ID de usuario del user de la base de datos?
    private String fecha; //debería coger la fecha y la hora de hoy, sería algo de java.time.LocalDate.now()

    ResultSet resultSet = null; //todo revisar si se usa para mostrar por pantalla resultados (lo mismo no se necesita)

    /**
     * Intenta llamar a la consultaSQL que añade los detalles de artículo y cantidad
     * al pedido y actualiza el Stock, si hay. En caso de no poder, imprime en terminal
     * un mensaje de rechazo.
     *
     * @param conex Instancia de la conexión a la BD
     */
    public void anadirDetalles(ConexionSQL conex) {
        try {
            resultSet = ConsultasSQL.anadirDetalle(conex); //todo aquí hay que pedir y meter parámetros de entrada

        } catch (SQLException e) {
            System.out.println("No se han podido añadir los detalles :c");
            e.printStackTrace();
        }
    }

    /**
     * Intenta llamar a la ConsultaSQL que elimina los detalles de pedido que
     * se han insertado. En caso de no poder, imprime en terminal
     * un mensaje de rechazo.
     *
     * @param conex Instancia de la conexión a la BD
     */
    public void eliminarDetalles(ConexionSQL conex) {
        try {
            resultSet = ConsultasSQL.eliminarDetalles(conex); //todo simplemente borrar los detalles

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
            resultSet = ConsultasSQL.cancelarPedido(conex); //todo cancelar el pedido

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
            resultSet = ConsultasSQL.finalizarPedido(conex); //todo confirmar el pedido

        } catch (SQLException e) {
            System.out.println("No se han podido finalozar el pedido :c");
            e.printStackTrace();
        }
    }
}

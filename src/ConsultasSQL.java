import javax.xml.transform.Result;
import java.sql.*;

/**
 * Esta clase se va a encargar de recopilar métodos que hagan consultas SQL.
 * El objetivo es que, el programa funcione independientemente de cómo se haga la consulta.
 * Es decir, para hacer una consulta, hay que llamar a un método de esta clase, y
 * es esta clase la que se encarga de implementarlo.
 * <p>
 * IMPORTANTE: Esta clase tiene sus métodos static porque no hay que crear una instancia para llamar a sus métodos
 *
 * @author Javier Florido Cartolano, //todo añadirse quien vaya escribiendo código
 */

public class ConsultasSQL {

    /**
     * Devuelve un ResultSet con todas las tablas de la Base de Datos
     * //todo resumir lo que hace la consulta SQL
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet mostrarTablas(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = "SELECT * FROM USER_TABLES"; //todo revisar si esto está bien, tal vez haya que hacer 3 select

        return conexionSQL.getSt().executeQuery(selectSQL); //devuelve un ResultSet
    }

    /**
     * Captura los detalles de un artículo y cantidad, y realiza la
     * inserción correspondiente en la tabla Detalle-Pedido.
     * Además, actualiza el Stock (si hay)
     * //todo resumir lo que hace la consulta SQL
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet anadirDetalle(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = "BEGIN TRANSACTION;INSERT INTO DetallePedido(Cpedido, Cproducto, Cantidad) VALUES(,,) WHERE Cproducto = Cproducto AND Cpedido = Cpedido COMMIT TRANSACTION;"; //todo HACER CONSULTA

        return conexionSQL.getSt().executeQuery(selectSQL); //devuelve un ResultSet
    }

    /**
     * Elimina todos los detalles de pedido que se han insertado en
     * la tabla Detalle-Pedido para el pedido ACTUAL (pero no el
     * pedido en la tabla Pedidos).
     * //todo resumir lo que hace la consulta SQL
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet eliminarDetalles(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = "DELETE * FROM DetallePedido where Cpedido = Cpedido"; //todo HACER CONSULTA

        return conexionSQL.getSt().executeQuery(selectSQL); //devuelve un ResultSet
    }

    /**
     * Elimina el pedido y todos sus detalles.
     * //todo resumir lo que hace la consulta SQL
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet cancelarPedido(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = "BEGIN TRANSACTION;DELETE * FROM Pedido, DetallePedido where Cpedido = Cpedido ROLLBACK ROLLBACK;"; //todo HACER CONSULTA (con un rollback)

        return conexionSQL.getSt().executeQuery(selectSQL); //devuelve un ResultSet
    }

    /**
     * Hace consistente el pedido y aplica los cambios de forma permanente.
     * //todo resumir lo que hace la consulta SQL
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet finalizarPedido(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = "BEGIN TRANSACTION;UPDATE COMMIT TRANSACTION;"; //todo HACER CONSULTA (con un commit)

        return conexionSQL.getSt().executeQuery(selectSQL); //devuelve un ResultSet
    }

    /**
     * Borra las tablas actuales y vuelve a crearlas insertando
     * 10 tuplas por defecto. Básicamente, reinicia las tablas.
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet reiniciarTablas(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = //todo revisar si esto está bien
            """
                DROP TABLE Stock;
                DROP TABLE Pedido;
                DROP TABLE DetallePedido;
                CREATE TABLE Stock (
                    Cproducto   int     PRIMARY KEY,
                    Cantidad    int     NOT NULL
                );
                CREATE TABLE Pedido(
                    Cpedido     int     PRIMARY KEY,
                    Ccliente    int     NOT NULL,
                    fechaPedido DATE    NOT NULL
                );
                CREATE TABLE DetallePedido (
                    Cpedido     int     NOT NULL,
                    Cproducto   int     NOT NULL,
                    Cantidad    int     NOT NULL,
                    FOREIGN KEY (Cproducto) references stock(Cproducto),
                    FOREIGN KEY (Cpedido) references pedido(Cpedido)
                );
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (1, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (2, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (3, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (4, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (5, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (6, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (7, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (8, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (9, 5);
                INSERT INTO Stock (Cproducto, Cantidad) VALUES (10, 5);""";

        return conexionSQL.getSt().executeQuery(selectSQL); //devuelve un ResultSet
    }

}

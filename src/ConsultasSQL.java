import javax.xml.transform.Result;
import java.sql.*;
/**
 *  Esta clase se va a encargar de recopilar métodos que hagan consultas SQL.
 *  El objetivo es que, el programa funcione independientemente de cómo se haga la consulta.
 *  Es decir, para hacer una consulta, hay que llamar a un método de esta clase, y
 *      es esta clase la que se encarga de implementarlo.
 *
 *  IMPORTANTE: Esta clase tiene sus métodos static porque no hay que crear una instancia para llamar a sus métodos
 *
 * @author Javier Florido Cartolano,
 *
 *
 */

public class ConsultasSQL {

    public static ResultSet mostrarTablas(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = "SELECT * FROM USER_TABLES"; //todo revisar si esto está bien

        return conexionSQL.getSt().executeQuery(selectSQL); //devuelve un ResultSet
    }

}

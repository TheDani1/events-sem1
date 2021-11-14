import java.sql.*;
import java.util.Scanner;

/**
 * Esta clase se va a encargar de recopilar métodos que hagan consultas SQL.
 * El objetivo es que, el programa funcione independientemente de cómo se haga la consulta.
 * Es decir, para hacer una consulta, hay que llamar a un método de esta clase, y
 * es esta clase la que se encarga de implementarlo.
 * <p>
 * IMPORTANTE: Esta clase tiene sus métodos static porque no hay que crear una instancia para llamar a sus métodos
 *
 * @author Javier Florido Cartolano, Yang Lin, //todo añadirse quien vaya escribiendo código
 */

public class ConsultasSQL {

    /**
     * Devuelve un ResultSet con las tablas de la Base de Datos y mostrar en la pantalla.
     * Una vez que leamos una tabla, si no está vacía mostrarla, si está vacía, mostramos
     * el mensaje.
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @throws SQLException
     */
    public static void mostrarTablas(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = "SELECT * FROM Stock";
        ResultSet tablas= conexionSQL.getSt().executeQuery(selectSQL);
        if(tablas.next() == false){
            System.out.println("Tabla de Stock esta en vacío.");
        }
        else{
            System.out.println("Tabla de Stock (Cproducto, Cantidad): ");
            while (tablas.next()) {
                System.out.println(tablas.getString(1) + "\t" + tablas.getString(2));
            }
        }

        selectSQL = "SELECT * FROM DetallePedido";
        tablas= conexionSQL.getSt().executeQuery(selectSQL);
        if(tablas.next() == false){
            System.out.println("Tabla de DetallePedido esta en vacío.");
        }
        else{
            System.out.println("Tabla de DetallePedido (Cproducto, Ccliente, Fecha_Pedido): ");
            while (tablas.next()) {
                System.out.println(tablas.getString(1) + "\t" + tablas.getString(2) + "\t" + tablas.getString(3));
            }
        }

        selectSQL = "SELECT * FROM Pedido";
        tablas= conexionSQL.getSt().executeQuery(selectSQL);
        if(tablas.next() == false){
            System.out.println("Tabla de Pedido esta en vacío.");
        }
        else{
            System.out.println("Tabla de Pedido (Cpedido, Cproducto, Cantidad): ");
            while (tablas.next()) {
                System.out.println(tablas.getString(1) + "\t" + tablas.getString(2) + "\t" + tablas.getString(3));
            }
        }

    }

    /**
     * Captura los detalles de un artículo y cantidad, y realiza la
     * inserción correspondiente en la tabla Detalle-Pedido.
     * Además, actualiza el Stock (si hay)
     * Leamos el código de producto y la cantidad que introduzca
     * el usuario, comprobamos si existe el producto y si la cantidad
     * que pide sobrepasa la que hay en Stock muestra un mensaje,
     * en caso que no, podemos añadirla en la tabla DetallePedido.
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @param pedido Instancia para que podemos almacenar los detalles en mismo pedido
     * @throws SQLException
     */
    public static void  anadirDetalle(ConexionSQL conexionSQL, Pedido pedido) throws SQLException {
        System.out.println("Introduzca el codigo del producto");
        Scanner entrada = new Scanner(System.in);
        String cproducto = entrada.nextLine();
        System.out.println("Introduzca la cantidad del producto");
        entrada = new Scanner(System.in);
        int ccantidad = entrada.nextInt();

        String instruccion = "SELECT cantidad FROM Stock WHERE cproducto="+cproducto;
        ResultSet ProductoElegido = conexionSQL.getSt().executeQuery(instruccion);

        if(ProductoElegido.next() != false)
        {
            int cantidadproducto = ProductoElegido.getInt(1);
            if(cantidadproducto > ccantidad)
            {
                cantidadproducto = cantidadproducto - ccantidad;
                instruccion = "UPDATE Stock SET Cantidad="+cantidadproducto+" WHERE cproducto="+cproducto;
                conexionSQL.getSt().executeUpdate(instruccion);
                instruccion = "INSERT INTO DetallePedido (CPedido, CProducto, Cantidad) VALUES ("+pedido.getCpedido()+", "+cproducto+","+ccantidad+")";
                conexionSQL.getSt().executeUpdate(instruccion);
                System.out.println("Detalle pedido realizado");
            }
            else
            {
                System.out.println("No hay suficiente cantidad de producto en el stock.");
            }
        }
        else
        {
            System.out.println("Este producto no existe.");
        }
    }

    /**
     * Elimina todos los detalles de pedido que se han insertado en
     * la tabla Detalle-Pedido para el pedido ACTUAL (pero no el
     * pedido en la tabla Pedidos).
     * Usando la función rollback() para volver al punto antes de crear los detalles.
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @param comienzo_detalle Sevepoint que vamos a recuperar
     * @throws SQLException
     */
    public static void eliminarDetalles(ConexionSQL conexionSQL, Savepoint comienzo_detalle) throws SQLException {
        conexionSQL.getConexion().rollback(comienzo_detalle);
        System.out.println("Los detalles no se han añadido.");
    }

    /**
     * Elimina el pedido y todos sus detalles.
     * Usando la función rollback() para volver al punto antes de crear el pedido.
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @param comienzo Sevepoint que vamos a recuperar
     * @throws SQLException
     */
    public static void cancelarPedido(ConexionSQL conexionSQL, Savepoint comienzo) throws SQLException {
        conexionSQL.getConexion().rollback(comienzo);
        System.out.println("El pedido se ha cancelado.");
    }

    /**
     * Hace consistente el pedido y aplica los cambios de forma permanente.
     * Usando la función commit() para aplicar los cambios.
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @throws SQLException
     */
    public static void finalizarPedido(ConexionSQL conexionSQL) throws SQLException {
        conexionSQL.getConexion().commit();
        System.out.println("El pedido se ha finalizado.");
    }

    /**
     * Borra las tablas actuales y vuelve a crearlas insertando
     * 10 tuplas por defecto. Básicamente, reinicia las tablas.
     *
     * @param conexionSQL Instancia de la conexión a la BD
     * @throws SQLException
     */
    public static void reiniciarTablas(ConexionSQL conexionSQL) throws SQLException {
        String selectSQL = null;
        try {
            selectSQL="DROP TABLE DetallePedido";
            conexionSQL.getSt().executeUpdate(selectSQL);
            selectSQL="DROP TABLE Stock";
            conexionSQL.getSt().executeUpdate(selectSQL);
            selectSQL="DROP TABLE Pedido";
            conexionSQL.getSt().executeUpdate(selectSQL);
        }
        catch(Exception e){
            System.out.println("No se han podido borrar las tablas ya que no estaban creadas");
        }
        selectSQL="CREATE TABLE Stock(Cproducto int PRIMARY KEY, Cantidad int NOT NULL)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="CREATE TABLE Pedido(Cpedido int PRIMARY KEY, Ccliente int NOT NULL, fechaPedido DATE NOT NULL)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="CREATE TABLE DetallePedido (Cpedido int NOT NULL, Cproducto int NOT NULL, Cantidad int NOT NULL, FOREIGN KEY (Cproducto) references stock(Cproducto), FOREIGN KEY (Cpedido) references pedido(Cpedido))";
        conexionSQL.getSt().executeUpdate(selectSQL);

        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (1, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (2, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (3, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (4, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (5, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (6, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (7, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (8, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (9, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
        selectSQL="INSERT INTO Stock (Cproducto, Cantidad) VALUES (10, 5)";
        conexionSQL.getSt().executeUpdate(selectSQL);
    }

}

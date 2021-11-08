import java.sql.*;

/**
 * Esta clase se encarga de hacer funcionar el menú grafico y permitir al usuario
 * navergar por las distintas opciones
 *
 */

public class Menu {

    private ConexionSQL conexionSQL;

    public Menu(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    public void iniciarMenu(){
        imprimirMenu();             //imprimimos las opciones
        int opcion = pedirInput();  //pedimos la opción elegida y la guardamos

        switch (opcion) {
            case 1:
                menuBorrarCrear();

            case 2:
                menuNuevoPedido();

            case 3:
                menuMostrar();

            case 0:
                //no hacer nada, el programa acaba por sí mismo
        }
    }

    private void imprimirMenu(){
        System.out.println("MENU PRINCIPAL. Teclee el número de la opción deseada.");
        System.out.println("[1] Borrado y creación de tuplas");
        System.out.println("[2] Dar de alta un nuevo pedido");
        System.out.println("[3] Mostrar tablas");
        System.out.println("[0] Salir del programa y cerrar conexión con la Base de Datos");
    }

    private int pedirInput(){
        int num = 0;

        //todo pedir el número del menú con un scanner

        return num;
    }

    private void menuBorrarCrear(){
        //todo
    }

    private void menuNuevoPedido(){
        //todo
    }

    private void menuMostrar(){
        //todo

        System.out.println("MOSTRANDO TODO EL CONTENIDO DE LAS TABLAS:");

        try {
            ResultSet resultSet = ConsultasSQL.mostrarTablas(conexionSQL);

            while (resultSet.next()){
                //todo encontrar una función que devuelva el número de columnas y hacer un for hasta el tamaño para mostrarlas
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("No se han podido encontrar las tablas :c");
            e.printStackTrace();
        }
    }
}

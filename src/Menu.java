import java.sql.*;
import java.util.Scanner;

/**
 * Esta clase se encarga de hacer funcionar el menú grafico y permitir al usuario
 * navergar por las distintas opciones
 *
 */

public class Menu {

    private ConexionSQL conexionSQL;
    private String menuPrincipalStr;
    private String menuBorrarCrearStr;
    private String menuNuevoPedidoStr;
    private String menuMostrarStr;

    public Menu(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
        menuPrincipalStr =  "MENU PRINCIPAL. Teclee el número de la opción deseada.\n" +
                            "[1] Borrado y creación de tuplas\n" +
                            "[2] Dar de alta un nuevo pedido\n" +
                            "[3] Mostrar tablas\n" +
                            "[0] Salir del programa y cerrar conexión con la Base de Datos";

        menuBorrarCrearStr= "MENÚ PARA BORRAR O CREAR TABLAS\n" +
                            "[1] Borrar\n" +
                            "[2] Crear\n" +
                            "[3] Insertar\n" +
                            "[0] Volver al menú principal";

        menuNuevoPedidoStr= "MENU PARA REALIZAR UN NUEVO PEDIDO\n" +
                            "[1] Añadir detalle del producto\n" +
                            "[2] Eliminar todos los detalles del producto\n" +
                            "[3] Cancelar pedido\n" +
                            "[4] Finalizar pedido";


        menuMostrarStr=     "MENU PARA MOSTRAR EL CONTENIDO DE LAS TABLAS";

    }

    /** MENÚ PRINCIPAL */
    public void iniciarMenu(){
        boolean fin = false;
        while(!fin){
            imprimir(menuPrincipalStr); //imprimimos las opciones

            int opcion = pedirInput();  //pedimos la opción elegida y la guardamos

            switch (opcion) {
                case 1:
                    menuBorrarCrear();
                break;

                case 2:
                    menuNuevoPedido();
                    break;

                case 3:
                    menuMostrar();
                    break;

                case 0:
                    fin = true;
            }
        }
    }

    private int pedirInput(){

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        return num;
    }

    private void imprimir(String menuStr){
        System.out.println(menuStr);
    }

    private void menuBorrarCrear(){
        //todo

        imprimir(menuBorrarCrearStr);
        int opcion = pedirInput();

        switch (opcion) {
            case 1:
                //llamar a consulta borrar
                System.out.println("Borrando...");
                break;

            case 2:
                //llamar a consulta crear
                System.out.println("Creando...");
                break;

            case 3:
                //llamar a consulta insertar
                System.out.println("Insertando...");
                break;

            case 0:
                //vuelve al menú principal

        }
    }

    private void menuNuevoPedido(){
        //todo

        boolean fin = false;
        while(!fin){
            imprimir(menuNuevoPedidoStr); //imprimimos las opciones

            int opcion = pedirInput();  //pedimos la opción elegida y la guardamos

            switch (opcion) {
                case 1:
                    //Añadir detalle del producto
                    System.out.println("Añadiendo detalle del producto...");
                    break;

                case 2:
                    //Eliminar todos los detalles del producto
                    System.out.println("Eliminando detalles del producto...");

                    break;

                case 3:
                    //Cancelar pedido
                    System.out.println("Cancelando pedido...");
                    fin = true;
                    break;

                case 4:
                    //Finalizar pedido
                    System.out.println("Finalizando pedido...");
                    fin = true;
                    break;
            }
        }
    }

    private void menuMostrar(){
        //todo

        imprimir(menuMostrarStr);

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

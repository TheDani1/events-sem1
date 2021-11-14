import java.sql.*;
import java.util.Scanner;

/**
 * Esta clase se encarga de hacer funcionar el menú grafico y permitir al usuario
 * navergar por las distintas opciones
 */

public class Menu {

    private ConexionSQL conexionSQL;
    private String menuPrincipalStr;
    private String menuBorrarCrearStr;
    private String menuNuevoPedidoStr;
    private String menuMostrarStr;

    /**
     * Crea una instancia de menú conectado a la Base de Datos establecida por
     * el parámetro conexionSQL. Este parámetro servirá para que las consultas
     * sean efectivas. El resto de parámetros son el string que se mostrará
     * como "Interfaz para el usuario" y que pueda navegar desde la terminal.
     *
     * @param conexionSQL Instancia de la conexión a la BD
     */
    public Menu(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
        menuPrincipalStr = """

                MENU PRINCIPAL. Teclee el número de la opción deseada.
                [1] Reiniciar tuplas
                [2] Dar de alta un nuevo pedido
                [3] Mostrar tablas
                [0] Salir del programa y cerrar conexión con la Base de Datos""";

        menuNuevoPedidoStr = """

                MENU PARA REALIZAR UN NUEVO PEDIDO. Teclee el número de la opción deseada.
                [1] Añadir detalle del producto
                [2] Eliminar todos los detalles del producto
                [3] Cancelar pedido
                [4] Finalizar pedido""";

        menuBorrarCrearStr = "\nReiniciando tuplas...";

        menuMostrarStr = "\nMostrando las tablas...";
    }

    /**
     * MENÚ PRINCIPAL. Controla las distintas opciones que la aplicación gestiona.
     * Muestra el display del Menú Principal para que el usuario sepa qué opciones
     * tiene. Según la que éste elija, le llevará a otros submenús o funcionalidades.
     */
    public void iniciarMenu() throws SQLException {
        boolean fin = false;
        while (!fin) {

            imprimir(menuPrincipalStr); //imprimimos las opciones

            int opcion = pedirInput();  //pedimos la opción elegida y la guardamos

            switch (opcion) {
                case 1 -> menuBorrarCrear();
                case 2 -> menuNuevoPedido();
                case 3 -> menuMostrar();
                case 0 -> fin = true;
                default -> System.out.println("Por favor, inserta un número válido");
            }
        }
    }

    /**
     * Pide usuario que introduzca un numero.
     *
     * @return num: Número de la opción elegida por el usuario
     */
    private int pedirInput() {
        Scanner input = new Scanner(System.in);

        //todo hacer un sistema de try-catch que detecte que se inserta un número y no cualquier otro caracter
        int num = input.nextInt();

        return num;
    }

    /**
     * Imprime la cadena de caracteres que se se le pasa por parámetro
     * Se hace en un método aparte por si cambia la salida a una interfaz
     * gráfica en lugar de la terminal.
     *
     * @param menuStr String que contiene los caracteres que se desea imprimir
     */
    private void imprimir(String menuStr) {
        System.out.println(menuStr);
    }

    /**
     * Intenta llamar al método de ConsultasSQL que reinicia las tablas.
     * En caso de no poder, manda un mensaje de rechazo.
     */
    private void menuBorrarCrear() {
        //todo revisar

        imprimir(menuBorrarCrearStr);

        try {
            ConsultasSQL.reiniciarTablas(conexionSQL);
            //todo asegurarse de que no se necesita el resultSet porque no hay que mostrar nada por pantalla

        } catch (SQLException e) {
            System.out.println("No se han podido reiniciar las tablas :c");
            e.printStackTrace();
        }
    }

    /**
     * Crea una nueva instancia de Pedido que se encarga de gestionar un nuevo pedido.
     * Sin embargo, muestra un submenú que delegará a los métodos de la clase Pedido según
     * la función que el usuario elija. Cuando las opciones 1 y 2 se realizan, vuelve a aparecer
     * este mismo menú. Pero, para las opciones 3 y 4 vuelve al menú principal.
     */
    private void menuNuevoPedido() throws SQLException {
        //todo revisar

        Pedido pedido = new Pedido();

        pedido.inicialNuevoPedido(conexionSQL);

        boolean fin = false;
        while (!fin) {
            imprimir(menuNuevoPedidoStr); //imprimimos las opciones

            int opcion = pedirInput();  //pedimos la opción elegida y la guardamos

            switch (opcion) {
                case 1 -> {
                    //Añadir detalle del producto
                    System.out.println("Añadiendo detalle del producto...");
                    pedido.anadirDetalles(conexionSQL);
                }
                case 2 -> {
                    //Eliminar todos los detalles del producto
                    System.out.println("Eliminando detalles del producto...");
                    pedido.eliminarDetalles(conexionSQL);
                }
                case 3 -> {
                    //Cancelar pedido
                    System.out.println("Cancelando pedido...");
                    pedido.cancelarPedido(conexionSQL);
                    fin = true; //salimos del menú
                }
                case 4 -> {
                    //Finalizar pedido
                    System.out.println("Finalizando pedido...");
                    pedido.finalizarPedido(conexionSQL);
                    fin = true; //salimos del menú
                }
                default -> System.out.println("Por favor, inserta un número válido");
            }
        }
    }

    /**
     * Intenta llamar al método de ConsultasSQL que muestra las tablas.
     * En caso de no poder, manda un mensaje de rechazo.
     */
    private void menuMostrar() {
        //todo

        imprimir(menuMostrarStr);

        try {
            ConsultasSQL.mostrarTablas(conexionSQL);

        } catch (SQLException e) {
            System.out.println("No se han podido encontrar las tablas :c");
            e.printStackTrace();
        }
    }
}
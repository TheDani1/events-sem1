public class Menu {

    //todo no se debería hacer un constructor?

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
    }
}

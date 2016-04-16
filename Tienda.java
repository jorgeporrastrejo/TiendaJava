/* Porras Trejo Jorge
   Grupo: 02
*/

import java.util.Scanner;

public class Tienda{
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {        

        /* Arreglo de cadenas que contiene nombres de los productos */
        String nombres [] = {"Cerveza", "Tequila", "Vodka", "Brandy","Whisky"};
        
        /* Arreglo bidimensional con los precios y las existencias de los productos, en el mismo orden que los nombres */
        int productos [][] = {{16,50}, {450,15}, {300,30},{615,13},{1775,10}};

        /* Opcion elegida del menu */
        int opcion;        
        
        /* Total de ventas */
        int ventas = 0;
                                         
        do {
            opcion = lee_opcion_menu();        
        
            switch (opcion) {
                case 1:
                    ventas = vender_productos(nombres, productos) + ventas;
                    break;                
                case 2:
                    ticket(ventas);
                    break;              

                }
        } while (opcion != 0);

        System.out.println();                      
        System.out.println("Gracias por sus compras!");
        System.out.println();  
        
    }
    
    /* Mostramos el menu de la Tienda */
    static int lee_opcion_menu() {
        int opcion;
        
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();            
            System.out.println("Menu de la tienda.");
            System.out.println();            
            System.out.println("1.- Realizar compra");
            System.out.println("2.- Generar total");
            System.out.println("0.- Salir");
            System.out.println();
            System.out.print("Introduce tu opcion: ");
            opcion = sc.nextInt();
            
        } while (opcion < 0 || opcion >2);
        
        return opcion;
    }
    
    /* Con este metodo enlistamos lo productos por Nombre Precio Stock*/
    static void listar_productos(String nombres[], int productos[][]) {
        int i = 0;
        
        System.out.println();
        System.out.println("\tProducto\tPrecio\t  Stock");

        for (i = 0; i<productos.length; i++) {
            /* Nota: imprime i+1 para no empezar desde cero */
            System.out.println((i+1)+".\t"+nombres[i]+"\t\t"+productos[i][0]+"\t  "+productos[i][1]);
        }
    }
    
    /* Con este metodo podemos realizar la venta de un producto  */
    static int vender_productos(String nombres[], int productos[][]) {
        int i = 0;
        int opcion;
        int existencias;
        int comprados;
        int total = 0;

        /* Mostramos lista de productos que se puedencomprar */
        do {
            System.out.println();
            System.out.println("Estos son los productos que vendemos dentro de la tienda.");
            listar_productos(nombres, productos);

            System.out.println();            
            System.out.print("Qué producto deseas comprar: ");
            opcion = sc.nextInt();
            
        } while (opcion < 1 || opcion > productos.length);
       
        existencias = productos[opcion-1][1];
        
        /* El if siguiente es para checar que haya existencias del producto */
        if(existencias > 0) {
            do {
                System.out.println();
                System.out.print("Cuantos productos desea comprar (1-"+existencias+"): ");
                comprados = sc.nextInt();
            
            } while (comprados < 1 || comprados > existencias);
        
            System.out.println();
            if(comprados>1){
                System.out.print("Añadiste "+ comprados + " " + nombres[opcion-1]+"s");
            }else{
                System.out.print("Añadiste "+ comprados + " " + nombres[opcion-1]);
            }           
            
            /* Calculo del total de esta venta */
            total = productos[opcion-1][0] * comprados;
            System.out.print(" con el valor de: "+total +" pesos.");

            productos[opcion-1][1] = existencias - comprados;
        } else {
            System.out.println();            
            System.out.println("Lo siento, no tenemos ese producto en existencia");
        }

        System.out.println();
        System.out.println();
        espera_tecla();
        
        return total;

    }
    
    /**
     * Este metodo muestra en pantalla el total de la compra.
     */
    static void ticket(int total) {
        System.out.println();
        System.out.println("El total de las compras ha sido de: "+total + " pesos.");
        System.out.println();
        espera_tecla();       
    }
    
    /**
     * Espera una tecla;
     */
    static void espera_tecla() {        
        System.out.println("Presione una tecla para continuar");
        sc.nextLine();
        sc.nextLine();
    }
}
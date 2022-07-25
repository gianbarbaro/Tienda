/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.servicios.FabricanteService;
import tienda.servicios.ProductoService;

/**
 *
 * @author Usuario
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        FabricanteService fabricanteServicio = new FabricanteService();
        ProductoService productoServicio = new ProductoService();

        Integer codProducto, codFabricante;
        String nombre, nombreFabricante, opcion;
        Double precio;

        Fabricante f = null;
        Producto p = null;

        try {
            System.out.println("BIENVENIDO! Elija una de las siguientes opciones:");
            do {
                System.out.println("----------------------------------------------------------------");
                System.out.println("1. Listar el nombre de todos los productos");
                System.out.println("2. Listar nombre y precio de todos los productos");
                System.out.println("3. Listar los productos donde su precio esté entre 120 y 202.");
                System.out.println("4. Listar todos los productos portátiles.");
                System.out.println("5. Mostrar nombre y precio del producto más barato.");
                System.out.println("6. Ingresar un nuevo producto a la base de datos.");
                System.out.println("7. Ingresar un fabricante a la base de datos");
                System.out.println("8. Editar los datos de un producto");
                System.out.println("9. Salir");
                System.out.println("----------------------------------------------------------------");

                opcion = leer.next();

                switch (opcion) {
                    case "1":
                        productoServicio.mostrarNombres();
                        break;

                    case "2":
                        productoServicio.mostrarNombreYPrecio();
                        break;

                    case "3":
                        productoServicio.mostrarSegunPrecio();
                        break;

                    case "4":
                        productoServicio.mostrarPortatiles();
                        break;

                    case "5":
                        System.out.println(productoServicio.buscarMasBarato().toString());
                        break;

                    case "6":
                        System.out.println("Ingrese los siguientes datos");

                        System.out.println("nombre:");
                        nombre = leer.next();
                        System.out.println("precio:");
                        precio = leer.nextDouble();
                        System.out.println("código del producto:");
                        codProducto = leer.nextInt();

                        fabricanteServicio.mostrarTodo();

                        System.out.println("¿El fabricante del producto se encuentra registrado en el sistema? (s/n)");
                        opcion = leer.next();

                        if (opcion.equalsIgnoreCase("s")) {

                            System.out.print("Indique el código del fabricante: ");
                            codFabricante = leer.nextInt();

                            f = fabricanteServicio.buscarPorCodigo(codFabricante);
                        } else {

                            System.out.println("En este caso, deberá cargar el nuevo fabricante en el sistema");
                            System.out.print("Ingrese los siguientes datos");

                            System.out.println("nombre:");
                            nombreFabricante = leer.next();
                            System.out.println("código:");
                            codFabricante = leer.nextInt();

                            fabricanteServicio.guardarDB(nombreFabricante, codFabricante);

                            f = fabricanteServicio.ultimoFabricanteAgregado();
                        }

                        productoServicio.guardarDB(nombre, precio, f, codProducto);

                        System.out.println("Nuevo producto ingresado a la base de datos :)");
                        break;

                    case "7":
                        System.out.println("Ingrese los siguientes datos");

                        System.out.println("nombre:");
                        nombre = leer.next();
                        System.out.println("código del fabricante:");
                        codFabricante = leer.nextInt();

                        fabricanteServicio.guardarDB(nombre, codFabricante);

                        System.out.println("Nuevo fabricante ingresado a la DDBB");
                        break;

                    case "8":
                        productoServicio.mostrarTodo();

                        System.out.print("Por favor, ingrese el código del producto a modificar: ");
                        codProducto = leer.nextInt();
                        p = productoServicio.buscarPorCodigo(codProducto);

                        System.out.println("Ingrese los nuevos datos");

                        System.out.print("nombre:");
                        nombre = leer.next();
                        System.out.print("precio: ");
                        precio = leer.nextDouble();
                        System.out.print("código del fabricante: ");
                        codFabricante = leer.nextInt();
                        f = fabricanteServicio.buscarPorCodigo(codFabricante);

                        productoServicio.modificar(p, nombre, precio, f);

                        System.out.println("El producto ha sido modificado con éxito :)");
                        break;

                    default:
                        System.out.println("Cerrando programa...");
                }
            } while (!opcion.equals("9"));

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

}

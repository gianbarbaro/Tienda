/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.util.List;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

/**
 *
 * @author Usuario
 */
public final class ProductoService {

    private final ProductoDAO productoDAO;

    public ProductoService() {
        productoDAO = new ProductoDAO();
    }

    public void guardarDB(String nombre, Double precio, Fabricante f, Integer codigo) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Debe ingresar el nombre del producto");
        }
        if (precio == null) {
            throw new Exception("Debe ingresar le precio del producto");
        }
        if (f == null) {
            throw new Exception("Debe indicar el fabricante del producto");
        }
        if (codigo <= 0) {
            throw new Exception("El código que desea ingresar no es válido");
        }

        Producto p = new Producto();

        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setFabricante(f);
        p.setCodigo(codigo);

        productoDAO.guardarDB(p);
    }

    public void modificar(Producto p, String nombre, Double precio, Fabricante f) throws Exception {

        if (p == null) {
            throw new Exception("El producto que desea modificar no existe");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Debe ingresar un nuevo nombre");
        }
        if (precio == null || precio.isNaN()) {
            throw new Exception("Debe ingresar un nuevo precio");
        }
        if (f == null) {
            throw new Exception("El código de fabricante ingresado no corresponde a ningún fabricante en el sistema");
        }

        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setFabricante(f);

        productoDAO.modificar(p);
    }

//    public List<Producto> listarNombreYPrecio() throws Exception {
//        return productoDAO.listarNombreYPrecio();
//    }
//    public List<Producto> listarNombre() throws Exception {
//        return productoDAO.listarNombres();
//    }
    
    public void mostrarNombres() throws Exception {

        List<Producto> productos = productoDAO.listarTodos();

        System.out.println("Productos disponibles(" + productos.size() + "):");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre());
        }
    }

    public void mostrarNombreYPrecio() throws Exception {

        List<Producto> productos = productoDAO.listarTodos();

        System.out.println("Productos disponibles(" + productos.size() + "):");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + ", $" + producto.getPrecio());
        }
    }

//    public List<Producto> listarSegunPrecio() throws Exception {
//        return productoDAO.listarSegunPrecio();
//    }
    
    public void mostrarSegunPrecio() throws Exception {

        List<Producto> productos = productoDAO.listarSegunPrecio();

        System.out.println("Productos disponibles(" + productos.size() + "):");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

//    public List<Producto> listarPortatiles() throws Exception {
//        return productoDAO.listarPortatiles();
//    }
    
    public void mostrarPortatiles() throws Exception {

        List<Producto> productos = productoDAO.listarPortatiles();

        System.out.println("Productos disponibles(" + productos.size() + "):");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public Producto buscarMasBarato() throws Exception {
        return productoDAO.buscarMasBarato();
    }

    public Producto buscarPorCodigo(Integer codigo) throws Exception {

        if (codigo == null || codigo < 1) {
            throw new Exception("El código ingresado no es válido");
        }
        return productoDAO.buscarPorCodigo(codigo);
    }

//    public List<Producto> listarTodos() throws Exception {
//        return productoDAO.listarTodos();
//    }
    
    public void mostrarTodo() throws Exception {

        List<Producto> productos = productoDAO.listarTodos();

        System.out.println("Productos disponibles(" + productos.size() + "):");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

}

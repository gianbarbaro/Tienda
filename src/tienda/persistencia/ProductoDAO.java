/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.servicios.FabricanteService;

/**
 *
 * @author Usuario
 */
public final class ProductoDAO extends DAO {

    private final FabricanteService fabricanteServicio;

    public ProductoDAO() {
        fabricanteServicio = new FabricanteService();
    }

    public void guardarDB(Producto p) throws Exception {
        try {
            String sql = "INSERT INTO producto VALUES (" + p.getCodigo() + ", '" + p.getNombre() + "', '"
                    + p.getPrecio() + "', '" + p.getFabricante().getCodigo() + "');";

            insertarModificarEliminarDB(sql);
        } catch (Exception e) {
            throw e;
        }
    }

//    public List<Producto> listarNombres() throws Exception {
//        
//        List<Producto> productos = new ArrayList<>();
//
//        try {
//            String sql = "SELECT nombre FROM producto;";
//
//            consultarDB(sql);
//
//            while (resultSet.next()) {
//                Producto p = new Producto();
//                p.setNombre(resultSet.getString(1));
//                productos.add(p);
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            throw e;
//        } finally {
//            desconectarDB();
//        }
//
//        return productos;
//    }
    
//    public List<Producto> listarNombreYPrecio() throws Exception {
//        
//        List<Producto> productos = new ArrayList<>();
//
//        try {
//            String sql = "SELECT nombre, precio FROM producto;";
//
//            consultarDB(sql);
//
//            while (resultSet.next()) {
//                Producto p = new Producto();
//                p.setNombre(resultSet.getString(1));
//                p.setPrecio(resultSet.getDouble(2));
//                productos.add(p);
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            throw e;
//        } finally {
//            desconectarDB();
//        }
//
//        return productos;
//    }
    
    public List<Producto> listarSegunPrecio() throws Exception {

        List<Producto> productos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM producto WHERE precio BETWEEN 120 AND 202;";

            consultarDB(sql);

            while (resultSet.next()) {
                Producto p = new Producto();
                p.setCodigo(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));
                p.setPrecio(resultSet.getDouble(3));

                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante f = fabricanteServicio.buscarPorCodigo(codigo_fabricante);
                p.setFabricante(f);

                productos.add(p);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return productos;
    }

    public List<Producto> listarPortatiles() throws Exception {

        List<Producto> productos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM producto WHERE nombre LIKE '%Portatil%';";

            consultarDB(sql);

            while (resultSet.next()) {
                Producto p = new Producto();
                p.setCodigo(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));
                p.setPrecio(resultSet.getDouble(3));

                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante f = fabricanteServicio.buscarPorCodigo(codigo_fabricante);
                p.setFabricante(f);

                productos.add(p);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return productos;
    }

    public Producto buscarMasBarato() throws Exception {

        Producto p = null;

        try {
            String sql = "SELECT * FROM producto ORDER BY precio ASC LIMIT 1;";

            consultarDB(sql);

            while (resultSet.next()) {
                p = new Producto();
                p.setCodigo(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));
                p.setPrecio(resultSet.getDouble(3));

                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante f = fabricanteServicio.buscarPorCodigo(codigo_fabricante);
                p.setFabricante(f);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return p;
    }

    public Producto buscarPorCodigo(Integer codigo) throws Exception {

        Producto p = null;

        try {
            String sql = "SELECT * FROM producto WHERE codigo = " + codigo + ";";

            consultarDB(sql);

            while (resultSet.next()) {
                p = new Producto();

                p.setCodigo(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));
                p.setPrecio(resultSet.getDouble(3));

                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante f = fabricanteServicio.buscarPorCodigo(codigo_fabricante);
                p.setFabricante(f);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return p;
    }

    public void modificar(Producto p) throws Exception {
        try {
            String sql = "UPDATE producto SET nombre = '" + p.getNombre()
                    + "', precio = " + p.getPrecio()
                    + ", codigo_fabricante = " + p.getFabricante().getCodigo()
                    + " WHERE codigo = " + p.getCodigo() + ";";

            insertarModificarEliminarDB(sql);

        } catch (Exception e) {
            throw e;
        }
    }

    public List<Producto> listarTodos() throws Exception {

        List<Producto> productos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM producto;";

            consultarDB(sql);

            while (resultSet.next()) {
                Producto p = new Producto();
                p.setCodigo(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));
                p.setPrecio(resultSet.getDouble(3));

                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante f = fabricanteServicio.buscarPorCodigo(codigo_fabricante);
                p.setFabricante(f);

                productos.add(p);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return productos;
    }

}

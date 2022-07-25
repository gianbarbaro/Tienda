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

/**
 *
 * @author Usuario
 */
public final class FabricanteDAO extends DAO {

    public void guardarDB(Fabricante f) throws Exception {
        try {
            String sql = "INSERT INTO fabricante VALUES (" + f.getCodigo() + ", '" + f.getNombre() + "');";

            insertarModificarEliminarDB(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarPorCodigo(Integer codigo) throws Exception {

        Fabricante f = null;

        try {
            String sql = "SELECT * FROM fabricante WHERE codigo = " + codigo + ";";

            consultarDB(sql);

            while (resultSet.next()) {
                f = new Fabricante();
                f.setCodigo(resultSet.getInt(1));
                f.setNombre(resultSet.getString(2));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return f;
    }

    public Fabricante buscarPorNombre(String nombre) throws Exception {

        Fabricante f = null;

        try {
            String sql = "SELECT * FROM fabricante WHERE nombre = '" + nombre + "';";

            consultarDB(sql);

            while (resultSet.next()) {
                f = new Fabricante();
                f.setCodigo(resultSet.getInt(1));
                f.setNombre(resultSet.getString(2));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return f;
    }

    public Fabricante ultimoAgregado() throws Exception {

        Fabricante f = null;

        try {
            String sql = "SELECT * FROM fabricante ORDER BY codigo DESC LIMIT 1;";

            consultarDB(sql);

            while (resultSet.next()) {
                f = new Fabricante();
                f.setCodigo(resultSet.getInt(1));
                f.setNombre(resultSet.getString(2));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return f;
    }

    public List<Fabricante> listarTodos() throws Exception {

        List<Fabricante> fabricantes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM fabricante;";

            consultarDB(sql);

            while (resultSet.next()) {
                Fabricante f = new Fabricante();
                f.setCodigo(resultSet.getInt(1));
                f.setNombre(resultSet.getString(2));
                fabricantes.add(f);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            desconectarDB();
        }

        return fabricantes;
    }

}

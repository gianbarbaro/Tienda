/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.util.List;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

/**
 *
 * @author Usuario
 */
public final class FabricanteService {

    private final FabricanteDAO fabricanteDAO;

    public FabricanteService() {
        fabricanteDAO = new FabricanteDAO();
    }

    public void guardarDB(String nombre, Integer codigo) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        if (codigo <= 0) {
            throw new Exception("El código que desea ingresar no es válido");
        }

        Fabricante f = new Fabricante();
        f.setNombre(nombre);
        f.setCodigo(codigo);

        fabricanteDAO.guardarDB(f);
    }

    public Fabricante buscarPorCodigo(Integer codigo) throws Exception {

        if (codigo == null || codigo < 1) {
            throw new Exception("Debe indicar un código");
        }
        return fabricanteDAO.buscarPorCodigo(codigo);
    }

    public Fabricante buscarPorNombre(String nombre) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        return fabricanteDAO.buscarPorNombre(nombre);
    }

    public Fabricante ultimoFabricanteAgregado() throws Exception {
        return fabricanteDAO.ultimoAgregado();
    }

//    public List<Fabricante> listarTodos() throws Exception {
//        return fabricanteDAO.listarTodos();
//    }
    
    public void mostrarTodo() throws Exception {

        List<Fabricante> fabricantes = fabricanteDAO.listarTodos();

        System.out.println("Fabricantes registrados(" + fabricantes.size() + "):");
        fabricantes.forEach((fabricante) -> {
            System.out.println(fabricante);
        });
    }

}

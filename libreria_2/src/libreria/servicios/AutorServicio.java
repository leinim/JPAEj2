/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

/**
 *
 * @author Mile
 */
public class AutorServicio {
    
    private LibroServicio libroServicio;
    private EditorialServicio editorialServicio;
    private final AutorDAO DAO;

    public AutorServicio() {
        this.DAO = new AutorDAO();
    }
    
    public void setServicios(LibroServicio libroServicio, EditorialServicio editorialServicio) {
        this.editorialServicio = editorialServicio;
        this.libroServicio = libroServicio;
    }
    
    public Autor crearAutor(String nombre) {        
        Autor autor = new Autor();
        try {
            if (nombre == null || nombre.trim().isEmpty()){
                throw new Exception("Debe indicar el nombre del autor");
            }
            if (buscarPorNombre(nombre) != null){
                throw new Exception("El autor ya se encuentra registrado");
            }
            autor.setNombre(nombre);
            autor.setAlta(true);
            DAO.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarPorId(Integer id) {        
        try {
            if (id == null){
                throw new Exception("Debe indicar el id");
            }
            Autor autor = DAO.buscarPorId(id);
            if (autor == null){
                throw new Exception("No se obtuvieron resultados");
            }
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public boolean eliminarPorId(Integer id) {        
        try {
            if (id == null){
                throw new Exception("Debe indicar el id");
            }
            Autor autor = DAO.buscarPorId(id);
            if (autor == null){
                throw new Exception("No se obtuvieron resultados");
            }        
            DAO.eliminar(autor);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<Autor> listarAutores() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarPorNombre(String nombre) throws Exception{
        if (nombre == null || nombre.trim().isEmpty()){
            throw new Exception("Debe indicar el nombre");
        }
        Autor autor = DAO.buscarPorNombre(nombre);
        if (autor == null){
            throw new Exception("No se obtuvieron resultados");
        }
        return autor;
    }
}

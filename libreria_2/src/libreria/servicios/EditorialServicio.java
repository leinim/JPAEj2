/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

/**
 *
 * @author Mile
 */
public class EditorialServicio {
    
    private AutorServicio autorServicio;
    private LibroServicio libroServicio;
    private final EditorialDAO DAO;

    public EditorialServicio() {
        this.DAO = new EditorialDAO();
    }
    
    public void setServicios(AutorServicio autorServicio, LibroServicio libroServicio) {
        this.autorServicio = autorServicio;
        this.libroServicio = libroServicio;
    }
    
    public Editorial crearEditorial(String nombre) {        
        Editorial editorial = new Editorial();
        try {
            if (nombre == null || nombre.trim().isEmpty()){
                throw new Exception("Debe indicar el nombre");
            }
            if (DAO.buscarPorNombre(nombre) != null){
                throw new Exception("La editorial ya se encuentra registrada");
            }
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            DAO.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Editorial buscarPorId(Integer id) {
        //id null
        try {
            if (id == null){
                throw new Exception("Debe indicar el id");
            }
            Editorial editorial = DAO.buscarPorId(id);
            if (editorial == null){
                throw new Exception("No se encontro ninguna editorial con el id indicado");
            }
            return editorial;
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
            if (DAO.buscarPorId(id) == null){
                throw new Exception("No existe ninguna editorial con el id indicado");
            }
            DAO.eliminar(DAO.buscarPorId(id));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<Editorial> listarEditoriales() {        
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Editorial buscarPorNombre(String nombre) throws Exception{
        try{
            if (nombre == null || nombre.trim().isEmpty()){
                throw new Exception("Debe indicar el nombre");
            }
            Editorial editorial = DAO.buscarPorNombre(nombre);
            if (editorial == null){
                throw new Exception("No se encontro la editorial");
            }
            return editorial;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

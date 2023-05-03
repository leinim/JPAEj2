/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Editorial;

/**
 *
 * @author Mile
 */
public class EditorialDAO extends DAO<Editorial>{

    @Override
    public void guardar(Editorial editorial) {        
        try{            
            super.guardar(editorial);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    
    @Override
    public void eliminar(Editorial editorial) {           
        try{                        
            super.eliminar(editorial);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    
    public List<Editorial> listarTodos() throws Exception {
        
        try{
            conectar();
            List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e ").getResultList();
            desconectar();
            return editoriales;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
    
    public Editorial buscarPorId(Integer id) throws Exception {
           
        try{
            conectar();
            Editorial editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id LIKE :id").setParameter("id", id).getSingleResult();
            
            desconectar();
            return editorial;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
    
    public Editorial buscarPorNombre(String nombre) throws Exception {
           
        try{
            conectar();
            Editorial editorial = em.createNamedQuery("Editorial.buscarPorNombre", Editorial.class).setParameter("nombre", nombre).getSingleResult();
            
            desconectar();
            return editorial;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
}

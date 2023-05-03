/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;

/**
 *
 * @author Mile
 */
public class AutorDAO extends DAO<Autor>{

    @Override
    public void guardar(Autor autor) {        
        try{            
            super.guardar(autor);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    
    @Override
    public void eliminar(Autor autor) {        
        try{             
            super.eliminar(autor);
        }catch(Exception e){
            System.out.println(e.getMessage());            
        }
    
    }
    
    public List<Autor> listarTodos() throws Exception {
        try{
            conectar();
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a ").getResultList();
            desconectar();
            return autores;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
    
    public Autor buscarPorId(Integer id) throws Exception {        
        try{
            conectar();
            Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id LIKE :id").setParameter("id", id).getSingleResult();            
            desconectar();
            return autor;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
    
    public Autor buscarPorNombre(String nombre) throws Exception {           
        try{
            super.conectar();
            Autor autor = em.createNamedQuery("Autor.buscarPorNombre", Autor.class).setParameter("nombre", nombre).getSingleResult();            
            super.desconectar();
            return autor;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
}

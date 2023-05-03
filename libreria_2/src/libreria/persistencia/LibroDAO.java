/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

/**
 *
 * @author Mile
 */
public class LibroDAO extends DAO<Libro>{

    @Override
    public void guardar(Libro libro) {        
        try{            
            super.guardar(libro);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    
    public void eliminar(Long isbn) throws Exception {
        
        try{            
            Libro libro = buscarPorIsbn(isbn);
            
            super.eliminar(libro);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<Libro> listarTodos() throws Exception {
        try{    
            conectar();
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l ").getResultList();
            desconectar();
            return libros;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
    
    public Libro buscarPorIsbn(Long isbn) throws Exception {
        
        try{    
            conectar();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn LIKE :isbn").setParameter("isbn", isbn).getSingleResult();
                      
            desconectar();
            return libro;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
    
    public Libro buscarPorTitulo(String titulo) throws Exception {
        
        try{    
            conectar();
            Libro libro = em.createNamedQuery("Libro.buscarPorTitulo", Libro.class).setParameter("titulo", titulo).getSingleResult();
            
            desconectar();
            return libro;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Libro buscarPorNombreAutor(String nombreAutor) throws Exception {
        
        try{    
            conectar();
            Libro libro = em.createNamedQuery("Libro.buscarPorNombreAutor", Libro.class).setParameter("nombre", nombreAutor).getSingleResult();
            
            desconectar();
            return libro;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }    
    }
    
    public Libro buscarPorNombreEditorial(String nombreEditorial) throws Exception {
        
        try{
            conectar();
            Libro libro = em.createNamedQuery("Libro.buscarPorNombreEditorial", Libro.class).setParameter("nombre", nombreEditorial).getSingleResult();
            
            desconectar();
            return libro;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }    
    
    @Override
    public void editar(Libro libro) {        
        try{
            super.editar(libro);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
}

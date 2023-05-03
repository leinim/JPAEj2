/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

/**
 *
 * @author Mile
 */
public class LibroServicio {
    
    private AutorServicio autorServicio;
    private EditorialServicio editorialServicio;
    private final LibroDAO DAO;

    public LibroServicio() {
        this.DAO = new LibroDAO();
    }

    public void setServicios(AutorServicio autorServicio, EditorialServicio editorialServicio) {
        this.autorServicio = autorServicio;
        this.editorialServicio = editorialServicio;
    }   
    
    public Libro crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws Exception {
        
        Libro libro = new Libro();
        try {
             if (isbn == null || titulo.trim().isEmpty() || anio == null || ejemplares == null || ejemplaresPrestados == null || ejemplaresRestantes == null || autor == null || editorial == null){
                throw new Exception("Debe indicar todos los datos requeridos");
            }
            if (buscarPorIsbn(isbn) != null){
                throw new Exception("El libro ya se encuentra registrado");
            }
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(true);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            DAO.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Libro buscarPorIsbn(Long isbn) {
        
        try {
            if (isbn == null){
                throw new Exception("Debe indicar el isbn");
            }
            Libro libro = DAO.buscarPorIsbn(isbn);
            if (libro == null){
                throw new Exception("No se encuentro ningun libro con el isbn indicado");
            }
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public boolean eliminarPorIsbn(Long isbn) {
        //isbn null
        try {
            if (isbn == null){
                throw new Exception("Debe indicar el isbn");
            }
            Libro libro = DAO.buscarPorIsbn(isbn);
            if (libro == null){
                throw new Exception("No se encontro ningun libro con el isbn indicado");
            }
            DAO.eliminar(isbn);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<Libro> listarLibros() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void editar(Libro libro) throws Exception {
        try{
            if (libro == null){
                throw new Exception("Debe indicar el libro a editar");
            }
            if (buscarPorIsbn(libro.getIsbn()) == null){
                throw new Exception("El libro no se encuentra registrado");
            }
            DAO.editar(libro);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
        
    }
    
    public Libro buscarPorTitulo(String titulo) throws Exception{
        try{
            if (titulo == null || titulo.trim().isEmpty()){
                throw new Exception("Debe indicar el titulo del libro");
            }
            if (DAO.buscarPorTitulo(titulo) == null){
                throw new Exception("El libro con el titulo indicado no se encuentra registrado");
            }
            return DAO.buscarPorTitulo(titulo);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

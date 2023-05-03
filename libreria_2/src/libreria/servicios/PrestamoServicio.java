/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.Date;
import java.util.List;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import static libreria.entidades.Prestamo_.fechaPrestamo;
import static libreria.entidades.Prestamo_.libro;
import libreria.persistencia.LibroDAO;
import libreria.persistencia.PrestamoDAO;

/**
 *
 * @author Mile
 */
public class PrestamoServicio {
    
    private LibroServicio libroServicio;
    private ClienteServicio clienteServicio;
    private final PrestamoDAO DAO;
    private final LibroDAO libroDAO;

    public PrestamoServicio() {
        this.DAO = new PrestamoDAO();
        this.libroDAO = new LibroDAO();
    }    

    public void setServicios(LibroServicio libroServicio, ClienteServicio clienteServicio) {
        this.libroServicio = libroServicio;
        this.clienteServicio = clienteServicio;
    }

    public Prestamo crearPrestamo(Date fechaPrestamo, Libro libro, Cliente cliente){
        Prestamo prestamo = new Prestamo();
        try {
            prestamo.setCliente(cliente);            
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setLibro(libro);
            DAO.guardar(prestamo);
            return prestamo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Prestamo buscarPorId(Integer id){
        try {
            if (id == null){
                throw new Exception("Debe indicar el id");
            }
            Prestamo prestamo = DAO.buscarPorId(id);
            if (prestamo == null){
                throw new Exception("No se encontro un prestamo con el id indicado");
            }
            return prestamo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void eliminar(Integer id){
        try {
            if (id == null){
                throw new Exception("Debe indicar el id");
            }
            Prestamo prestamo = buscarPorId(id);
            if (prestamo == null){
                throw new Exception("No existe ningun prestamo con el id indicado");
            }
            DAO.eliminar(prestamo);
        } catch (Exception e) {
            System.out.println(e.getMessage());            
        }
    }
    
    public List<Prestamo> listarPrestamos(){
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<Prestamo> buscarPorCliente(String nombreCliente){
        try {
            if (nombreCliente == null || nombreCliente.trim().isEmpty()){
                throw new Exception("Debe indicar el nombre del cliente");
            }
            List<Prestamo> prestamos = DAO.buscarPorCliente(nombreCliente);
            if (prestamos == null){
                throw new Exception("No se encontraron prestamos para el cliente indicado");
            }
            return prestamos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;            
        }
        
    }
    
    public void iniciarPrestamo(Libro libro, Cliente cliente){
        try{ 
            if(libro == null || cliente == null){
               throw new Exception("Debe indicar un libro y un cliente validos"); 
            }
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
            crearPrestamo(new Date(), libro, cliente);            
            libroDAO.editar(libro);            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    
    public void devolucion(Integer id){
        try {
            if (id == null){
                throw new Exception("Debe indicar el id del prestamo");
            }
            Prestamo prestamo = buscarPorId(id);
            if (prestamo == null){
                throw new Exception("No existe un prestamo con el id indicado");
            }
            prestamo.setFechaDevolucion(new Date());
            Libro libro = prestamo.getLibro();
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() - 1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() + 1);
            libroDAO.editar(libro);
            DAO.devolucion(prestamo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }           
    }
    
}

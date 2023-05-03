/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.Date;
import java.util.List;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;

/**
 *
 * @author Mile
 */
public class PrestamoDAO extends DAO<Prestamo>{
    
    private final LibroDAO libroDAO;

    public PrestamoDAO() {
        this.libroDAO = new LibroDAO();
    }    
    
    @Override
    public void guardar(Prestamo prestamo) {
        try {
            super.guardar(prestamo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void eliminar(Prestamo prestamo){
        try {
            super.eliminar(prestamo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Prestamo> listarTodos(){
        try {
            conectar();
            List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p ").getResultList();
            desconectar();
            return prestamos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }    
    }
    
    public Prestamo buscarPorId(Integer id){
        try {
            conectar();
            Prestamo prestamo = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.id LIKE :id").setParameter("id", id).getSingleResult();
            desconectar();
            return prestamo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public List<Prestamo> buscarPorCliente(String nombreCliente) {
        try {
            conectar();
            List<Prestamo> prestamos = em.createNamedQuery("Prestamo.buscarPorCliente", Prestamo.class).setParameter("nombre", nombreCliente).getResultList();
            desconectar();
            return prestamos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }    
        
    public void devolucion(Prestamo prestamo){
        try {
            super.editar(prestamo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

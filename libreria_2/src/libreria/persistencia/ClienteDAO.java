/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Cliente;

/**
 *
 * @author Mile
 */
public class ClienteDAO extends DAO<Cliente> {

    @Override
    public void guardar(Cliente cliente) {
        try {
            super.guardar(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }   
    
    @Override
    public void eliminar(Cliente cliente) {
        try {
            super.eliminar(cliente);
        } catch (Exception e) {
        }
    }
    
    @Override
    public void editar(Cliente cliente){
        try {
            super.editar(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Cliente> listarTodos() {
        try {
            conectar();
            List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c ").getResultList();
            desconectar();
            return clientes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Cliente buscarPorId(Integer id) {
        try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.id LIKE :id").setParameter("id", id).getSingleResult();
            desconectar();
            return cliente;            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Cliente buscarPorDocumento(Long documento){
        try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.documento LIKE :documento").setParameter("documento", documento).getSingleResult();
            desconectar();
            return cliente;            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

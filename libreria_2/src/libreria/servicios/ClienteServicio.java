/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteDAO;

/**
 *
 * @author Mile
 */
public class ClienteServicio {
    
    private PrestamoServicio prestamoServicio;
    private LibroServicio libroServicio;
    private final ClienteDAO DAO;

    public ClienteServicio() {
        this.DAO = new ClienteDAO();
    }
    
    public void setServicios(PrestamoServicio prestamoServicio, LibroServicio libroServicio) {
        this.prestamoServicio = prestamoServicio;
        this.libroServicio = libroServicio;
    }

    public Cliente crearCliente(Long documento, String nombre, String apellido, String telefono){
        Cliente cliente = new Cliente();
        try {
            if (telefono.trim().isEmpty() || documento == null || nombre == null || nombre.trim().isEmpty() || apellido == null || apellido.trim().isEmpty() || telefono == null){
                throw new Exception("Debe indicar todos los datos requeridos");
            }
            if (DAO.buscarPorDocumento(documento) != null){
                throw new Exception("El cliente ya se encuentra registrado");
            }
            cliente.setApellido(apellido);
            cliente.setDocumento(documento);
            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);
            DAO.guardar(cliente);
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }        
    }
    
    public Cliente buscarPorId(Integer id){
        try {
            if (id == null){
                throw new Exception("Debe indicar el id");
            }
            Cliente cliente = DAO.buscarPorId(id);
            if (cliente == null){
                throw new Exception("No se encontro ningun cliente con el id indicado");
            }
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public boolean eliminarPorId(Integer id){
        try {
            if (id == null){
                throw new Exception("Debe indicar el id");
            }
            Cliente cliente = buscarPorId(id);
            if (cliente == null){
                throw new Exception("No se encontro ningun cliente con el id indicado");
            }
            DAO.eliminar(cliente);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<Cliente> listarClientes(){
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public Cliente buscarPorDocumento(Long documento){
        try {
            if (documento == null){
                throw new Exception("Debe indicar el dni");
            }
            Cliente cliente = DAO.buscarPorDocumento(documento);
            if (cliente == null){
                throw new Exception("No se encontro ningun cliente con el documento indicado");
            }
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}

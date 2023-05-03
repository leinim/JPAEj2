/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;

/**
 *
 * @author Mile
 */
public class Menu {
    
    private final LibroServicio ls = new LibroServicio();
    private final EditorialServicio es = new EditorialServicio();
    private final AutorServicio as = new AutorServicio();
    
    public Menu(){
        ls.setServicios(as, es);
        es.setServicios(as, ls);
        as.setServicios(ls, es);
    }
    
    public void ejecucion() throws Exception {
        

    }
}

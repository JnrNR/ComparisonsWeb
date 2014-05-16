/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jnr.wsdlcomaparison.managerbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jnr
 */
@ManagedBean
@ViewScoped
public class MB_TablaControl {

   
    private List<Registro> registros;
    private Registro registroSeleccionado;
    
    
    public Registro getRegistroSeleccionado() {
        return registroSeleccionado;
    }

    public void setRegistroSeleccionado(Registro registroSeleccionado) {
        this.registroSeleccionado = registroSeleccionado;
    }
      
    public List<Registro> getRegistros() {
        return registros;
    }
    /**
     * Creates a new instance of MB_TablaControl
     */
    public MB_TablaControl() {
        registros = new ArrayList<Registro>();
        registros.add(new Registro("SA00", "OA00", "SB00", "OB00", "S00"));
    }
    
    public void instertarRegistro(){
        boolean a;
        for(int i=1;i<100000; i++){
            for(int j=1; j<10000;j++)
                a= true;
        }
        registros.add(new Registro("SA", "OA", "SB", "OB", "S"));
    } 
    
}

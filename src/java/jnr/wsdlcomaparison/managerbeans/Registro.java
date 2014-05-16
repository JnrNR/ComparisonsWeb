/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jnr.wsdlcomaparison.managerbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jnr
 */
@ManagedBean
@ViewScoped
public class Registro {

    public String getServicioA() {
        return servicioA;
    }

    public void setServicioA(String servicioA) {
        this.servicioA = servicioA;
    }

    public String getServicioB() {
        return servicioB;
    }

    public void setServicioB(String servicioB) {
        this.servicioB = servicioB;
    }

    public String getOperacionA() {
        return operacionA;
    }

    public void setOperacionA(String operacionA) {
        this.operacionA = operacionA;
    }

    public String getOperacionB() {
        return operacionB;
    }

    public void setOperacionB(String operacionB) {
        this.operacionB = operacionB;
    }

    public String getSimilitud() {
        return similitud;
    }

    public void setSimilitud(String similitud) {
        this.similitud = similitud;
    }
    
    private String servicioA;
    private String servicioB;
    private String operacionA;
    private String operacionB;
    private String similitud;
    
    public Registro(String servicioA, String operacionA, String servicioB, String operacionB, String similitud){
        this.operacionA = operacionA;
        this.operacionB = operacionB;
        this.servicioA = servicioA;
        this.servicioB = servicioB;
        this.similitud = similitud;
    }
    
}


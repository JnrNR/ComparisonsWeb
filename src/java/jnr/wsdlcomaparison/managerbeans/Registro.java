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

    public String getSimilitudT() {
        return similitudT;
    }

    public void setSimilitudT(String similitudT) {
        this.similitudT = similitudT;
    }

    public String getSimilitudE() {
        return similitudE;
    }

    public void setSimilitudE(String similitudE) {
        this.similitudE = similitudE;
    }

    public String getSimilitudS() {
        return similitudS;
    }

    public void setSimilitudS(String similitudS) {
        this.similitudS = similitudS;
    } 

    public String getLinkGoogleChartOperacionA() {
        return linkGoogleChartOperacionA;
    }

    public void setLinkGoogleChartOperacionA(String linkGoogleChartOperacionA) {
        this.linkGoogleChartOperacionA = linkGoogleChartOperacionA;
    }

    public String getLinkGoogleChartOperacionB() {
        return linkGoogleChartOperacionB;
    }

    public void setLinkGoogleChartOperacionB(String lonkGoogleChartOperacionB) {
        this.linkGoogleChartOperacionB = lonkGoogleChartOperacionB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getADotString(){
        return linkGoogleChartOperacionA.substring(46);
    }
    
    public String getBDotString(){
        return linkGoogleChartOperacionB.substring(46);
    }
    
    
    
    private int id;
    private String servicioA;
    private String servicioB;
    private String operacionA;
    private String operacionB;
    private String similitudT;
    private String similitudE;
    private String similitudS;
    private String linkGoogleChartOperacionA;
    private String linkGoogleChartOperacionB;
    
    
    public Registro(int id, String servicioA, String operacionA, String servicioB, String operacionB, String linkGoogleChartOperacionA, String linkGoogleChartOperacionB, String similitudT, String similitudE, String similitudS){
        this.id =id;
        this.operacionA = operacionA;
        this.operacionB = operacionB;
        this.servicioA = servicioA;
        this.servicioB = servicioB;
        this.similitudT = similitudT;
        this.similitudE = similitudE;
        this.similitudS = similitudS;
        this.linkGoogleChartOperacionA = linkGoogleChartOperacionA;
        this.linkGoogleChartOperacionB = linkGoogleChartOperacionB;
    }
    
}


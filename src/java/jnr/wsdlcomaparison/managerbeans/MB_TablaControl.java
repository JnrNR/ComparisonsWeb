/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jnr.wsdlcomaparison.managerbeans;

import com.predic8.xml.util.ResourceDownloadException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import jnr.operationsmatcher.MatcherResult;
import jnr.operationsmatcher.TreeMatcher;
import jnr.utilities.Directorio;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Jnr
 */
@ManagedBean
@ViewScoped
public class MB_TablaControl {

   
    private List<Registro> registros;
    private Registro registroSeleccionado;
    private TreeMatcher comparador;

    private String op1_wsdl1;
    private String op1_wsdl2;
    private boolean pruebaDosServicos;
    
    private String op2_patron;
    private String op2_repositorio;
    private boolean pruebaServiciosServico;
    
    private String op3_repositorio;
    private boolean pruebaRepositorio;

   
    
    
    public String getOp1_wsdl1() {
        return op1_wsdl1;
    }

    public void setOp1_wsdl1(String op1_wsdl1) {
        this.op1_wsdl1 = op1_wsdl1;
    }

    public String getOp1_wsdl2() {
        return op1_wsdl2;
    }

    public void setOp1_wsdl2(String op1_wsdl2) {
        this.op1_wsdl2 = op1_wsdl2;
    }

    public boolean isPruebaDosServicos() {
        return pruebaDosServicos;
    }

    public void setPruebaDosServicos(boolean pruebaDosServicos) {
        this.pruebaDosServicos = pruebaDosServicos;
    }

    public String getOp2_patron() {
        return op2_patron;
    }

    public void setOp2_patron(String op2_patron) {
        this.op2_patron = op2_patron;
    }

    public String getOp2_repositorio() {
        return op2_repositorio;
    }

    public void setOp2_repositorio(String op2_repositorio) {
        this.op2_repositorio = op2_repositorio;
    }

    public boolean isPruebaServiciosServico() {
        return pruebaServiciosServico;
    }

    public void setPruebaServiciosServico(boolean pruebaServiciosServico) {
        this.pruebaServiciosServico = pruebaServiciosServico;
    }

    public String getOp3_repositorio() {
        return op3_repositorio;
    }

    public void setOp3_repositorio(String op3_repositorio) {
        this.op3_repositorio = op3_repositorio;
    }

    public boolean isPruebaRepositorio() {
        return pruebaRepositorio;
    }

    public void setPruebaRepositorio(boolean pruebaRepositorio) {
        this.pruebaRepositorio = pruebaRepositorio;
    }

    
    
    
    
   
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
        op1_wsdl1 = op1_wsdl2 = op2_patron = op2_repositorio = op3_repositorio = ""; 
        
        comparador = new TreeMatcher();

        //registros.add(new Registro("SA00", "OA00", "SB00", "OB00", "S00"));
    }
    
    
    
    public void compararWSDLUnoAUno(){
        registros = new ArrayList<Registro>();
                
        List<MatcherResult> resultados;
        try{
            resultados = comparador.matchWSDLOnetoOne_URL(op1_wsdl1, op1_wsdl2);
            for(int i=0; i<resultados.size(); i++){
                registros.add(new Registro(i+1, resultados.get(i).getServicioA(), resultados.get(i).getServicioA(), resultados.get(i).getServicioB(), resultados.get(i).getEstructuraB(), resultados.get(i).getLinkGoogleChartestructuraA(), resultados.get(i).getLinkGoogleChartestructuraB(), Float.toString(resultados.get(i).getPorcentaje())));
            }
        }catch(ResourceDownloadException e){
            Mensajes.errorStandard("Error", "Se excedio el tiempo de respuesta del repositorio\tError>" + e.toString());
        }catch(Exception e){
            Mensajes.errorStandard("Error", "Se produjo un error durante la comparacion\tError>" + e.toString());
        }

    }
    
    public void compararWSDLyRepositorio(){
        registros = new ArrayList<Registro>();
                
        List<MatcherResult> resultados;
        try{
            resultados = comparador.matchWSDLAndDirectory_URL(op2_patron, op2_repositorio);
            for(int i=0; i<resultados.size(); i++){
                registros.add(new Registro(i+1, resultados.get(i).getServicioA(), resultados.get(i).getServicioA(), resultados.get(i).getServicioB(), resultados.get(i).getEstructuraB(), resultados.get(i).getLinkGoogleChartestructuraA(), resultados.get(i).getLinkGoogleChartestructuraB(), Float.toString(resultados.get(i).getPorcentaje())));
            }
        }catch(ResourceDownloadException e){
            Mensajes.errorStandard("Error", "Se excedio el tiempo de respuesta del repositorio\tError>" + e.toString());
        }catch(Exception e){
            Mensajes.errorStandard("Error", "Se produjo un error durante la comparacion\tError>" + e.toString());
        }
        
    }
    
    public void compararRepositorio(){
        registros = new ArrayList<Registro>();
                
        List<MatcherResult> resultados;
        try{
            resultados = comparador.matchWSDLDirectory_URL(op3_repositorio);
            for(int i=0; i<resultados.size(); i++){
                registros.add(new Registro(i+1, resultados.get(i).getServicioA(), resultados.get(i).getServicioA(), resultados.get(i).getServicioB(), resultados.get(i).getEstructuraB(), resultados.get(i).getLinkGoogleChartestructuraA(), resultados.get(i).getLinkGoogleChartestructuraB(), Float.toString(resultados.get(i).getPorcentaje())));
            }
        }catch(ResourceDownloadException e){
            Mensajes.errorStandard("Error", "Se excedio el tiempo de respuesta del repositorio\tError>" + e.toString());
        }catch(Exception e){
            Mensajes.errorStandard("Error", "Se produjo un error durante la comparacion\tError>" + e.toString());
        }

    } 
    
    public void insertarregristro2(){
        System.out.println("LEYENDO DIRECTORIO");
        Directorio.listarElementosDirectorio(".");
        registros.add(new Registro(0, " ", " ", " ", " ", " ", " ", " "));
    }
   
    
    public void setPruebaDosServicios(){
        if(pruebaDosServicos){
            op1_wsdl1 = "http://148.247.102.37:8080/wsdlcomparison/servicios/precisionRecall/repositorio/university_academic-support-staff_service.wsdl";
            op1_wsdl2 = "http://148.247.102.37:8080/wsdlcomparison/servicios/precisionRecall/repositorio/_digitalstandardpriceprice_MediaMarktservice.wsdl";
        }else{
            op1_wsdl1 = op1_wsdl2 = "";
        }
        
    }
    
    public void setPruebaServiciosServicio(){
        if(pruebaServiciosServico){
            op2_repositorio = "http://148.247.102.37:8080/wsdlcomparison/servicios/precisionRecall/repositorio/";
            op2_patron = "http://148.247.102.37:8080/wsdlcomparison/servicios/precisionRecall/patron/1personbicycle4wheeledcar_price_service.wsdl";
        }else{
            op2_repositorio = op2_patron = "";
        }
    }
   
    public void setPruebaRepositorio(){
        if(pruebaRepositorio){
            op3_repositorio = "http://148.247.102.37:8080/wsdlcomparison/servicios/precisionRecall/repositorio/";
        }else{
            op3_repositorio = "";
        }
    }
    
    public void manejadorUpload(FileUploadEvent event){
        UploadedFile file = event.getFile();
        Mensajes.infoStandard("Succesfull Upload", " " + file.getFileName() + " is uploaded.");

    }
    
    
    
    public static class Mensajes{
            public static void infoStandard(String titulo, String mensaje) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));
            }

            public static void warnStandard(String titulo, String mensaje) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje));
            }

            public static void errorStandard(String titulo, String mensaje) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje));
            }

            public static void fatalStandard(String titulo, String mensaje) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje));
            }
    }
    
}

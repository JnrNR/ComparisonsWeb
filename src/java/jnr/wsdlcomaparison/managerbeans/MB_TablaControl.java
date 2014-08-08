/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jnr.wsdlcomaparison.managerbeans;

import com.predic8.xml.util.ResourceDownloadException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import jnr.operationsmatcher.MatcherResult;
import jnr.operationsmatcher.TreeMatcher;
import jnr.utilities.CompresionDeDatos;
import jnr.utilities.Directorio;
import org.apache.commons.io.FilenameUtils;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Jnr
 */
@ManagedBean
@ViewScoped
public class MB_TablaControl {

    private boolean skip;
    private StreamedContent file;
    
    private String sesionKey;
    private String projectPath;
   
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

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    
    
    
    
    /**
     * Creates a new instance of MB_TablaControl
     */
    public MB_TablaControl() {
        op1_wsdl1 = op1_wsdl2 = op2_patron = op2_repositorio = op3_repositorio = ""; 
        
        comparador = new TreeMatcher();

        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ExternalContext externalContext = facesContext.getExternalContext();  
        HttpSession session = (HttpSession)externalContext.getSession(true);  
        
        sesionKey = session.getId() + "_" + Long.toString(System.currentTimeMillis());
        projectPath = externalContext.getRealPath("/");
        
        new File("ComparisonsWeb_TMP").mkdirs();
        
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
        Mensajes.infoStandard("sessionKey", sesionKey);
        
        new File("ComparisonsWeb_TMP/"+sesionKey).mkdirs();
        new File("ComparisonsWeb_TMP/"+sesionKey+"/WSDL").mkdirs();
        new File("ComparisonsWeb_TMP/"+sesionKey+"/RDF").mkdirs();
        
        File tmpfile = new File("ComparisonsWeb_TMP/"+sesionKey+"/WSDL/"+file.getFileName());  
        
        try {
            InputStream is = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(tmpfile);
            byte buf[] = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0)
                out.write(buf, 0, len);
            is.close();
            out.close();
            
            Mensajes.infoStandard("Ubicacion","ComparisonsWeb_TMP/"+sesionKey+"/WSDL/"+file.getFileName());
        }catch(Exception e){
            Mensajes.fatalStandard("Error creando archivo", e.toString());
        }
    }
    
    public void gettingRDFs(){
        
        comparador.getRDFsFromDirectory("ComparisonsWeb_TMP/"+sesionKey+"/WSDL", "ComparisonsWeb_TMP/"+sesionKey+"/RDF");
        
        CompresionDeDatos compresor = new CompresionDeDatos();
        compresor.directorioZip("ComparisonsWeb_TMP/"+sesionKey+"/RDF", "ComparisonsWeb_TMP/"+sesionKey+"/"+sesionKey+".zip");

        try {        
            File zipFile = new File("ComparisonsWeb_TMP/"+sesionKey+"/"+sesionKey+".zip");
            InputStream stream;
            stream = new FileInputStream (zipFile);
            file = new DefaultStreamedContent(stream, "compress/zip", sesionKey+".zip");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MB_TablaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String wizardRDFflow(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
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

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
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import jnr.operationsmatcher.MatcherResult;
import jnr.operationsmatcher.TreeMatcher;
import jnr.utilities.Directorio;

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

    private String textoUno;
    private String textoDos;
    
    public String getTextoDos(){
        return textoDos;
    }
    
    public void setTextoDos(String textoDos){
        this.textoDos = textoDos;
    }

    /**
     * Get the value of textoUno
     *
     * @return the value of textoUno
     */
    public String getTextoUno() {
        return textoUno;
    }

    /**
     * Set the value of textoUno
     *
     * @param textoUno new value of textoUno
     */
    public void setTextoUno(String textoUno) {
        this.textoUno = textoUno;
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
        comparador = new TreeMatcher();
        registros = new ArrayList<Registro>();
        registros.add(new Registro("SA00", "OA00", "SB00", "OB00", "S00"));
    }
    
    public void instertarRegistro(){
        boolean a;
        for(int i=1;i<100000; i++){
            for(int j=1; j<10000;j++)
                a= true;
        }
                
        List<MatcherResult> resultados;
        resultados = comparador.matchWSDLDirectory_URL(textoUno);
        for(int i=0; i<resultados.size(); i++){
            registros.add(new Registro(resultados.get(i).getServicioA(), resultados.get(i).getServicioA(), resultados.get(i).getServicioB(), resultados.get(i).getEstructuraB(), Float.toString(resultados.get(i).getPorcentaje())));
        }
        
        //comparador.matchWSDLAndDirectory("D:\\CINVESTAV\\Tesis\\servicios\\precisionRecall\\patron\\1personbicycle4wheeledcar_price_service.wsdl", "D:\\CINVESTAV\\Tesis\\servicios\\precisionRecall\\repositorio");
              
        
        //jnr.utilities.Directorio.listarElementosDeURL("http://148.247.102.37:8080/wsdlcomparison/servicios/pruebas2/");
    } 
    
    public void insertarregristro2(){
        System.out.println("LEYENDO DIRECTORIO");
        Directorio.listarElementosDirectorio(".");
        registros.add(new Registro(" ", " ", " ", " ", " "));
    }
    
}

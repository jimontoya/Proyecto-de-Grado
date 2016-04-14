/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dto.Entrenamiento;
import com.neurocienciasueb.dto.VariableEntrenamiento;
import com.neurocienciasueb.service.EntrenamientoService;
import com.neurocienciasueb.service.VariableEntrenamientoService;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Isaac
 */
@Controller
@Scope("session")
public class EntrenamientoController extends BaseController implements Serializable{
    
    private static final long serialVersionUID = 2123538437465357306L;
    
    @Autowired
    private EntrenamientoService service;
    
    @Autowired
    private VariableEntrenamientoService variableEntrenamientoService;
    
    @Getter
    @Setter
    private Entrenamiento entrenamiento;
    
    @Getter
    @Setter
    private VariableEntrenamiento variableEntrenamiento;

    public EntrenamientoController() {
        this.entrenamiento = new Entrenamiento();
        this.variableEntrenamiento = new VariableEntrenamiento();
    }
    
    public void limpiar(){
        this.entrenamiento = new Entrenamiento();        
    }
    
    public void limpiarVariableEntrenamiento(){
        this.variableEntrenamiento = new VariableEntrenamiento();
        variableEntrenamiento.setTipoDato("color");
        variableEntrenamiento.setTipoVariable("E");
    }
    
    public List<Entrenamiento> listarTodo(){
        return service.listarActivos();
    }
    
    public List<Entrenamiento> listarTodoAI(){
        return service.listarTodo();
    }
    
    public List<VariableEntrenamiento> listarVariableEntrenamientoPorEntrenamiento(Entrenamiento e){
        return variableEntrenamientoService.listarPorEntrenamientoId(e.getId());
    }
    
    public List<VariableEntrenamiento> listarVariableEntrenamientoSeleccionado(){
        
        return variableEntrenamientoService.listarPorEntrenamientoId(entrenamiento.getId());
    }
    
    public void guardarOActualizar(){
        entrenamiento.setEstado("A");
        service.guardarOActualizar(entrenamiento);
        addMessage("Se ha guardado correctamente el entrenamiento "+entrenamiento.getNombre(), FacesMessage.SEVERITY_INFO);
        addCallbackParam("success", true);
    }
    
    public void guardarVariableEntrenamiento(){
        this.variableEntrenamiento.setEntrenamiento(entrenamiento);
        variableEntrenamientoService.guardarOActualizar(this.variableEntrenamiento);        
        addMessage("Se ha guardado correctamente la variable "+this.variableEntrenamiento.getNombre(), FacesMessage.SEVERITY_INFO);
        this.variableEntrenamiento = new VariableEntrenamiento();
        addCallbackParam("success", true);
    }
    
    public void cambiarEstado(Entrenamiento ent){
        if(ent.getEstado().equals("A")){
            ent.setEstado("I");
        }else{
            ent.setEstado("A");
        }
        service.guardarOActualizar(ent);
    }
    
    public void eliminar(Entrenamiento entrenamiento){
        try {
            service.eliminar(entrenamiento);
            addMessage("Se ha eliminado correctamente el entrenamiento", FacesMessage.SEVERITY_INFO);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            addMessage("No es posible eliminar el entrenamiento "+ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void eliminarVariableEntrenamiento(VariableEntrenamiento ve){
        try {
            variableEntrenamientoService.eliminar(ve);
            addMessage("Se ha eliminado correctamente la variable", FacesMessage.SEVERITY_INFO);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            addMessage("No es posible eliminar la variable "+ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public String onFlowProcess(FlowEvent event){
        if(event.getOldStep().equals("info") && event.getNewStep().equals("vars")) guardarOActualizar();        
        return event.getNewStep();
    }
    
}

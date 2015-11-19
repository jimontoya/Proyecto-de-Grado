/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.Entrenamiento;
import com.neurocienciasueb.service.EntrenamientoService;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
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
    
    private Entrenamiento entrenamiento;

    public EntrenamientoController() {
        this.entrenamiento = new Entrenamiento();
    }
    
    public void limpiar(){
        this.entrenamiento = new Entrenamiento();
    }
    
    public List<Entrenamiento> listarTodo(){
        return service.listarTodo();
    }
    
    public void guardarOActualizar(){
        service.guardarOActualizar(entrenamiento);
        addMessage("Se ha guardado correctamente el entrenamiento "+entrenamiento.getNombre(), FacesMessage.SEVERITY_INFO);
        addCallbackParam("success", true);
    }
    
    public void eliminar(Entrenamiento entrenamiento){
        service.eliminar(entrenamiento);
        addMessage("Se ha eliminado correctamente el entrenamiento", FacesMessage.SEVERITY_INFO);
    }

    public Entrenamiento getEntrenamiento() {
        return entrenamiento;
    }

    public void setEntrenamiento(Entrenamiento entrenamiento) {
        this.entrenamiento = entrenamiento;
    }
    
    
    
    
}

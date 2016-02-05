/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dto.ClasificacionEntrenamiento;
import com.neurocienciasueb.service.ClasificacionEntrenamientoService;
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
public class ClasificacionEntrenamientoController extends BaseController implements Serializable{
    
    private static final long serialVersionUID = 1794538412465357306L;
    
    @Autowired
    private ClasificacionEntrenamientoService service;
    
    private ClasificacionEntrenamiento clasificacionEntrenamiento;

    public ClasificacionEntrenamientoController() {
        this.clasificacionEntrenamiento = new ClasificacionEntrenamiento();
    }
    
    public void limpiar(){
        this.clasificacionEntrenamiento = new ClasificacionEntrenamiento();
    }
    
    public List<ClasificacionEntrenamiento> listarTodo(){
        return service.listarTodo();
    }
    
    public void guardarOActualizar(){
        service.guardarOActualizar(clasificacionEntrenamiento);
        addMessage("Se ha guardado correctamente la clasificacion "+clasificacionEntrenamiento.getNombre(), FacesMessage.SEVERITY_INFO);
        addCallbackParam("success", true);
    }
    
    public void eliminar(ClasificacionEntrenamiento clasificacionEntrenamiento){
        try {
            service.eliminar(clasificacionEntrenamiento);
            addMessage("Se ha eliminado correctamente la clasificacion", FacesMessage.SEVERITY_INFO);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            addMessage("No es posible eliminar la clasificacion "+ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public ClasificacionEntrenamiento getClasificacionEntrenamiento() {
        return clasificacionEntrenamiento;
    }

    public void setClasificacionEntrenamiento(ClasificacionEntrenamiento clasificacionEntrenamiento) {
        this.clasificacionEntrenamiento = clasificacionEntrenamiento;
    }
    
    
    
    
}

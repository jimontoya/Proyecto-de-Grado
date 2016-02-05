/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dto.Grupo;
import com.neurocienciasueb.service.GrupoService;
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
public class GrupoController extends BaseController implements Serializable{
    
    private static final long serialVersionUID = 2123538437465357306L;
    
    @Autowired
    private GrupoService service;
    
    private Grupo grupo;

    public GrupoController() {
        this.grupo = new Grupo();
    }
    
    public void limpiar(){
        this.grupo = new Grupo();
    }
    
    public List<Grupo> listarTodo(){
        return service.listarTodo();
    }
    
    public void guardarOActualizar(){
        service.guardarOActualizar(grupo);
        addMessage("Se ha guardado correctamente el grupo "+grupo.getNombre(), FacesMessage.SEVERITY_INFO);
        addCallbackParam("success", true);
    }
    
    public void eliminar(Grupo grupo){
        try {
            service.eliminar(grupo);
            addMessage("Se ha eliminado correctamente el grupo", FacesMessage.SEVERITY_INFO);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            addMessage("No es posible eliminar el grupo "+ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
    
    
}

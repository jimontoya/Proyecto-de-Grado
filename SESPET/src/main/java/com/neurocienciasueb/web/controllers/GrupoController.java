<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

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
        service.eliminar(grupo);
        addMessage("Se ha eliminado correctamente el grupo", FacesMessage.SEVERITY_INFO);
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
    
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dto.Grupo;
import com.neurocienciasueb.dto.Usuario;
import com.neurocienciasueb.dto.UsuarioGrupo;
import com.neurocienciasueb.service.GrupoService;
import com.neurocienciasueb.service.UsuarioGrupoService;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    @Autowired
    private UsuarioGrupoService usuarioGrupoService;
    
    private Grupo grupo;
    
    private List<Usuario> pacientes;

    public GrupoController() {
        this.grupo = new Grupo();
    }    
    
    public void limpiar(){
        this.grupo = new Grupo();
    }
    
    public List<Grupo> listarTodo(){
        return service.listarTodo();
    }
    
    public void inicioAsignarPacientesAGrupo(Grupo g){
        pacientes = new ArrayList<>();
        this.grupo = g;
    }
    
    public void asignarPacientesAGrupo(){
        for(Usuario aux:pacientes){
            try{
               UsuarioGrupo us = new UsuarioGrupo();
                us.setUsuario(aux.getUserName());
                us.setGrupo(grupo.getId());
                usuarioGrupoService.guardar(us); 
            }catch (Exception ex){}            
        }
        addMessage("Se han asignado los pacientes", FacesMessage.SEVERITY_INFO);
    }
    
    public void eliminarUsuarioGrupo(Usuario us, Grupo g){
        UsuarioGrupo aux = new UsuarioGrupo();
        aux.setGrupo(g.getId());
        aux.setUsuario(us.getUserName());
        usuarioGrupoService.eliminar(aux);
        addMessage("Se ha removido con exito", FacesMessage.SEVERITY_INFO);
    }
    
    public List<Usuario> listarPacientesPorGrupo(int idGrupo){
        return usuarioGrupoService.listarPacientesPorGrupo(idGrupo);
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

    public List<Usuario> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Usuario> pacientes) {
        this.pacientes = pacientes;
    }
    
    
    
    
}
>>>>>>> 15fc32ced39cf361a80e126b964e70a0fda63d2c

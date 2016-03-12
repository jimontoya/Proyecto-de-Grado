/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.AsignacionEntrenamientoUsuario;
import com.neurocienciasueb.dto.Usuario;
import com.neurocienciasueb.dto.ValorVariableEntrenamiento;
import com.neurocienciasueb.service.AsignacionEntrenamientoUsuarioService;
import com.neurocienciasueb.service.ValorVariableEntrenamientoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Isaac
 */
@Controller
@Scope("session")
public class OrdenEntrenamientosController extends BaseController implements Serializable{
    
    private static final long serialVersionUID = 211233825357306L;
    
    @Autowired
    private AsignacionEntrenamientoUsuarioService asignacionEntrenamientoUsuarioService;
    
    @Autowired
    private ValorVariableEntrenamientoService valorVariableEntrenamientoService;
    
    private Usuario pacienteSeleccionado;
    
    private AsignacionEntrenamientoUsuario asignacionEntrenamientoUsuarioSeleccionado;
    
    public void actualizarOrden(){
        asignacionEntrenamientoUsuarioService.guardarOActualizar(asignacionEntrenamientoUsuarioSeleccionado);
    }
    
    public List<ValorVariableEntrenamiento> getValorVariableEntrenamiento(int idAsignacion){
        return valorVariableEntrenamientoService.findByAsignacionEntrenamientoUsuario(idAsignacion);
    }

    public List<AsignacionEntrenamientoUsuario> getAsignacionEntrenamientosPaciente(){
        if(pacienteSeleccionado != null){
        return asignacionEntrenamientoUsuarioService.findByUsuarioIdAndRealizadoOrderByOrdenAsc(pacienteSeleccionado.getUsername(), "N");
        }else{
            return new ArrayList();
        }
    }
    
    public Usuario getPacienteSeleccionado() {
        return pacienteSeleccionado;
    }

    public void setPacienteSeleccionado(Usuario pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;
    }

    public AsignacionEntrenamientoUsuario getAsignacionEntrenamientoUsuarioSeleccionado() {
        return asignacionEntrenamientoUsuarioSeleccionado;
    }

    public void setAsignacionEntrenamientoUsuarioSeleccionado(AsignacionEntrenamientoUsuario asignacionEntrenamientoUsuarioSeleccionado) {
        this.asignacionEntrenamientoUsuarioSeleccionado = asignacionEntrenamientoUsuarioSeleccionado;
    }
    
    
    
}

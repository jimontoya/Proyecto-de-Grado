/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.AsignacionEntrenamientoUsuario;
import com.neurocienciasueb.dto.Entrenamiento;
import com.neurocienciasueb.service.AsignacionEntrenamientoUsuarioService;
import com.neurocienciasueb.service.ValorVariableEntrenamientoService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Isaac
 */
@Controller
@Scope("session")
public class PacienteController extends BaseController implements Serializable{
    
    private static final long serialVersionUID = 2123538437465357306L;
    
    @Autowired
    private AsignacionEntrenamientoUsuarioService asignacionEntrenamientoUsuarioService;
    
    @Autowired
    private ValorVariableEntrenamientoService valorVariableEntrenamientoService;
    
    private Entrenamiento entrenamientoActual;
    
    private AsignacionEntrenamientoUsuario asignacionEntrenamientoUsuario;
    
    private String salida;
    
    public void aRealizar(){
        asignacionEntrenamientoUsuario = asignacionEntrenamientoUsuarioService.findFirstByUsuarioIdAndRealizadoOrderByOrdenAsc(getUsuario().getUsername(), "N");
        entrenamientoActual = asignacionEntrenamientoUsuario.getEntrenamiento();
    }
    
    public String codigo(){
        if(entrenamientoActual != null){
            String codigo=entrenamientoActual.getCodigo();
            for(Object[] parametros : valorVariableEntrenamientoService.arregloParametros(asignacionEntrenamientoUsuario.getId(), "E")){
                codigo = codigo.replaceAll("!"+parametros[0]+"!", parametros[1]+"");
            }
            return codigo;
        }else{
            return "No tiene asignado ningún Entrenamiento";
        }
    }

    public Entrenamiento getEntrenamientoActual() {
        return entrenamientoActual;
    }

    public void setEntrenamientoActual(Entrenamiento entrenamientoActual) {
        this.entrenamientoActual = entrenamientoActual;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }
    
    
}

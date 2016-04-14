/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.AsignacionEntrenamientoUsuario;
import com.neurocienciasueb.dto.Entrenamiento;
import com.neurocienciasueb.dto.ValorVariableEntrenamientoDTO;
import com.neurocienciasueb.dto.VariableEntrenamiento;
import com.neurocienciasueb.service.AsignacionEntrenamientoUsuarioService;
import com.neurocienciasueb.service.ValorVariableEntrenamientoService;
import com.neurocienciasueb.service.VariableEntrenamientoService;
import java.io.Serializable;
import org.primefaces.context.RequestContext;
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
    
    @Autowired
    private VariableEntrenamientoService variableEntrenamientoService;
    
    private Entrenamiento entrenamientoActual;
    
    private AsignacionEntrenamientoUsuario asignacionEntrenamientoUsuario;
    
    private String salida;
    
    public void aRealizar(){
        asignacionEntrenamientoUsuario = asignacionEntrenamientoUsuarioService.findFirstByUsuarioIdAndRealizadoOrderByOrdenAsc(getUsuario().getUsername(), "N");
        if(asignacionEntrenamientoUsuario != null){
        entrenamientoActual = asignacionEntrenamientoUsuario.getEntrenamiento();
        }else{
            entrenamientoActual = null;
        }
    }
    
    public String codigo(){
        if(entrenamientoActual != null){
            String codigo=entrenamientoActual.getCodigo();
            for(Object[] parametros : valorVariableEntrenamientoService.arregloParametros(asignacionEntrenamientoUsuario.getId(), "E")){
                codigo = codigo.replaceAll("!"+parametros[0]+"!", parametros[1]+"");
            }
            return codigo;
        }else{
            return "alert('No se tiene asignado ningún entrenamiento');";
        }
    }
    
    public void terminarEntrenamiento(){
        asignacionEntrenamientoUsuario.setRealizado("S");
            asignacionEntrenamientoUsuarioService.guardarOActualizar(asignacionEntrenamientoUsuario);
            aRealizar();
            RequestContext.getCurrentInstance().update("form");
    }
    
    public void terminar(){        
            for(VariableEntrenamiento aux : variableEntrenamientoService.findByEntrenamientoIdAndTipoVariable(asignacionEntrenamientoUsuario.getEntrenamiento().getId(),"S")){
                ValorVariableEntrenamientoDTO temp = new ValorVariableEntrenamientoDTO();
                temp.setAsignacionEntrenamientoUsuario(asignacionEntrenamientoUsuario.getId());
                temp.setVariableEntrenamiento(aux.getId());
                temp.setValor(buscarValor(aux.getReferencia()));
                valorVariableEntrenamientoService.guardarOActualizar(temp);
            }
            salida = new String();
            asignacionEntrenamientoUsuario.setRealizado("S");
            asignacionEntrenamientoUsuarioService.guardarOActualizar(asignacionEntrenamientoUsuario);
            aRealizar();
            RequestContext.getCurrentInstance().update("form");
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

    private String buscarValor(String tipoDato) {
        String[] split = salida.split(";");
        for(String aux: split){
            if(aux.contains(tipoDato)){
                return aux.split("=")[1];
            }
        }
        return "";
    }
    
    
}

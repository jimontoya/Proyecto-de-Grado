/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.AsignacionEntrenamientoUsuario;
import com.neurocienciasueb.dto.Usuario;
import com.neurocienciasueb.dto.ValorVariableEntrenamiento;
import com.neurocienciasueb.dto.ValorVariableEntrenamientoDTO;
import com.neurocienciasueb.dto.VariableEntrenamiento;
import com.neurocienciasueb.service.AsignacionEntrenamientoUsuarioService;
import com.neurocienciasueb.service.EntrenamientoService;
import com.neurocienciasueb.service.UsuarioService;
import com.neurocienciasueb.service.ValorVariableEntrenamientoService;
import com.neurocienciasueb.service.VariableEntrenamientoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
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
public class AsignacionEntrenamientoController extends BaseController implements Serializable{
    
    private static final long serialVersionUID = 21123384375357306L;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private VariableEntrenamientoService variableEntrenamientoService;
    
    @Autowired
    private AsignacionEntrenamientoUsuarioService asginacAsignacionEntrenamientoUsuarioService;
    
    @Autowired
    private ValorVariableEntrenamientoService valorVariableEntrenamientoService;
    
    private String busquedaUsuario;
    
    private AsignacionEntrenamientoUsuario asignacionEntrenamientoUsuario;
    
    private List<Usuario> seleccionadosPacientes;
    
    private List<Usuario> pacientesFiltrados;
    
    private List<ValorVariableEntrenamiento> valoresVariablesEntrenamiento;

    public AsignacionEntrenamientoController() {
        seleccionadosPacientes = new ArrayList<>();
        pacientesFiltrados = new ArrayList<>();
        valoresVariablesEntrenamiento = new ArrayList<>();
        asignacionEntrenamientoUsuario = new AsignacionEntrenamientoUsuario();
    }
    
    public String onFlowProcess(FlowEvent event) {        
        if(asignacionEntrenamientoUsuario.getEntrenamiento() == null){
            return "tab1";
        }else{
            if(event.getOldStep().equals("tab1") && event.getNewStep().equals("tab2")){
                valoresVariablesEntrenamiento = new ArrayList<>();
                for(VariableEntrenamiento ve: variableEntrenamientoService.findByEntrenamientoIdAndTipoVariable(asignacionEntrenamientoUsuario.getEntrenamiento().getId(),"E")){
                    ValorVariableEntrenamiento aux = new ValorVariableEntrenamiento();
                    aux.setVariableEntrenamiento(ve);
                    valoresVariablesEntrenamiento.add(aux);
                }
            }
             return event.getNewStep();
        }                   
    }
    
    public void asignar(){
        if(!seleccionadosPacientes.isEmpty()){
            for(Usuario aux:seleccionadosPacientes){
                asignacionEntrenamientoUsuario.setUsuario(aux);
                asignacionEntrenamientoUsuario.setRealizado("N");
                asginacAsignacionEntrenamientoUsuarioService.guardarOActualizar(asignacionEntrenamientoUsuario);
                for(ValorVariableEntrenamiento vve:valoresVariablesEntrenamiento){
                    ValorVariableEntrenamientoDTO vveDTO = new ValorVariableEntrenamientoDTO();
                    vveDTO.setValor(vve.getValor());
                    vveDTO.setAsignacionEntrenamientoUsuario(asignacionEntrenamientoUsuario.getId());
                    vveDTO.setVariableEntrenamiento(vve.getVariableEntrenamiento().getId());
                    valorVariableEntrenamientoService.guardarOActualizar(vveDTO);
                }
                asignacionEntrenamientoUsuario.setId(0);
            }            
            seleccionadosPacientes = new ArrayList<>();
            addCallbackParam("success", true);
            addMessage("Se ha asignado correctamente", FacesMessage.SEVERITY_INFO);
        }else{
            addMessage("Debe escoger por lo menos un paciente", FacesMessage.SEVERITY_INFO);
            addCallbackParam("success", false);
        }
    }
    
    public void anadirPaciente(Usuario us){
        if(!seleccionadosPacientes.contains(us)){        
        seleccionadosPacientes.add(us);
        }
    }
    
    public void removerPaciente(Usuario us){
        seleccionadosPacientes.remove(us);
    }
    
    public void filtrarPacientesPorNombreODocumento(){
        pacientesFiltrados = usuarioService.findPaciantesByNombreOrDocumento(busquedaUsuario);
    }

    public String getBusquedaUsuario() {
        return busquedaUsuario;
    }

    public void setBusquedaUsuario(String busquedaUsuario) {
        this.busquedaUsuario = busquedaUsuario;
    }

    public List<Usuario> getSeleccionadosPacientes() {
        return seleccionadosPacientes;
    }

    public void setSeleccionadosPacientes(List<Usuario> seleccionadosPacientes) {
        this.seleccionadosPacientes = seleccionadosPacientes;
    }

    public List<Usuario> getPacientesFiltrados() {
        return pacientesFiltrados;
    }

    public void setPacientesFiltrados(List<Usuario> pacientesFiltrados) {
        this.pacientesFiltrados = pacientesFiltrados;
    }

    public List<ValorVariableEntrenamiento> getValoresVariablesEntrenamiento() {
        return valoresVariablesEntrenamiento;
    }

    public void setValoresVariablesEntrenamiento(List<ValorVariableEntrenamiento> valoresVariablesEntrenamiento) {
        this.valoresVariablesEntrenamiento = valoresVariablesEntrenamiento;
    }

    public AsignacionEntrenamientoUsuario getAsignacionEntrenamientoUsuario() {
        return asignacionEntrenamientoUsuario;
    }

    public void setAsignacionEntrenamientoUsuario(AsignacionEntrenamientoUsuario asignacionEntrenamientoUsuario) {
        this.asignacionEntrenamientoUsuario = asignacionEntrenamientoUsuario;
    }
    
    
    
}

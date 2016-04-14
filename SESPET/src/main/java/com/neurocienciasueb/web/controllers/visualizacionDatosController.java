/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.Entrenamiento;
import com.neurocienciasueb.dto.Grupo;
import com.neurocienciasueb.dto.Usuario;
import com.neurocienciasueb.dto.VariableEntrenamiento;
import com.neurocienciasueb.service.UsuarioGrupoService;
import com.neurocienciasueb.service.ValorVariableEntrenamientoService;
import com.neurocienciasueb.service.VariableEntrenamientoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class visualizacionDatosController extends BaseController implements Serializable{
    
    private static final long serialVersionUID = 212353843746535716L;
    
    @Autowired
    private ValorVariableEntrenamientoService valorVariableEntrenamientoService;
    
    @Autowired
    private VariableEntrenamientoService variableEntrenamientoService;
    
    @Autowired
    private UsuarioGrupoService usuarioGrupoService;
    
    private Entrenamiento entrenamientoSeleccionado;
    
    private Grupo grupoSeleccionado;
    
    private Set<Usuario> pacientesSeleccionados=new HashSet<>();
    
    private VariableEntrenamiento variableEntrenamientoEntrada;
    
    private VariableEntrenamiento variableEntrenamientoSalida;
    
    private List<String> valoresEjeX;
    
    private List<String> valoresEjeY;
    
    private float intVal; 
    
    private int intervalos=5;
    
    public boolean isEntrenamientoSeleccionado(){
        return entrenamientoSeleccionado!= null;
    }
    
    public boolean isPacientesSeleccionados(){
        return !pacientesSeleccionados.isEmpty();
    }
    
    public boolean isVariableEntreadaSeleccionada(){
        return variableEntrenamientoEntrada!=null;
    }
    
    public boolean isVariableSalidaSeleccionada(){
        return variableEntrenamientoSalida!=null;
    }
    
    public void asignarPacientesDeGrupo(){
        pacientesSeleccionados.addAll(usuarioGrupoService.listarPacientesPorGrupo(grupoSeleccionado.getId()));
    }
    
    public List<VariableEntrenamiento> variablesEntrenamientoEntrada(){
        if(entrenamientoSeleccionado!=null){
            return variableEntrenamientoService.findByEntrenamientoIdAndTipoVariable(entrenamientoSeleccionado.getId(), "E");
        }else{
            return new ArrayList();
        }        
    }
    
    public List<VariableEntrenamiento> variablesEntrenamientoSalida(){
        if(entrenamientoSeleccionado!=null){
            return variableEntrenamientoService.findByEntrenamientoIdAndTipoVariable(entrenamientoSeleccionado.getId(), "S");
        }else{
            return new ArrayList();
        }        
    }
    
    public void filtrar(){
        cargarValoresEjeX();
        cargarValoresEjeY();
    }
    
    private void cargarValoresEjeX(){
        valoresEjeX = new ArrayList<>();
        valoresEjeX.add("");
        valoresEjeX.addAll(valorVariableEntrenamientoService.valoresParaVariableEntrenamiento(variableEntrenamientoEntrada.getId()));
    }
    
    private void cargarValoresEjeY(){        
        if(variableEntrenamientoSalida.getTipoDato().contains("cont")){
        valoresEjeY = valorVariableEntrenamientoService.valoresVariableContinua(variableEntrenamientoSalida.getId(), intervalos);
        if(valoresEjeY==null){
            addMessage("Ningún paciente ha presentado el entranamiento seleccionado", FacesMessage.SEVERITY_WARN);
        }else{
           intVal = Float.valueOf(valoresEjeY.get(0));
           valoresEjeY.remove(0);  
        }
        
    }else{
          valoresEjeY = new ArrayList<>(valorVariableEntrenamientoService.valoresParaVariableEntrenamiento(variableEntrenamientoSalida.getId()));  
        }       
    }
    
    public String dato(String x, String y){
        if(x.isEmpty()){
            return y;
        }else {
            return ""+valor(x,y);
        }       
    }
    
    private int valor(String valorEntrada, String valorSalida){
        if(variableEntrenamientoSalida.getTipoDato().contains("cont")){
            int acumulador=0; 
            for(Usuario paciente: pacientesSeleccionados){                
                acumulador+= valorVariableEntrenamientoService.resultadosContinuosPorPacienteVariable(
                        paciente.getUsername(), variableEntrenamientoEntrada.getId(), valorEntrada, variableEntrenamientoSalida.getId(),  Float.valueOf(valorSalida),Float.valueOf(valorSalida)-intVal);
            }
            return acumulador; 
        }else{
            int acumulador=0;
            for(Usuario paciente: pacientesSeleccionados){
                acumulador+= valorVariableEntrenamientoService.resultadosDiscretosPorPacienteVariable(
                        paciente.getUsername(), variableEntrenamientoEntrada.getId(), valorEntrada, variableEntrenamientoSalida.getId(), valorSalida);
            }
            return acumulador;
        }
    }

    public Entrenamiento getEntrenamientoSeleccionado() {
        return entrenamientoSeleccionado;
    }

    public void setEntrenamientoSeleccionado(Entrenamiento entrenamientoSeleccionado) {
        variableEntrenamientoEntrada=null;
        variableEntrenamientoSalida=null;
        this.entrenamientoSeleccionado = entrenamientoSeleccionado;
    }

    public Set<Usuario> getPacientesSeleccionados() {
        return pacientesSeleccionados;
    }

    public void setPacientesSeleccionados(Set<Usuario> pacientesSeleccionados) {
        this.pacientesSeleccionados = pacientesSeleccionados;
    }

    public VariableEntrenamiento getVariableEntrenamientoEntrada() {
        return variableEntrenamientoEntrada;
    }

    public void setVariableEntrenamientoEntrada(VariableEntrenamiento variableEntrenamientoEntrada) {
        this.variableEntrenamientoEntrada = variableEntrenamientoEntrada;
    }

    public VariableEntrenamiento getVariableEntrenamientoSalida() {
        return variableEntrenamientoSalida;
    }

    public void setVariableEntrenamientoSalida(VariableEntrenamiento variableEntrenamientoSalida) {
        this.variableEntrenamientoSalida = variableEntrenamientoSalida;
    }

    public List<String> getValoresEjeX() {
        return valoresEjeX;
    }

    public void setValoresEjeX(List<String> valoresEjeX) {
        this.valoresEjeX = valoresEjeX;
    }

    public List<String> getValoresEjeY() {
        return valoresEjeY;
    }

    public void setValoresEjeY(List<String> valoresEjeY) {
        this.valoresEjeY = valoresEjeY;
    }

    public float getIntVal() {
        return intVal;
    }

    public void setIntVal(float intVal) {
        this.intVal = intVal;
    }

    public int getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(int intervalos) {
        this.intervalos = intervalos;
    }

    public Grupo getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }
    
    
    
}

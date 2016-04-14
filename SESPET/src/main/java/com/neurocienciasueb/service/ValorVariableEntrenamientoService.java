/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dao.ValorVariableEntrenamientoDao;
import com.neurocienciasueb.dao.VariableEntrenamientoDao;
import com.neurocienciasueb.dto.ValorVariableEntrenamiento;
import com.neurocienciasueb.dto.ValorVariableEntrenamientoDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isaac
 */
@Service
public class ValorVariableEntrenamientoService implements Serializable, ServicioGenerico<ValorVariableEntrenamientoDTO>{
    
    private static final long serialVersionUID = 323538641584547306L;
    
    @Autowired
    private ValorVariableEntrenamientoDao service;
    
    @Autowired
    private VariableEntrenamientoDao variableEntrenamientoDao;
    
    public List<ValorVariableEntrenamiento> findByAsignacionEntrenamientoUsuario(int idAsignacionEntrenamiento){
        List<ValorVariableEntrenamiento> lista = new ArrayList<>();
        for(ValorVariableEntrenamientoDTO aux: service.findByAsignacionEntrenamientoUsuario(idAsignacionEntrenamiento)){
           ValorVariableEntrenamiento temp = new ValorVariableEntrenamiento();
           temp.setVariableEntrenamiento(variableEntrenamientoDao.findFirstById(aux.getVariableEntrenamiento()));
           temp.setValor(aux.getValor());
           lista.add(temp);
        }
        return lista;
    }

    @Override
    public List<ValorVariableEntrenamientoDTO> listarTodo() {
        return service.findAll();
    }

    @Override
    public ValorVariableEntrenamientoDTO guardarOActualizar(ValorVariableEntrenamientoDTO entidad) {
        return service.saveAndFlush(entidad);
    }

    @Override
    public void eliminar(ValorVariableEntrenamientoDTO entidad) throws MySQLIntegrityConstraintViolationException {
        service.delete(entidad);
    }
    
    public List<String[]> arregloParametros(int idAsignacion, String tipo){
        return service.arregloParametros(idAsignacion, tipo);
    }
    
    public List<String> valoresParaVariableEntrenamiento(int idVariableEntrenamiento){
        return service.valoresParaVariableEntrada(idVariableEntrenamiento);
    }
    
    public List<String> valoresVariableContinua(int idVariableEntrenamiento, int intervalos){
        Float max = service.maxParaVariableSalida(idVariableEntrenamiento),
                min = service.minParaVariableSalida(idVariableEntrenamiento),
                intVal;
        if(max ==null || min ==null) {
            return null;
        }else{
            min-=1;
            intVal = (Math.abs(min-max))/intervalos;
        }
        List<String> rel = new ArrayList();
        rel.add(String.valueOf(intVal));
        for(int i=0; i< intervalos;i++){
            min+=intVal;
            rel.add(String.valueOf(min));            
        }
        return rel;
    }
    
    public int resultadosContinuosPorPacienteVariable(String usuario, int idVariableEntrenamientoEntrada, String valorEntrada,int idVariableEntrenamientoSalida, float mayor, float menor){
        return service.resultadosIntervaloPorPacienteVariable(usuario, idVariableEntrenamientoEntrada, valorEntrada, idVariableEntrenamientoSalida, mayor, menor);
    }
    
    public int resultadosDiscretosPorPacienteVariable(String usuario, int idVariableEntrenamientoEntrada, String valorEntrada,int idVariableEntrenamientoSalida, String valorSalida ){
        return service.resultadosDiscretosPorPacienteVariable(usuario, idVariableEntrenamientoEntrada, valorEntrada, idVariableEntrenamientoSalida, valorSalida);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dao.VariableEntrenamientoDao;
import com.neurocienciasueb.dto.VariableEntrenamiento;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isaac
 */
@Service
public class VariableEntrenamientoService implements Serializable, ServicioGenerico<VariableEntrenamiento>{
    
    private static final long serialVersionUID = 323538641592747306L;
    
    @Autowired
    private VariableEntrenamientoDao service;

    @Override
    public List<VariableEntrenamiento> listarTodo() {
        return service.findAll();
    }

    @Override
    public VariableEntrenamiento guardarOActualizar(VariableEntrenamiento entidad) {
        return service.saveAndFlush(entidad);
    }

    @Override
    public void eliminar(VariableEntrenamiento entidad) throws MySQLIntegrityConstraintViolationException{
        service.delete(entidad);
    }
    
    public List<VariableEntrenamiento> listarPorEntrenamientoId(int idEntrenamiento){
        return service.findByEntrenamientoId(idEntrenamiento);
    }
    
    public List<VariableEntrenamiento> findByEntrenamientoIdAndTipoVariable(int indEntrenamiento, String tipoVariable){
        return service.findByEntrenamientoIdAndTipoVariable(indEntrenamiento, tipoVariable);
    }
    
}

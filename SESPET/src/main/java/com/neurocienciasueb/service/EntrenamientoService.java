/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dao.EntrenamientoDao;
import com.neurocienciasueb.dto.Entrenamiento;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isaac
 */

@Service
public class EntrenamientoService implements Serializable, ServicioGenerico<Entrenamiento>{
    
    private static final long serialVersionUID = 103531231596487306L;
    
    @Autowired
    private EntrenamientoDao service;
    
    @Override
    public List<Entrenamiento> listarTodo(){
        return service.findAll();
    }
    
    public List<Entrenamiento> listarActivos(){
        return service.findByEstado("A");
    }
    
    @Override
    public Entrenamiento guardarOActualizar(Entrenamiento entrenamiento){
        return service.saveAndFlush(entrenamiento);
    }
    
    @Override
    public void eliminar(Entrenamiento entrenamiento) throws MySQLIntegrityConstraintViolationException{
        service.delete(entrenamiento);
    }
    
}

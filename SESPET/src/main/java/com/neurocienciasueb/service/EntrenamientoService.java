/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

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
public class EntrenamientoService implements Serializable{
    
    private static final long serialVersionUID = 103531231596487306L;
    
    @Autowired
    private EntrenamientoDao service;
    
    public List<Entrenamiento> listarTodo(){
        return service.findAll();
    }
    
    public Entrenamiento guardarOActualizar(Entrenamiento entrenamiento){
        return service.saveAndFlush(entrenamiento);
    }
    
    public void eliminar(Entrenamiento entrenamiento){
        service.delete(entrenamiento);
    }
    
}

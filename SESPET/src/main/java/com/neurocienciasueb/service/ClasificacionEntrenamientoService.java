/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.neurocienciasueb.dao.ClasificacionEntrenamientoDao;
import com.neurocienciasueb.dto.ClasificacionEntrenamiento;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isaac
 */

@Service
public class ClasificacionEntrenamientoService implements Serializable{
    
    private static final long serialVersionUID = 9023531231595557306L;
    
    @Autowired
    private ClasificacionEntrenamientoDao service;
    
    public List<ClasificacionEntrenamiento> listarTodo(){
        return service.findAll();
    }
    
    public ClasificacionEntrenamiento guardarOActualizar(ClasificacionEntrenamiento clasificacionEntrenamiento){
        return service.saveAndFlush(clasificacionEntrenamiento);
    }
    
    public void eliminar(ClasificacionEntrenamiento clasificacionEntrenamiento){
        service.delete(clasificacionEntrenamiento);
    }
    
}

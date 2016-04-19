/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.neurocienciasueb.dao.GrupoDao;
import com.neurocienciasueb.dto.Grupo;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isaac
 */
@Service
public class GrupoService implements Serializable{
    
    private static final long serialVersionUID = 2123538431595357306L;
    
    @Autowired
    private GrupoDao service;
    
    public List<Grupo> listarTodo(){
        return service.findAll();
    }
    
    public Grupo guardarOActualizar(Grupo grupo){
        return service.saveAndFlush(grupo);
    }
    
    public void eliminar(Grupo grupo){
        service.delete(grupo);
    }
    
    

    

    
    
}

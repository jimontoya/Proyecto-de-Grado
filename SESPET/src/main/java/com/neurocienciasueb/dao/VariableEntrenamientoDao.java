/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dao;

import com.neurocienciasueb.dto.VariableEntrenamiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Isaac
 */

@Repository
public interface VariableEntrenamientoDao extends JpaRepository<VariableEntrenamiento, Integer>{
    
    public List<VariableEntrenamiento> findByEntrenamientoId(int idEntrenamiento);
    
    public List<VariableEntrenamiento> findByEntrenamientoIdAndTipoVariable(int indEntrenamiento, String tipoVariable);
    
    public VariableEntrenamiento findFirstById(int id);
}

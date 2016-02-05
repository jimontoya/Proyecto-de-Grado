/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dao;

import com.neurocienciasueb.dto.ValorVariableEntrenamientoDTO;
import com.neurocienciasueb.dto.ValorVariableEntrenamientoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Isaac
 */
@Repository
public interface ValorVariableEntrenamientoDao extends JpaRepository<ValorVariableEntrenamientoDTO,ValorVariableEntrenamientoId>{
    
    @Query(value = " SELECT ve.tipo_dato, vve.valor "
            + "FROM valor_variable_entrenamiento vve left join variable_entrenamiento ve on(ve.id = vve.id_parametro) " 
            + "where vve.id_asignacion = ?1 and ve.tipo_variable = ?2", nativeQuery = true)
    List<String[]> arregloParametros(int idAsignacion, String tipo);
    
}

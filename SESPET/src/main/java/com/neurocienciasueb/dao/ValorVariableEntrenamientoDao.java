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
    
    @Query(value = " SELECT ve.referencia, vve.valor "
            + "FROM valor_variable_entrenamiento vve left join variable_entrenamiento ve on(ve.id = vve.id_parametro) " 
            + "where vve.id_asignacion = ?1 and ve.tipo_variable = ?2", nativeQuery = true)
    List<String[]> arregloParametros(int idAsignacion, String tipo);
    
    List<ValorVariableEntrenamientoDTO> findByAsignacionEntrenamientoUsuario(int idAsignacionEntrenamiento);
    
    //Valores distintos dada una variable de entrada o salida discreta
    @Query(value ="select distinct vve.valor " +
            "from variable_entrenamiento ve left join valor_variable_entrenamiento vve on(vve.id_parametro = ve.id) " +
            "where ve.id = ?1"
            , nativeQuery = true)
    List<String> valoresParaVariableEntrada(int idVariableEntrenamiento);
    
    //Número mayor de resultado dada una variable de salida 
    @Query(value ="select max(cast(vve.valor as decimal(11,6))) " +
                    "from variable_entrenamiento ve left join valor_variable_entrenamiento vve on(vve.id_parametro = ve.id) " +
                    "where ve.id = ?1"
            , nativeQuery = true)
    Float maxParaVariableSalida( int idVariableEntrenamientoSalida);
    
    //Número menor de resultado dada una variable de salida 
    @Query(value ="select min(cast(vve.valor as decimal(11,6))) " +
                    "from variable_entrenamiento ve left join valor_variable_entrenamiento vve on(vve.id_parametro = ve.id) " +
                    "where ve.id = ?1"
            , nativeQuery = true)
    Float minParaVariableSalida(int idVariableEntrenamientoSalida);
    
    /*Número de resultados que están dentro de los intervalos dados, para el paciente 
    y la variable de salida dada numerica continua
    */
    @Query(value ="select count(*) from ( " +
                    "select distinct aue.id as idAue " +
                    "from asignacion_entrenamiento_usuario aue left join entrenamiento e on(aue.entrenamiento = e.id) " +
                    "left join variable_entrenamiento ve on(ve.id_entrenamiento = e.id) " +
                    "left join valor_variable_entrenamiento vve on(vve.id_parametro = ve.id and vve.id_asignacion = aue.id) " +
                    "where aue.usuario= ?1 and ve.id = ?2 and vve.valor = ?3 ) con  " +
                    "left join asignacion_entrenamiento_usuario aue on(con.idAue = aue.id) " +
                    "left join entrenamiento e on(aue.entrenamiento = e.id) " +
                    "left join variable_entrenamiento ve on(ve.id_entrenamiento = e.id) " +
                    "left join valor_variable_entrenamiento vve on(vve.id_parametro = ve.id and vve.id_asignacion = aue.id) " +
                    "where  ve.id = ?4 and cast(vve.valor as decimal(11,6)) <= ?5 " +
                    "and cast(vve.valor as decimal(11,6)) > ?6 "
            , nativeQuery = true)
    Integer resultadosIntervaloPorPacienteVariable(String usuario, int idVariableEntrenamientoEntrada, String valorEntrada, int idVariableEntrenamientoSalida, float mayor, float menor);
    
    /*Número de resultados que son iguales al valor dado, para el paciente 
    y la variable de salida dada numerica discreta
    */
    @Query(value ="select count(*) from ( " +
                    "select distinct aue.id as idAue " +
                    "from asignacion_entrenamiento_usuario aue left join entrenamiento e on(aue.entrenamiento = e.id) " +
                    "left join variable_entrenamiento ve on(ve.id_entrenamiento = e.id) " +
                    "left join valor_variable_entrenamiento vve on(vve.id_parametro = ve.id and vve.id_asignacion = aue.id) " +
                    "where aue.usuario= ?1 and ve.id = ?2 and vve.valor = ?3 ) con " +
                    "left join asignacion_entrenamiento_usuario aue on(con.idAue = aue.id) " +
                    "left join entrenamiento e on(aue.entrenamiento = e.id) " +
                    "left join variable_entrenamiento ve on(ve.id_entrenamiento = e.id) " +
                    "left join valor_variable_entrenamiento vve on(vve.id_parametro = ve.id and vve.id_asignacion = aue.id) " +
                    "where  ve.id = ?4 and vve.valor = ?5 "
            , nativeQuery = true)
    Integer resultadosDiscretosPorPacienteVariable(String usuario, int idVariableEntrenamientoEntrada, String valorEntrada, int idVariableEntrenamientoSalida, String valorSalida);
    
}

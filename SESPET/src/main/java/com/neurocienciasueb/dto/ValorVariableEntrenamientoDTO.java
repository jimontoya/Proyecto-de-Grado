/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Isaac
 */
@Entity
@Table(name = "valor_variable_entrenamiento")
@IdClass(ValorVariableEntrenamientoId.class)
public class ValorVariableEntrenamientoDTO implements Serializable{
    
    private static final long serialVersionUID = 2119421275355606L;
    
    @Getter
    @Setter
    @Id
    @Column(name = "id_parametro", nullable = false)
    private int variableEntrenamiento;
    
    @Getter
    @Setter
    @Id
    @Column(name = "id_asignacion", nullable = false)
    private int asignacionEntrenamientoUsuario;
    
    @Getter
    @Setter
    @Column(name = "valor", nullable = false, length = 100)
    private String valor;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Isaac
 */
@Entity
@Table(name = "valor_variable_entrenamiento")
public class ValorVariableEntrenamiento implements Serializable{
    
    private static final long serialVersionUID = 211942127465355606L;
    
    @Getter
    @Setter
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parametro", nullable = false)
    private VariableEntrenamiento variableEntrenamiento;
    
    @Getter
    @Setter
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_asignacion", nullable = false)
    private AsignacionEntrenamientoUsuario asignacionEntrenamientoUsuario;
    
    @Getter
    @Setter
    @Column(name = "valor", nullable = false, length = 100)
    private String valor;
    
    
    
}

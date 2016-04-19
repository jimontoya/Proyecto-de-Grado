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
@Table(name = "asignacion_entrenamiento_usuario")
public class AsignacionEntrenamientoUsuario extends RegularIdEntity implements Serializable{
    
    private static final long serialVersionUID = 121955127465355606L;
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrenamiento", nullable = false)
    private Entrenamiento entrenamiento;
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario", nullable = true)
    private Usuario usuario;
    
    @Getter
    @Setter
    @Column(name = "orden", nullable = true)
    private int orden;
    
    @Getter
    @Setter
    @Column(name = "realizado", nullable = false, length = 1)
    private String realizado;
    
}

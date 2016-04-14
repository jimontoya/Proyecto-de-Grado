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
@Table(name = "entrenamiento")
public class Entrenamiento extends RegularIdEntity implements Serializable{
    
    private static final long serialVersionUID = 1593488617959077977L;
    
    @Getter
    @Setter
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Getter
    @Setter
    @Column(name = "instrucciones", nullable = false)
    private String instrucciones;
    
    @Getter
    @Setter
    @Column(name = "codigo", nullable = false)
    private String codigo;
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clasificacion", nullable = false)
    private ClasificacionEntrenamiento clasificacionEntrenamiento;
    
    @Getter
    @Setter
    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
    
}

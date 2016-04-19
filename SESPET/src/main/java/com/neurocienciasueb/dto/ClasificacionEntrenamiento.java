/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Isaac
 */

@Entity
@Table(name = "clasificacion_entrenamiento")
public class ClasificacionEntrenamiento extends RegularIdEntity implements Serializable{
    
    private static final long serialVersionUID = 3873488611159077977L;
    
    @Getter
    @Setter
    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;
    
    @Getter
    @Setter
    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;
    
}

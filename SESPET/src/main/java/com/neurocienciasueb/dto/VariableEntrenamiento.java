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
@Table(name = "variable_entrenamiento")
public class VariableEntrenamiento extends RegularIdEntity implements Serializable{
    
    
    
    private static final long serialVersionUID = 121942127465355606L;
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_entrenamiento", nullable = false)
    private Entrenamiento entrenamiento;
    
    @Getter
    @Setter
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Getter
    @Setter
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @Getter
    @Setter
    @Column(name = "referencia", nullable = false, length = 45)
    private String referencia;
    
    @Getter
    @Setter
    @Column(name = "tipo_variable", nullable = false, length = 1)
    private String tipoVariable;
    
    @Getter
    @Setter
    @Column(name = "tipo_dato", nullable = false, length = 10)
    private String tipoDato;
    
    @Getter
    @Setter
    @Column(name = "valor_defecto", nullable = true, length = 50)
    private String ValorDefecto;
    
}

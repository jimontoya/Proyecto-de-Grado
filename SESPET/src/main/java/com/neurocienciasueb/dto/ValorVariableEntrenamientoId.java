/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Isaac
 */
public class ValorVariableEntrenamientoId implements Serializable{
    
    private static final long serialVersionUID = 21194212365355606L;
    
    @Getter
    @Setter
    private int variableEntrenamiento;
    
    @Getter
    @Setter
    private int asignacionEntrenamientoUsuario;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.variableEntrenamiento;
        hash = 67 * hash + this.asignacionEntrenamientoUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ValorVariableEntrenamientoId other = (ValorVariableEntrenamientoId) obj;
        if (this.variableEntrenamiento != other.variableEntrenamiento) {
            return false;
        }
        if (this.asignacionEntrenamientoUsuario != other.asignacionEntrenamientoUsuario) {
            return false;
        }
        return true;
    }

    
    
    
    
}

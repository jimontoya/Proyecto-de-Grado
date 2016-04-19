/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dto;

import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Isaac
 */
public class UsuarioGrupoId implements Serializable{
    
    private static final long serialVersionUID = 1294212365355606L;
    
    @Getter
    @Setter
    private String usuario;
    
    @Getter
    @Setter
    private int grupo;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.usuario);
        hash = 47 * hash + this.grupo;
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
        final UsuarioGrupoId other = (UsuarioGrupoId) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (this.grupo != other.grupo) {
            return false;
        }
        return true;
    }

    
    
    
    
}

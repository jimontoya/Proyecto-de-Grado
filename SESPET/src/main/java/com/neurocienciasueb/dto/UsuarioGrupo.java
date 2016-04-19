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
@Table(name = "usuario_grupo")
@IdClass(UsuarioGrupoId.class)
public class UsuarioGrupo implements Serializable{
    
    private static final long serialVersionUID = 389421275355606L;
    
    @Getter
    @Setter
    @Id
    @Column(name = "idusuario", nullable = false)
    private String usuario;
    
    @Getter
    @Setter
    @Id
    @Column(name = "idgrupo", nullable = false)
    private int grupo;
    
}

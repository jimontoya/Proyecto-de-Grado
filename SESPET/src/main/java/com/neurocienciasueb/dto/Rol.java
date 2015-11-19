/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Isaac
 */

@Entity
@Table(name = "rol")
public class Rol extends RegularIdEntity implements Serializable,GrantedAuthority{
    
    private static final long serialVersionUID = 2123531546465357306L;
    
    @Getter
    @Setter
    @Column(name = "authority", nullable = false, length = 45)
    private String authority;
    
    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rol_funcionalidad", joinColumns = { @JoinColumn(name = "idrol", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "idfuncionalidad", nullable = false, updatable = false) })
    private List<Funcionalidad> funcionalidads = new ArrayList<>(0);
    
}

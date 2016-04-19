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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Isaac
 */

@Entity
@Table(name = "funcionalidad")
public class Funcionalidad extends RegularIdEntity implements Serializable{
    
     private static final long serialVersionUID = 9080088617959077977L;
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "padre", nullable = true)
    private Funcionalidad funcionalidad;
    
    @Getter
    @Setter
    @Column(name = "nombre_funcionalidad", nullable = false, length = 60)
    private String nombreFuncionalidad;
    
    @Getter
    @Setter
    @Column(name = "interfaz", nullable = false, length = 70)
    private String interfaz;
    
    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rol_funcionalidad", joinColumns = { @JoinColumn(name = "idfuncionalidad", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "idrol", nullable = false, updatable = false) })
    private List<Rol> rols = new ArrayList<>(0);
    
    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "funcionalidad")
    private List<Funcionalidad> funcionalidads = new ArrayList<Funcionalidad>(0);
    
}

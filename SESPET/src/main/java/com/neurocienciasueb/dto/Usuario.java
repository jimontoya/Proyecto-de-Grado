/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Isaac
 */
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails, Serializable{
    
    private static final long serialVersionUID = 2123109737465357306L;
    
    
    @Setter
    @Id
    @Column(name = "username",  nullable = false, length = 100)
    private String userName;
    
    @Getter
    @Setter
    @Column(name = "documento_identidad",  nullable = false, length = 100)
    private String documentoIdentidad;
    
    @Getter
    @Setter
    @Column(name = "nombre_completo",  nullable = false, length = 300)
    private String nombreCompleto;
    
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", nullable = false, length = 23)
    private Date fechaNacimiento;
    
    @Getter
    @Setter
    @Column(name = "correo_electronico",  nullable = true, length = 100)
    private String correoElectronico;
    
    @Getter
    @Setter
    @Column(name="telefono",  nullable=true, length = 30)
    private String telefono;
    
    @Getter
    @Setter
    @Column(name = "password",  nullable = false, length = 256)
    private String password;
    
    @Getter
    @Setter
    @Column(name = "estado",  nullable = false, length = 1)
    private String estado;    
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol", nullable = false)
    private Rol rol;

    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Rol> roles = new ArrayList<>(1);
        roles.add(rol);
        return roles;
    }

    public Usuario(Usuario us) {
        this.correoElectronico = us.correoElectronico;
        this.documentoIdentidad = us.documentoIdentidad;
        this.estado = us.estado;
        this.fechaNacimiento = us.fechaNacimiento;
        this.nombreCompleto = us.nombreCompleto;
        this.password = us.password;
        this.rol = us.rol;
        this.telefono = us.telefono;
        this.userName = us.userName;
    }

    public String getUserName(){
        return this.userName;
    }
    
    @Override
    public String getUsername() {
        return this.userName;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return this.estado.equals("A");
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.estado.equals("A");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.estado.equals("A");
    }

    @Override
    public boolean isEnabled() {
        return this.estado.equals("A");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.userName);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.dao;

import com.neurocienciasueb.dto.UsuarioGrupo;
import com.neurocienciasueb.dto.UsuarioGrupoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Isaac
 */

@Repository
public interface UsuarioGrupoDao extends JpaRepository<UsuarioGrupo, UsuarioGrupoId> {
    
    public List<UsuarioGrupo> findByGrupo(int idGrupo);
}

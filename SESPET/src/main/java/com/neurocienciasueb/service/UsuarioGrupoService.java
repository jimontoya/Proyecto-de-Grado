/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.neurocienciasueb.dao.UsuarioDao;
import com.neurocienciasueb.dao.UsuarioGrupoDao;
import com.neurocienciasueb.dto.Usuario;
import com.neurocienciasueb.dto.UsuarioGrupo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isaac
 */

@Service
public class UsuarioGrupoService implements Serializable{
    
    private static final long serialVersionUID = 32353815984547306L;
    
    @Autowired
    private UsuarioGrupoDao service;
    
    @Autowired
    private UsuarioDao usuarioService;
    
    public UsuarioGrupo guardar(UsuarioGrupo usuarioGrupo){
        return service.saveAndFlush(usuarioGrupo);
    }
    
    public void eliminar(UsuarioGrupo usuarioGrupo){
        service.delete(usuarioGrupo);
    }
    
    public List<Usuario> listarPacientesPorGrupo(int idGrupo){
        List<Usuario> usuarios = new ArrayList<>();
        service.findByGrupo(idGrupo).stream().forEach((usuarioGrupo) -> {
            usuarios.add(usuarioService.findFirstByUserName(usuarioGrupo.getUsuario()));
        });
        return usuarios;
    }
    
    
    
}

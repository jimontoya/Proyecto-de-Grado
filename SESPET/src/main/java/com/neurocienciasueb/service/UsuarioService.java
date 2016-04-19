/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dao.UsuarioDao;
import com.neurocienciasueb.dto.Usuario;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isaac
 */
@Service
public class UsuarioService implements Serializable, ServicioGenerico<Usuario>{
    
    private static final long serialVersionUID = 2123524931595357306L;
    
    @Autowired
    private UsuarioDao service;

    @Override
    public List<Usuario> listarTodo() {
        return service.findAll();
    }

    @Override
    public Usuario guardarOActualizar(Usuario entidad) {
        return service.saveAndFlush(entidad);
    }

    @Override
    public void eliminar(Usuario entidad) throws MySQLIntegrityConstraintViolationException {
        service.delete(entidad);
    }
    
    public List<Usuario> findPaciantesByNombreOrDocumento(String busqueda){
        return service.findPaciantesByNombreOrDocumento(busqueda);
    }
    
    public boolean existe (String username){
        return service.findFirstByUserName(username) != null;
    }
    
    public List<Usuario> listarPorRolId(int idRol){
        return service.findByRolId(idRol);
    }
}

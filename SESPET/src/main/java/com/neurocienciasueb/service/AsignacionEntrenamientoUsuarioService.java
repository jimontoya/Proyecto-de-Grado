/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.neurocienciasueb.dao.AsignacionEntrenamientoUsuarioDao;
import com.neurocienciasueb.dto.AsignacionEntrenamientoUsuario;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Isaac
 */
@Service
public class AsignacionEntrenamientoUsuarioService implements Serializable, ServicioGenerico<AsignacionEntrenamientoUsuario>{
    
    private static final long serialVersionUID = 103531231196487306L;
    
    @Autowired
    private AsignacionEntrenamientoUsuarioDao service;

    @Override
    public List<AsignacionEntrenamientoUsuario> listarTodo() {
        return service.findAll();
    }

    @Override
    public AsignacionEntrenamientoUsuario guardarOActualizar(AsignacionEntrenamientoUsuario entidad) {
        return service.saveAndFlush(entidad);
    }

    @Override
    public void eliminar(AsignacionEntrenamientoUsuario entidad) throws MySQLIntegrityConstraintViolationException {
        service.delete(entidad);
    }
    
    public List<AsignacionEntrenamientoUsuario> findByUsuarioIdAndRealizadoOrderByOrdenAsc(String username, String realizado){
        if(realizado.equals("S") || realizado.equals("N")){
            return service.findByUsuarioUserNameAndRealizadoOrderByOrdenAsc(username, realizado);
        } else {
            List lista = service.findByUsuarioUserNameAndRealizadoOrderByOrdenAsc(username, "S");
            lista.addAll(service.findByUsuarioUserNameAndRealizadoOrderByOrdenAsc(username, "N"));
            return lista;
        }
    }
    
    public AsignacionEntrenamientoUsuario findFirstByUsuarioIdAndRealizadoOrderByOrdenAsc(String username, String realizado){
        return service.findFirstByUsuarioUserNameAndRealizadoOrderByOrdenAsc(username, realizado);
    }
    
}

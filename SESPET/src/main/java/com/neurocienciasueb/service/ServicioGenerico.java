/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.service;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.util.List;

/**
 *
 * @author Isaac
 * @param <T>
 */
public interface ServicioGenerico<T extends Object> {
    
    public List<T> listarTodo();
    
    public T guardarOActualizar(T entidad);
    
    public void eliminar(T entidad) throws MySQLIntegrityConstraintViolationException;
    
}

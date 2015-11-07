/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Isaac
 */
public class BaseController implements Serializable{
    
    private static final long serialVersionUID = 113538437465357306L;
    
    public Usuario getUsuario(){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(obj instanceof Usuario)
        {
          return (Usuario) obj;
        }
        else
        {
          return null;  
        }
    }
    
    public void addMessage(String message, FacesMessage.Severity severity)
    {
        if(severity != null)
        {
            FacesContext.getCurrentInstance().
                addMessage(null,new FacesMessage(severity, message,""));
        }
        else
        {
           FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(message,""));
        }
        
                
    }
    
    public void addCallbackParam(String nombre, Object valor){
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam(nombre, valor);
    }
    
}

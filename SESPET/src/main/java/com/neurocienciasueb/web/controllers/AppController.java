/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Isaac
 */
@Controller
@Scope("session")
public class AppController extends BaseController implements Serializable
{
    private static final long serialVersionUID = -99662387246183477L;

    
    private String username;
    
    private String password;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    
    
    public String getAppUploadPath()
    {
        return "/uploads";
    }
    
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Usuario getAuthenticatedUser()
    {
        Object us = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(us instanceof Usuario)
            return (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        else return null;
    }
    
    public void login() throws ServletException, IOException
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        
        try
        {
            Authentication authentication = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
            Authentication result = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(result);
            
            if (result.isAuthenticated()) {
                HttpServletRequest request = (HttpServletRequest)
                        FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpServletResponse response = (HttpServletResponse)
                        FacesContext.getCurrentInstance().getExternalContext().getResponse();
                RequestCache requestCache = new HttpSessionRequestCache();
                SavedRequest savedRequest = requestCache.getRequest(request, response);
                
                String url = "";
                
                if(savedRequest != null)
                {
                   url = savedRequest.getRedirectUrl();                                
                }
                else
                {
                    url = context.getInitParameter("app.base.url") + context.getInitParameter("app.base.home");   
                }  
                
                FacesContext
                        .getCurrentInstance()
                            .getExternalContext()
                                .redirect(url); 
            }            
        }
        catch(AuthenticationException ae)
        {
            
            addMessage("Usuario ó Password Incorrectos! Intente de Nuevo!", FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void logout() throws IOException
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        
        HttpServletRequest request = (HttpServletRequest)
                        FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpServletResponse response = (HttpServletResponse)
                        FacesContext.getCurrentInstance().getExternalContext().getResponse();
                
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);            
        }
        
        String url = context.getInitParameter("app.base.url") + context.getInitParameter("app.base.login"); 
        FacesContext
                .getCurrentInstance()
                    .getExternalContext()
                        .redirect(url); 
        
    }
    
    public String getVersion()
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        
        String version = context.getInitParameter("app.version");
        
        return version;
    }
}

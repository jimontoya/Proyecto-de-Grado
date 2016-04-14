/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.Rol;
import com.neurocienciasueb.dto.Usuario;
import com.neurocienciasueb.service.UsuarioService;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Isaac
 */
@Controller
@Scope("session")
public class UsuarioController extends BaseController implements Serializable{
    
    private static final long serialVersionUID = 43538437465357306L;
    
    @Autowired
    private UsuarioService usuarioService;
    
    private Usuario usuarioSeleccionado;
    
    private String pass;

    public UsuarioController() {
        nuevoUsuario(3);
    }

    
    
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        pass="";
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    public void guardarOEditar(){
        try{            
                if(getUsuario().getRol().getId()!=1 && usuarioSeleccionado.getRol().getId()==1){
                   addMessage("Solo un usuario administrador puede crear administradores", FacesMessage.SEVERITY_WARN); 
                }else{
                    if(!pass.isEmpty()){
                        usuarioSeleccionado.setPassword(sha1(pass));
                        pass="";
                    }
                   usuarioService.guardarOActualizar(usuarioSeleccionado);
                   addMessage("Se ha guardado correctamente", FacesMessage.SEVERITY_INFO); 
                   addCallbackParam("success", true);
                }           
        } catch(Exception ex){
            addMessage(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    
    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
    
    public void deshabilitar(Usuario user){
        if(user.getEstado().equals("A")){
            user.setEstado("I");
        } else {
            user.setEstado("A");
        }
        
        usuarioService.guardarOActualizar(user);
    }
    
    public void nuevoUsuario(int idRol){
        pass="";
        usuarioSeleccionado = new Usuario();
        usuarioSeleccionado.setEstado("A");
        usuarioSeleccionado.setRol(new Rol());
        usuarioSeleccionado.getRol().setId(idRol);
    }
    
    public List<Usuario> listarTodo(){
        if(this.getUsuario().getRol().getId()==1){
            return usuarioService.listarTodo();
        }
        else return new ArrayList<>();
    }
    
    public List<Usuario> listarPorRolId(int idRol){
        return usuarioService.listarPorRolId(idRol);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}

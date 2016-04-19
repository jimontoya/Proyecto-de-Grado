/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neurocienciasueb.web.controllers;

import com.neurocienciasueb.dto.Funcionalidad;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Isaac
 */
@Controller
@Scope("session")
public class MenuController extends BaseController implements Serializable
{
    private static final long serialVersionUID = 0535762647673744560224L;
    
    private MenuModel model;
    
        
    private Set<Funcionalidad> funcionalidadesRoles;
    
    public MenuModel getModel()
    {
        DefaultSeparator separator = new DefaultSeparator();
        model = new DefaultMenuModel();
        llenarFuncionalidadesRoles();
        
        for(Funcionalidad f : funcionalidadesRoles)
        {
            if(f.getFuncionalidad() == null)
            {
                DefaultSubMenu p = new DefaultSubMenu(f.getNombreFuncionalidad(),
                        f.getInterfaz());
                
                model.addElement(generateMenu(f.getFuncionalidads(), p));
                
                model.addElement(separator);
            }
        }
        
        model.generateUniqueIds();
        return model;
    }
    
    
    private DefaultSubMenu generateMenu(List<Funcionalidad> funcionalidad, DefaultSubMenu padre)
    {
        funcionalidad.stream().forEach((f) -> {
            
            if(f.getInterfaz().equals("#"))
            {
                if(funcionalidadesRoles.contains(f))
                {
                    DefaultSubMenu current = new DefaultSubMenu(f.getNombreFuncionalidad());
                
                    padre.addElement(current);

                    generateMenu(f.getFuncionalidads(), current);
                }                
            }
            else
            {
                if(funcionalidadesRoles.contains(f))
                {
                   DefaultMenuItem item = new DefaultMenuItem(f.getNombreFuncionalidad(),
                            "",
                            f.getInterfaz());
                    padre.addElement(item); 
                }                
            }
        });
        
        return padre;
    }

    private void llenarFuncionalidadesRoles() {
        funcionalidadesRoles = new HashSet<>();        
            funcionalidadesRoles.addAll(getUsuario().getRol().getFuncionalidads());       
    }
}

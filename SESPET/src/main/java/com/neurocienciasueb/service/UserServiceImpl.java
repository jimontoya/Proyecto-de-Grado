/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.neurocienciasueb.service;

import com.neurocienciasueb.dao.UsuarioDao;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author isaac
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService, Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private UsuarioDao service; 

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        UserDetails ud = service.findFirstByUserName(string);
        return ud;
    }   
}

package com.neurocienciasueb.dao;

import com.neurocienciasueb.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioDao extends JpaRepository<Usuario, String>
{
    Usuario findFirstByUserName(String userName);
    
    
}

<<<<<<< HEAD
package com.neurocienciasueb.dao;

import com.neurocienciasueb.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioDao extends JpaRepository<Usuario, String>
{
    Usuario findFirstByUserName(String userName);
    
    
}
=======
package com.neurocienciasueb.dao;

import com.neurocienciasueb.dto.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioDao extends JpaRepository<Usuario, String>
{
    Usuario findFirstByUserName(String userName);
    
    List<Usuario> findByRolId(int idRol);
    
    @Query(value = "SELECT * FROM usuario u where u.rol = 3 and u.estado = 'A' "
            + "AND (lower(u.nombre_completo) LIKE  lower(concat('%',?1,'%')) "
            + "OR lower(u.documento_identidad) like lower(concat('%',?1,'%')))", nativeQuery = true)
    List<Usuario> findPaciantesByNombreOrDocumento(String busqueda);
}
>>>>>>> 15fc32ced39cf361a80e126b964e70a0fda63d2c

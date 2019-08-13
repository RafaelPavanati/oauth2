/*
*
* Aula com Nataniel Paiva
*
* Esse projeto poderá ser distribuído da forma que você achar melhor
* O importante é que você aprenda de verdade!
*
 */
package com.cicloi.config;

import com.cicloi.model.Perfil;
import com.cicloi.model.Usuario;
import com.cicloi.repository.PerfilRepository;
import com.cicloi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nataniel Paiva <nataniel.paiva@gmail.com>
 */
@Component
public class CargaInicial implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    PerfilRepository perfilRepository;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        List<Perfil> perfis = perfilRepository.findAll();
        
        if(perfis.isEmpty()){
            perfilRepository.save(new Perfil("ROLE_ADMIN"));
            
            Perfil perfil = perfilRepository.findByNome("ROLE_ADMIN");
            
            List<Perfil> perfisUsuario = new ArrayList<>();
            
            perfisUsuario.add(perfil);
            
            usuarioRepository.save(new Usuario("rafael", perfisUsuario, "admin", "123"));
        }
    }
    
}

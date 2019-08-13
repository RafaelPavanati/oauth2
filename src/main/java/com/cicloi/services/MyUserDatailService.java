package com.cicloi.services;

import com.cicloi.config.MyUserDetails;
import com.cicloi.model.Usuario;
import com.cicloi.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDatailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public MyUserDatailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private static RuntimeException get() {
        throw new UsernameNotFoundException("Usuario ou senha invalidos");
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Usuario usuario = Optional.ofNullable(usuarioRepository.findByEmail(userName))
                .orElseThrow(MyUserDatailService::get);
        return new MyUserDetails(usuario);
    }
}

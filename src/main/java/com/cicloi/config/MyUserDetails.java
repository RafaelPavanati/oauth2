package com.cicloi.config;

import com.cicloi.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails extends Usuario implements UserDetails {

    public MyUserDetails(Usuario usuario) {
        super(usuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Perfil implementa GrantedAuthority
        return getPerfis();
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getNome();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

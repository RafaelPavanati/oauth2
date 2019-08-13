package com.cicloi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document
public class Perfil implements GrantedAuthority {

    @Id
    private String id;

    private String nome;

    public Perfil() {
    }

    public Perfil(String role_admin) {
        this.nome = role_admin;
    }

    public String getId() {
        return id;
    }

    public Perfil setId(String id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Perfil setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}

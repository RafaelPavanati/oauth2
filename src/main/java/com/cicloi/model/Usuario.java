package com.cicloi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Usuario {

    @Id
    private String id;

    private String nome;

    @DBRef
    private List<Perfil> perfis;

    private int idade;

    private String email;

    private String senha;

    public Usuario(String nome, List<Perfil> perfis, String email, String senha) {
        this.nome = nome;
        this.perfis = perfis;
        this.email = email;
        this.senha = senha;
    }
    public Usuario() {

    }
    public Usuario(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.perfis = usuario.getPerfis();
        this.senha = usuario.getSenha();
    }

    public String getId() {
        return id;
    }

    public Usuario setId(String id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getIdade() {
        return idade;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public Usuario setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
        return this;
    }

    public Usuario setIdade(int idade) {
        this.idade = idade;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }
}

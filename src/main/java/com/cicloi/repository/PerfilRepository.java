package com.cicloi.repository;

import com.cicloi.model.Perfil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends MongoRepository<Perfil , String> {
    Perfil findByNome(String role_admin);
}

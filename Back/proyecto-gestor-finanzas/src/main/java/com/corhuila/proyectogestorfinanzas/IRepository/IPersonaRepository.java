package com.corhuila.proyectogestorfinanzas.IRepository;

import com.corhuila.proyectogestorfinanzas.Document.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonaRepository extends MongoRepository<Persona, String> {
    public Persona findByGmailAndPassword(String gmail, String password);
}

package com.corhuila.proyectogestorfinanzas.IRepository;

import com.corhuila.proyectogestorfinanzas.Document.Gasto;
import com.corhuila.proyectogestorfinanzas.Document.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGastoRepository extends MongoRepository<Gasto, String> {

    List<Gasto> findByCategoria(String categoria);

    List<Gasto> findByPersona(Persona persona);

    List<Gasto> findByPersonaId(String personaId);

}

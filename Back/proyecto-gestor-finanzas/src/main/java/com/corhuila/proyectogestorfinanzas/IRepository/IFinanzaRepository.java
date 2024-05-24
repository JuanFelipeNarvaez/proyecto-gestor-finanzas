package com.corhuila.proyectogestorfinanzas.IRepository;

import com.corhuila.proyectogestorfinanzas.Document.Finanza;
import com.corhuila.proyectogestorfinanzas.Document.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFinanzaRepository extends MongoRepository<Finanza, String> {

    List<Finanza> findByCategoria(String categoria);

    List<Finanza> findByPersona(Persona persona);

    List<Finanza> findByPersonaId(String personaId);

    List<Finanza> findByPersonaIdAndOpcion(String personaId, String opcion); //Lista las personas por su id y la opcion


}

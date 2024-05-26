package com.corhuila.proyectogestorfinanzas.IRepository;

import com.corhuila.proyectogestorfinanzas.Document.Opcion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOpcionRepository extends MongoRepository<Opcion, String> {
}

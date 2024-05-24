package com.corhuila.proyectogestorfinanzas.IRepository;

import com.corhuila.proyectogestorfinanzas.Document.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends MongoRepository<Rol, String> {
}

package com.corhuila.proyectogestorfinanzas.IRepository;

import com.corhuila.proyectogestorfinanzas.Document.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends MongoRepository<Categoria, String> {
}

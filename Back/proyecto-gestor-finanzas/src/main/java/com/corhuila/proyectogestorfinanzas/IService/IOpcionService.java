package com.corhuila.proyectogestorfinanzas.IService;

import com.corhuila.proyectogestorfinanzas.Document.Finanza;
import com.corhuila.proyectogestorfinanzas.Document.Opcion;

import java.util.List;
import java.util.Optional;

public interface IOpcionService {
    public List<Opcion> findAll();
    public Optional<Opcion> findById(String id);
    public Opcion save(Opcion opcion);
    void update(Opcion opcion, String id);
    void delete(String id);
}

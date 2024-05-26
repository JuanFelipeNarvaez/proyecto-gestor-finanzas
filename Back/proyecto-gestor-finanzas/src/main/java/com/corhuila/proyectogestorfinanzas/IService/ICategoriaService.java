package com.corhuila.proyectogestorfinanzas.IService;

import com.corhuila.proyectogestorfinanzas.Document.Categoria;
import com.corhuila.proyectogestorfinanzas.Document.Finanza;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    public List<Categoria> findAll();
    public Optional<Categoria> findById(String id);
    public Categoria save(Categoria categoria);
    void update(Categoria categoria, String id);
    void delete(String id);
}

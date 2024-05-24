package com.corhuila.proyectogestorfinanzas.IService;

import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.Document.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    public List<Rol> findAll();
    public Optional<Rol> findById(String id);
    public Rol save(Rol rol);
    public void update (Rol rol, String id);
    public void delete(String id);
}

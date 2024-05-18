package com.corhuila.proyectogestorfinanzas.IService;

import com.corhuila.proyectogestorfinanzas.Document.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    public List<Persona> findAll();
    public Optional<Persona> findById(String id);
    public Persona save(Persona persona);
    public void update (Persona persona, String id);
    public void delete(String id);
    public Persona login(String gmail, String password);
}

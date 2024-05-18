package com.corhuila.proyectogestorfinanzas.IService;

import com.corhuila.proyectogestorfinanzas.Document.Gasto;
import com.corhuila.proyectogestorfinanzas.Document.Persona;

import java.util.List;
import java.util.Optional;

public interface IGastoService {
    public List<Gasto> findAll();
    public Optional<Gasto> findById(String id);
    public Gasto save(Gasto gasto);
    void update(Gasto gasto, String id);
    void delete(String id);
    public List<Gasto> findByCategoria(String categoria);

    public List<Gasto> findByPersona(Persona persona);

    public List<Gasto> findGastosByPersonaId(String personaId);

    public List<Gasto> findGastosByPersonaIdAndOpcion(String personaId, String opcion);
}

package com.corhuila.proyectogestorfinanzas.IService;

import com.corhuila.proyectogestorfinanzas.Document.Finanza;
import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.Document.SumaResponse;

import java.util.List;
import java.util.Optional;

public interface IFinanzaService {
    public List<Finanza> findAll();
    public Optional<Finanza> findById(String id);
    public Finanza save(Finanza finanza);
    void update(Finanza finanza, String id);
    void delete(String id);
    /*public List<Finanza> findByCategoria(String categoria);

    public List<Finanza> findByPersona(Persona persona);

    public List<Finanza> findGastosByPersonaId(String personaId);*/

    public List<Finanza> findGastosByPersonaIdAndOpcion(String personaId, String opcion);

    public SumaResponse findGastosByPersonaIdAndOpcionSum(String personaId, String opcion);
}

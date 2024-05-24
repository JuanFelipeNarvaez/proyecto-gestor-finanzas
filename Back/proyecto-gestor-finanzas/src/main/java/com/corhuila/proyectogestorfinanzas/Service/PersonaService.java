package com.corhuila.proyectogestorfinanzas.Service;

import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.IRepository.IPersonaRepository;
import com.corhuila.proyectogestorfinanzas.IService.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements IPersonaService {
    @Autowired
    private IPersonaRepository repository;

    @Override
    public List<Persona> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Persona> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Persona save(Persona persona) {
        return repository.save(persona);
    }

    @Override
    public void update(Persona persona, String id) {
        Optional<Persona> per = repository.findById(id);

        if(!per.isEmpty()){
            Persona personaUpdate = per.get();
            personaUpdate.setNombre(persona.getNombre());
            personaUpdate.setApellido(persona.getApellido());
            personaUpdate.setEdad(persona.getEdad());
            personaUpdate.setGmail(persona.getGmail());
            personaUpdate.setPassword(persona.getPassword());
            personaUpdate.setRol(persona.getRol());

            repository.save(personaUpdate);
        }else{
            System.out.println("No existe la persona");
        }
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Persona login(String gmail, String password){
        return repository.findByGmailAndPassword(gmail, password);
    }
}

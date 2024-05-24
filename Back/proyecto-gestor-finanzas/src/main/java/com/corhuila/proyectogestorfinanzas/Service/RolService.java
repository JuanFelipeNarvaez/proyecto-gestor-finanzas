package com.corhuila.proyectogestorfinanzas.Service;

import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.Document.Rol;
import com.corhuila.proyectogestorfinanzas.IRepository.IRolRepository;
import com.corhuila.proyectogestorfinanzas.IService.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService {
    @Autowired
    private IRolRepository repository;
    @Override
    public List<Rol> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Rol> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Rol save(Rol rol) {
        return repository.save(rol);
    }

    @Override
    public void update(Rol rol, String id) {
        Optional<Rol> ro = repository.findById(id);

        if(!ro.isEmpty()){
            Rol rolUpdate = ro.get();
            rolUpdate.setNombre(rol.getNombre());

            repository.save(rolUpdate);
        }else{
            System.out.println("No existe el rol");
        }
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

}

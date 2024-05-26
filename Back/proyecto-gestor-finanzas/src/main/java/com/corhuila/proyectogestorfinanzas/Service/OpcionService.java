package com.corhuila.proyectogestorfinanzas.Service;

import com.corhuila.proyectogestorfinanzas.Document.Opcion;
import com.corhuila.proyectogestorfinanzas.IRepository.IOpcionRepository;
import com.corhuila.proyectogestorfinanzas.IService.IOpcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpcionService implements IOpcionService {
    @Autowired
    private IOpcionRepository repository;

    @Override
    public List<Opcion> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Opcion> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Opcion save(Opcion opcion) {
        return repository.save(opcion);
    }

    @Override
    public void update(Opcion opcion, String id) {
        Optional<Opcion> opc = repository.findById(id);

        if (!opc.isEmpty()){
            Opcion opcionUpdate = opc.get();
            opcionUpdate.setNombre(opcion.getNombre());

            repository.save(opcionUpdate);
        }else{
            System.out.println("No existe la opcion");
        }
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}

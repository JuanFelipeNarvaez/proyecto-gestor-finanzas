package com.corhuila.proyectogestorfinanzas.Service;

import com.corhuila.proyectogestorfinanzas.Document.Gasto;
import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.IRepository.IGastoRepository;
import com.corhuila.proyectogestorfinanzas.IService.IGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoService implements IGastoService {
    @Autowired
    private IGastoRepository repository;

    @Override
    public List<Gasto> findAll(){
        return repository.findAll();
    }


    @Override
    public Optional<Gasto> findById(String id){
        return repository.findById(id);
    }

    @Override
    public Gasto save(Gasto gasto){
        return repository.save(gasto);
    }

    @Override
    public void update(Gasto gasto, String id){
        Optional<Gasto> gas = repository.findById(id);

        if (!gas.isEmpty()){
            Gasto gastoUpdate = gas.get();
            gastoUpdate.setValor(gasto.getValor());
            gastoUpdate.setCategoria(gasto.getCategoria());
            gastoUpdate.setFecha(gasto.getFecha());
            gastoUpdate.setComentario(gasto.getComentario());

            repository.save(gastoUpdate);
        }else{
            System.out.println("No existe el gasto");
        }
    }
    @Override
    public void delete(String id){
        repository.deleteById(id);
    }

    @Override
    public List<Gasto> findByCategoria(String categoria){
        return repository.findByCategoria(categoria);
    }

    @Override
    public List<Gasto> findByPersona(Persona persona){
        return repository.findByPersona(persona);
    }

    @Override
    public List<Gasto> findGastosByPersonaId(String personaId){
        return repository.findByPersonaId(personaId);
    }

}

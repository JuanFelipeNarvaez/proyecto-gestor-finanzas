package com.corhuila.proyectogestorfinanzas.Service;

import com.corhuila.proyectogestorfinanzas.Document.Finanza;
import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.Document.SumaResponse;
import com.corhuila.proyectogestorfinanzas.IRepository.IFinanzaRepository;
import com.corhuila.proyectogestorfinanzas.IService.IFinanzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanzaService implements IFinanzaService {
    @Autowired
    private IFinanzaRepository repository;

    @Override
    public List<Finanza> findAll(){
        return repository.findAll();
    }


    @Override
    public Optional<Finanza> findById(String id){
        return repository.findById(id);
    }

    @Override
    public Finanza save(Finanza gasto){
        return repository.save(gasto);
    }

    @Override
    public void update(Finanza gasto, String id){
        Optional<Finanza> gas = repository.findById(id);

        if (!gas.isEmpty()){
            Finanza gastoUpdate = gas.get();
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
    public List<Finanza> findByCategoria(String categoria){
        return repository.findByCategoria(categoria);
    }

    @Override
    public List<Finanza> findByPersona(Persona persona){
        return repository.findByPersona(persona);
    }

    @Override
    public List<Finanza> findGastosByPersonaId(String personaId){
        return repository.findByPersonaId(personaId);
    }

    @Override
    public List<Finanza> findGastosByPersonaIdAndOpcion(String personaId, String opcion){
        return repository.findByPersonaIdAndOpcion(personaId, opcion);
    }

    @Override
    public SumaResponse findGastosByPersonaIdAndOpcionSum(String personaId, String opcion){
        List<Finanza> finanzas = repository.findByPersonaIdAndOpcion(personaId, opcion);
        Integer sumaTotal = finanzas.stream().mapToInt(Finanza::getValor).sum();
        return new SumaResponse(finanzas, sumaTotal);
    }

}

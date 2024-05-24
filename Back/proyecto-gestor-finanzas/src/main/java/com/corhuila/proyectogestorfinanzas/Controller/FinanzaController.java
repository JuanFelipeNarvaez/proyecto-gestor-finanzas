package com.corhuila.proyectogestorfinanzas.Controller;

import com.corhuila.proyectogestorfinanzas.Document.Finanza;
import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.Document.SumaResponse;
import com.corhuila.proyectogestorfinanzas.IService.IFinanzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/gasto")
public class FinanzaController {
    @Autowired
    private IFinanzaService service;

    @GetMapping()
    public List<Finanza> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Finanza> findById(@PathVariable String id){
        return service.findById(id);
    }

    @PostMapping()
    public Finanza save(@RequestBody Finanza gasto){
        return service.save(gasto);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Finanza gasto, @PathVariable String id){
        service.update(gasto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @GetMapping("/findByCategoria/{categoria}")
    public List<Finanza> findByCategoria(@PathVariable String categoria){
        return service.findByCategoria(categoria);
    }

    @GetMapping("/{persona}")
    public List<Finanza> findByPersona(@PathVariable Persona persona){
        return service.findByPersona(persona);
    }

    @GetMapping("/byPersona/{personaId}")
    public List<Finanza> getGastosByPersonaId(@PathVariable String personaId){
        return service.findGastosByPersonaId(personaId);
    }

    @GetMapping("/byPersona/{personaId}/{opcion}")
    public List<Finanza> getGastoByPersonaIdAndOpcion(@PathVariable String personaId, @PathVariable String opcion){
        return service.findGastosByPersonaIdAndOpcion(personaId, opcion);
    }

    @GetMapping("/byPersona/{personaId}/suma/{opcion}")
    public SumaResponse sumatoria(@PathVariable String personaId, @PathVariable String opcion){
        return service.findGastosByPersonaIdAndOpcionSum(personaId, opcion);
    }
}

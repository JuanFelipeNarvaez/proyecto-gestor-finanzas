package com.corhuila.proyectogestorfinanzas.Controller;

import com.corhuila.proyectogestorfinanzas.Document.Gasto;
import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.IService.IGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/gasto")
public class GastoController {
    @Autowired
    private IGastoService service;

    @GetMapping()
    public List<Gasto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Gasto> findById(@PathVariable String id){
        return service.findById(id);
    }

    @PostMapping()
    public Gasto save(@RequestBody Gasto gasto){
        return service.save(gasto);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Gasto gasto, @PathVariable String id){
        service.update(gasto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }

    @GetMapping("/findByCategoria/{categoria}")
    public List<Gasto> findByCategoria(@PathVariable String categoria){
        return service.findByCategoria(categoria);
    }

    @GetMapping("/{persona}")
    public List<Gasto> findByPersona(@PathVariable Persona persona){
        return service.findByPersona(persona);
    }

    @GetMapping("/byPersona/{personaId}")
    public List<Gasto> getGastosByPersonaId(@PathVariable String personaId){
        return service.findGastosByPersonaId(personaId);
    }
}

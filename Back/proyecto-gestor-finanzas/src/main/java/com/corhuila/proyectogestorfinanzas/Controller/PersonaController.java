package com.corhuila.proyectogestorfinanzas.Controller;

import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.IService.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/persona")
public class PersonaController {
    @Autowired
    private IPersonaService service;
    @GetMapping()
    public List<Persona> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Persona> findById(@PathVariable String id){
        return service.findById(id);
    }
    @PostMapping()
    public Persona save(@RequestBody Persona persona){
        return service.save(persona);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody Persona persona, @PathVariable String id){
        service.update(persona, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }
    @PostMapping("login")
    public Persona login(@RequestBody Persona persona){
        return service.login(persona.getGmail(), persona.getPassword());
    }
}

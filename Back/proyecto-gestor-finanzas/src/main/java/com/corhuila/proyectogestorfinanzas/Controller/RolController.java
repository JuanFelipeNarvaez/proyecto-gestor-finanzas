package com.corhuila.proyectogestorfinanzas.Controller;

import com.corhuila.proyectogestorfinanzas.Document.Persona;
import com.corhuila.proyectogestorfinanzas.Document.Rol;
import com.corhuila.proyectogestorfinanzas.IService.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/rol")
public class RolController {
    @Autowired
    private IRolService service;
    @GetMapping()
    public List<Rol> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Rol> findById(@PathVariable String id){
        return service.findById(id);
    }
    @PostMapping()
    public Rol save(@RequestBody Rol rol){
        return service.save(rol);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody Rol rol, @PathVariable String id){
        service.update(rol, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}

package com.corhuila.proyectogestorfinanzas.Controller;

import com.corhuila.proyectogestorfinanzas.Document.Finanza;
import com.corhuila.proyectogestorfinanzas.Document.Opcion;
import com.corhuila.proyectogestorfinanzas.IService.IOpcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/opcion")
public class OpcionController {
    @Autowired
    private IOpcionService service;
    @GetMapping()
    public List<Opcion> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Opcion> findById(@PathVariable String id){
        return service.findById(id);
    }

    @PostMapping()
    public Opcion save(@RequestBody Opcion opcion){
        return service.save(opcion);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Opcion opcion, @PathVariable String id){
        service.update(opcion, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }
}

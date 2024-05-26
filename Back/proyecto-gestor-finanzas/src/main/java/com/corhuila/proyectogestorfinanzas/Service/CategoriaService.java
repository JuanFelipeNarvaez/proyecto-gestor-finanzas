package com.corhuila.proyectogestorfinanzas.Service;

import com.corhuila.proyectogestorfinanzas.Document.Categoria;
import com.corhuila.proyectogestorfinanzas.IRepository.ICategoriaRepository;
import com.corhuila.proyectogestorfinanzas.IService.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    private ICategoriaRepository repository;

    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Categoria> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public void update(Categoria categoria, String id) {
        Optional<Categoria> cat = repository.findById(id);

        if(!cat.isEmpty()){
            Categoria categoriaUpdate = cat.get();
            categoriaUpdate.setNombre(categoria.getNombre());

            repository.save(categoriaUpdate);
        }else{
            System.out.println("No existe la categoria");
        }
    }

    @Override
    public void delete(String id) {

    }
}

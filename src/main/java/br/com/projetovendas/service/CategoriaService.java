package br.com.projetovendas.service;

import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.repository.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Categoria não encontrada", Categoria.class.getName()));
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

}

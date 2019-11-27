package br.com.projetovendas.service;

import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.repository.ProdutoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Produto não encontrado", Produto.class.getName()));
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public void save(Produto produto) {
        produtoRepository.save(produto);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

}

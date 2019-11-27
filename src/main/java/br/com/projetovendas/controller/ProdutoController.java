package br.com.projetovendas.controller;

import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.service.CategoriaService;
import br.com.projetovendas.service.ProdutoService;
import br.com.projetovendas.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final UploadService uploadService;

    @Autowired
    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService, UploadService uploadService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.uploadService = uploadService;
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("produto/listar");
        List<Produto> produtos = produtoService.findAll();
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionarCategroria(Produto produto) {
        ModelAndView mv = new ModelAndView("produto/adicionar");
        mv.addObject("produto", produto);
        List<Categoria> categorias = categoriaService.findAll();
        mv.addObject("categorias", categorias);
        return mv;
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Produto produto, @RequestParam("file") MultipartFile file, BindingResult result) {
        if (result.hasErrors()) {
            return adicionarCategroria(produto);
        }
        uploadService.salvar(file);
        produto.setLogo(file.getOriginalFilename());
        produtoService.save(produto);
        return listar();
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarProduto(@PathVariable("id") Long id) {
        Produto produto = produtoService.findById(id);
        return adicionarCategroria(produto);
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable Long id) {
        produtoService.delete(id);
        return listar();
    }

}

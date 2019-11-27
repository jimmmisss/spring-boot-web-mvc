package br.com.projetovendas.controller;

import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.entity.Endereco;
import br.com.projetovendas.service.CategoriaService;
import br.com.projetovendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("categoria/listar");
        List<Categoria> categorias = categoriaService.findAll();
        mv.addObject("categorias", categorias);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionarCategroria(Categoria categoria) {
        ModelAndView mv = new ModelAndView("categoria/adicionar");
        mv.addObject("categoria", categoria);
        return mv;
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            return adicionarCategroria(categoria);
        }
        categoriaService.save(categoria);
        return listar();
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarCategoria(@PathVariable("id") Long id) {
        Categoria categoria = categoriaService.findById(id);
        return adicionarCategroria(categoria);
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable Long id) {
        categoriaService.delete(id);
        return listar();
    }

}

package br.com.projetovendas.controller;

import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.entity.Pedido;
import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.service.ClienteService;
import br.com.projetovendas.service.PedidoService;
import br.com.projetovendas.service.ProdutoService;
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
@RequestMapping("/venda")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    @Autowired
    public PedidoController(PedidoService pedidoService, ClienteService clienteService, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("venda/listar");
        List<Pedido> pedidos = pedidoService.findAll();
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionarPedido(Pedido pedido) {
        ModelAndView mv = new ModelAndView("venda/adicionar");
        List<Cliente> clientes = clienteService.findAll();
        List<Produto> produtos = produtoService.findAll();
        mv.addObject("produtos", produtos);
        mv.addObject("clientes", clientes);
        return mv;
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Pedido pedido, BindingResult result) {
        if (result.hasErrors()) {
            return adicionarPedido(pedido);
        }
        pedidoService.save(pedido);
        return listar();
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletar(@PathVariable Long id) {
        pedidoService.delete(id);
        return listar();
    }

}

package br.com.projetovendas.controller;

import br.com.projetovendas.entity.Categoria;
import br.com.projetovendas.entity.Cliente;
import br.com.projetovendas.entity.Pedido;
import br.com.projetovendas.entity.Produto;
import br.com.projetovendas.service.CategoriaService;
import br.com.projetovendas.service.ClienteService;
import br.com.projetovendas.service.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;

    @Autowired
    public PedidoController(PedidoService pedidoService, ClienteService clienteService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("produto/listar");
        List<Pedido> pedidos = pedidoService.findAll();
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionarPedido(Pedido pedido) {
        ModelAndView mv = new ModelAndView("venda/adicionar");
        mv.addObject("pedido", pedido);
        List<Cliente> clientes = clienteService.findAll();
        mv.addObject("categorias", clientes);
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

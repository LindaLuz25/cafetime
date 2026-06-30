package pe.edu.idat.appcafetime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.idat.appcafetime.model.Usuario;
import pe.edu.idat.appcafetime.service.PedidoService;
import pe.edu.idat.appcafetime.service.UsuarioService;

@Controller
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    @PostMapping("/comprar/{idProducto}")
    public String comprar(@PathVariable Integer idProducto,
                          Authentication authentication,
                          RedirectAttributes redirectAttributes) {

        Usuario usuario =
                usuarioService.getUsuarioByUsername(authentication.getName());

        pedidoService.registrarCompra(usuario, idProducto);

        redirectAttributes.addFlashAttribute(
                "success",
                "Pedido registrado correctamente."
        );

        return "redirect:/auth/home";
    }

    @GetMapping
    public String listarPedidos(Model model) {

        model.addAttribute("pedidos",
                pedidoService.listarPedidos());

        return "admin/pedidos";
    }

    @PostMapping("/{idPedido}/estado")
    public String actualizarEstado(@PathVariable Integer idPedido,
                                   @RequestParam String estado) {

        pedidoService.actualizarEstado(idPedido, estado);

        return "redirect:/pedido";
    }

}
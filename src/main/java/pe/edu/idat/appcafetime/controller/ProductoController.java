package pe.edu.idat.appcafetime.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.appcafetime.model.Producto;
import pe.edu.idat.appcafetime.service.ProductoService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "producto/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto/formulario";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productoService.obtenerProducto(id));
        return "producto/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Producto producto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "producto/formulario";
        }

        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}
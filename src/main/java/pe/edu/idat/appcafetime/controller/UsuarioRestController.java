package pe.edu.idat.appcafetime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.idat.appcafetime.model.Usuario;
import pe.edu.idat.appcafetime.service.UsuarioService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    @PostMapping("/admin")
    public Usuario crearAdmin(@RequestBody Usuario usuario){
        return usuarioService.saveAdmin(usuario);
    }

}
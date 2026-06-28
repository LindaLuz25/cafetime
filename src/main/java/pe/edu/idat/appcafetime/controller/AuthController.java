package pe.edu.idat.appcafetime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.appcafetime.dto.UsuarioSeguridadDto;
import pe.edu.idat.appcafetime.model.Usuario;
import pe.edu.idat.appcafetime.service.UsuarioService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "auth/formLogin";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "auth/frmRegistroUser";
    }

    @GetMapping("/login-success")
    public String loginSuccess(){
        return "redirect:/auth/home";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.saveUser(usuario);
        return "auth/frmLogin";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UsuarioSeguridadDto dto, Model model){
        if (dto == null){
            return "redirect:/auth/login";
        }

        model.addAttribute("nombre", dto.getUsername());
        model.addAttribute("email", dto.getEmail());
        return "auth/home";
    }

}

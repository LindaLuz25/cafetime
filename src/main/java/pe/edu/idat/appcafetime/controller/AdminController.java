package pe.edu.idat.appcafetime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.appcafetime.dto.UsuarioSeguridadDto;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UsuarioSeguridadDto usuario,
                            Model model) {

        model.addAttribute("nombre", usuario.getUsername());
        model.addAttribute("email", usuario.getEmail());

        return "admin/dashboard";
    }
}
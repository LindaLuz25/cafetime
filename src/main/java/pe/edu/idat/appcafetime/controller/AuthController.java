package pe.edu.idat.appcafetime.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appcafetime.dto.LoginForm;
import pe.edu.idat.appcafetime.dto.UsuarioSeguridadDto;
import pe.edu.idat.appcafetime.model.Usuario;
import pe.edu.idat.appcafetime.service.RecaptchaService;
import pe.edu.idat.appcafetime.service.UsuarioService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;
    private final RecaptchaService captchaService;

    @Value("${recaptcha.site-key}")
    private String recaptchaSiteKey;

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(required = false) String registroExitoso){
        model.addAttribute("form", new LoginForm());
        model.addAttribute("recaptchaSiteKey", recaptchaSiteKey);
        if (registroExitoso != null) {
            model.addAttribute("registroExitoso", "Registro completado. Ahora puedes iniciar sesion.");
        }
        return "auth/frmLogin";
    }

    @GetMapping("/registrar")
    public String registrar(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("recaptchaSiteKey", recaptchaSiteKey);
        return "auth/frmRegistroUser";
    }

    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication){
        System.out.println("Entró al login-success");

        if(authentication != null){
            System.out.println(authentication.getName());
        }

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            return "redirect:/admin/dashboard";
        }
        return "redirect:/auth/home";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(
            @ModelAttribute Usuario usuario,
            @RequestParam("g-recaptcha-response") String token,
            HttpServletRequest request,
            Model model){

        boolean captchaValido =
                captchaService.verify(token, request.getRemoteAddr());

        if(!captchaValido){
            model.addAttribute("usuario", usuario);
            model.addAttribute("errorCaptcha",
                    "Debe completar correctamente el CAPTCHA.");
            return "auth/frmRegistroUser";
        }

        usuarioService.saveUser(usuario);

        return "redirect:/auth/login?registroExitoso";
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

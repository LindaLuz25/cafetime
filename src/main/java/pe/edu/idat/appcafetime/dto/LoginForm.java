package pe.edu.idat.appcafetime.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank(message = "Usuario requerido")
    private String username;

    @NotBlank(message = "Password requerido")
    private String password;
}
package pe.edu.idat.appcafetime.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.idat.appcafetime.model.Rol;
import pe.edu.idat.appcafetime.model.Usuario;
import pe.edu.idat.appcafetime.repository.UsuarioRepository;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario getUsuarioByUsuario(String usuario){
        return usuarioRepository.findByNombre(usuario);
    }

    public Usuario saveUser(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Rol rol = new Rol();
        rol.setId(1);
        usuario.setRol(rol);
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }
}

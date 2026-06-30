package pe.edu.idat.appcafetime.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.idat.appcafetime.model.Rol;
import pe.edu.idat.appcafetime.model.Usuario;
import pe.edu.idat.appcafetime.repository.RolRepository;
import pe.edu.idat.appcafetime.repository.UsuarioRepository;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario saveUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Rol rol = rolRepository.findByNombre("ROLE_CLIENTE");
        usuario.setRol(rol);
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    public Usuario saveAdmin(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Rol rol = rolRepository.findByNombre("ROLE_ADMIN");
        usuario.setRol(rol);
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);

    }
}

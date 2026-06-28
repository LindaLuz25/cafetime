package pe.edu.idat.appcafetime.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.idat.appcafetime.dto.UsuarioSeguridadDto;
import pe.edu.idat.appcafetime.model.Rol;
import pe.edu.idat.appcafetime.model.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DetalleUsuarioService implements UserDetailsService{

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioService.getUsuarioByUsuario(username);
        return getUsuarioSeguridadDto(usuario,
                grantedAuthorityList(usuario.getRol()));
    }

    private List<GrantedAuthority> grantedAuthorityList(Rol rol){
        if (rol == null){
            return Collections.emptyList();
        }
        return List.of(new SimpleGrantedAuthority(rol.getNombre()));
    }

    private UsuarioSeguridadDto getUsuarioSeguridadDto(Usuario usuario, List<GrantedAuthority> grantedAuthorities){
        UsuarioSeguridadDto dto = new UsuarioSeguridadDto(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true,
                grantedAuthorities
        );
        dto.setEmail(usuario.getEmail());
        return dto;
    }

}

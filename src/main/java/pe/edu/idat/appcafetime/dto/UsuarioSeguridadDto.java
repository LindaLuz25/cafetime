package pe.edu.idat.appcafetime.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UsuarioSeguridadDto extends User {
    private String email;

    public UsuarioSeguridadDto(String username,
                               @Nullable String password,
                               boolean enabled,
                               boolean accountNonExpired,
                               boolean credentialsNonExpired,
                               boolean accountNonLocked,
                               Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
    }
}

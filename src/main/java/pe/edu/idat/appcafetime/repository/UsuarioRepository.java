package pe.edu.idat.appcafetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.appcafetime.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByUsername(String username);
}

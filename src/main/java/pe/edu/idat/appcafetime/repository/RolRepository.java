package pe.edu.idat.appcafetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.appcafetime.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}

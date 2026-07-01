package pe.edu.idat.appcafetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.appcafetime.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}

package pe.edu.idat.appcafetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.appcafetime.model.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Integer> {
}
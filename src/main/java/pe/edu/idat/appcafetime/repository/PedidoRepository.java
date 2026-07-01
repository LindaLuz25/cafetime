package pe.edu.idat.appcafetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.appcafetime.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
package pe.edu.idat.appcafetime.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appcafetime.model.DetallePedido;
import pe.edu.idat.appcafetime.model.Pedido;
import pe.edu.idat.appcafetime.model.Producto;
import pe.edu.idat.appcafetime.model.Usuario;
import pe.edu.idat.appcafetime.repository.PedidoRepository;
import pe.edu.idat.appcafetime.repository.ProductoRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoService productoService;

    public Pedido registrarCompra(Usuario usuario, Integer idProducto) {

        Producto producto = productoService.obtenerProducto(idProducto);

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(producto.getPrecio());

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(1);
        detalle.setPrecio(producto.getPrecio());
        detalle.setSubtotal(producto.getPrecio());

        pedido.setDetalles(List.of(detalle));

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedido(Integer idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Transactional
    public void actualizarEstado(Integer idPedido, String estado) {
        Pedido pedido = obtenerPedido(idPedido);
        pedido.setEstado(estado);
        pedidoRepository.save(pedido);
    }
}
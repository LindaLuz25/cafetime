package pe.edu.idat.appcafetime.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appcafetime.model.Producto;
import pe.edu.idat.appcafetime.repository.ProductoRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProducto(Integer id) {
        return productoRepository.findById(id).orElseThrow();
    }

    public Producto guardarProducto(Producto producto) {
        if (producto.getActivo() == null) {
            producto.setActivo(true);
        }
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}

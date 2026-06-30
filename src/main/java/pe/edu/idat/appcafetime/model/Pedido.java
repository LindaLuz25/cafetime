package pe.edu.idat.appcafetime.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedido")
    private Integer id;

    @NotNull(message = "El usuario del pedido es obligatorio")
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    private LocalDateTime fecha;

    @NotNull(message = "El total del pedido es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El total debe tener hasta 8 enteros y 2 decimales")
    private BigDecimal total;

    @NotBlank(message = "El estado del pedido es obligatorio")
    private String estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;

    @PrePersist
    public void prePersist() {
        fecha = LocalDateTime.now();

        if (estado == null) {
            estado = "PENDIENTE";
        }
    }

}
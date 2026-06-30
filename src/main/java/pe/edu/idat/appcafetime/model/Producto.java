package pe.edu.idat.appcafetime.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 120, message = "El nombre no debe superar los 120 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripcion no debe superar los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El precio debe tener hasta 8 enteros y 2 decimales")
    private BigDecimal precio;

    @NotNull(message = "El stock del producto es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotBlank(message = "La categoria del producto es obligatoria")
    @Size(max = 80, message = "La categoria no debe superar los 80 caracteres")
    private String categoria;

    @Size(max = 255, message = "La URL de la imagen no debe superar los 255 caracteres")
    private String imagen;

    private Boolean activo;

}
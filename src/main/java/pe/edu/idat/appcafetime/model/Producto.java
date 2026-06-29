package pe.edu.idat.appcafetime.model;

import jakarta.persistence.*;
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

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private Integer stock;

    private String categoria;

    private String imagen;

    private Boolean activo;

}
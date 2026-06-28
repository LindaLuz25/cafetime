package pe.edu.idat.appcafetime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "idrol")
    private Rol rol;

}

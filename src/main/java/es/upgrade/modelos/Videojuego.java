package es.upgrade.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidad que representa un videojuego en la base de datos.
 * Contiene información como el ID, nombre, compañía y nota del videojuego.
 */
@Entity
@Table(name = "videojuegos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Videojuego {
    /**
     * Identificador único del videojuego.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del videojuego. No puede estar en blanco y debe ser único.
     */
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Column(nullable = false, unique = true)
    private String nombre;

    /**
     * Compañía desarrolladora del videojuego.
     */
    private String compania;

    /**
     * Nota o calificación del videojuego.
     */
    private Double nota;

    // Agregar getters y setters explícitos por si Lombok falla
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

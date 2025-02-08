package es.upgrade.repositorios;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.upgrade.modelos.Videojuego;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
    Optional<Videojuego> findByNombre(String nombre);
    List<Videojuego> findByNombreContaining(String nombre);
}

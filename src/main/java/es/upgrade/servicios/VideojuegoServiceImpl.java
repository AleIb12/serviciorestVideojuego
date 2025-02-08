package es.upgrade.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import es.upgrade.modelos.Videojuego;
import es.upgrade.repositorios.VideojuegoRepository;

/**
 * Implementación de la interfaz VideojuegoService.
 * Proporciona la lógica de negocio para la gestión de videojuegos.
 */
@Service
public class VideojuegoServiceImpl implements VideojuegoService {

    @Autowired
    private VideojuegoRepository repo;

    /**
     * Crea un nuevo videojuego.
     * @param videojuego El videojuego a crear.
     * @return El videojuego creado.
     * @throws RuntimeException Si ya existe un videojuego con el mismo nombre.
     */
    @Override
    public Videojuego crear(Videojuego videojuego) {
        Optional<Videojuego> existente = repo.findByNombre(videojuego.getNombre());
        if(existente.isPresent()){
            throw new RuntimeException("Ya existe un videojuego con el mismo nombre");
        }
        return repo.save(videojuego);
    }

    /**
     * Actualiza un videojuego existente.
     * @param id El ID del videojuego a actualizar.
     * @param videojuego El videojuego con los datos actualizados.
     * @return El videojuego actualizado.
     * @throws RuntimeException Si el videojuego no se encuentra o si ya existe otro videojuego con el mismo nombre.
     */
    @Override
    public Videojuego actualizar(Long id, Videojuego videojuego) {
        Videojuego existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));
        // Verificar duplicidad de nombre si cambia
        if(!existente.getNombre().equals(videojuego.getNombre())) {
            repo.findByNombre(videojuego.getNombre()).ifPresent(v -> {
                throw new RuntimeException("Ya existe un videojuego con el mismo nombre");
            });
        }
        // Actualización de campos
        existente.setNombre(videojuego.getNombre());
        existente.setCompania(videojuego.getCompania());
        existente.setNota(videojuego.getNota());
        return repo.save(existente);
    }

    /**
     * Obtiene un videojuego por su ID.
     * @param id El ID del videojuego a buscar.
     * @return El videojuego encontrado.
     * @throws RuntimeException Si el videojuego no se encuentra.
     */
    @Override
    public Videojuego obtenerPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));
    }

    /**
     * Elimina un videojuego por su ID.
     * @param id El ID del videojuego a eliminar.
     */
    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    /**
     * Lista todos los videojuegos.
     * @return Una lista con todos los videojuegos.
     */
    @Override
    public List<Videojuego> listarTodos() {
        return repo.findAll();
    }

    /**
     * Lista los videojuegos cuyo nombre contenga la cadena especificada.
     * @param nombre La cadena a buscar en el nombre de los videojuegos.
     * @return Una lista de videojuegos cuyo nombre contiene la cadena especificada.
     */
    @Override
    public List<Videojuego> listarPorNombre(String nombre) {
        return repo.findByNombreContaining(nombre);
    }
}

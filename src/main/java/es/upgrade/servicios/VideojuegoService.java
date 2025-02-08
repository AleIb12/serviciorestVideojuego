package es.upgrade.servicios;

import java.util.List;
import es.upgrade.modelos.Videojuego;

/**
 * Interfaz que define los servicios para la gestión de videojuegos.
 */
public interface VideojuegoService {
    /**
     * Crea un nuevo videojuego.
     * @param videojuego El videojuego a crear.
     * @return El videojuego creado.
     */
    Videojuego crear(Videojuego videojuego);

    /**
     * Actualiza un videojuego existente.
     * @param id El ID del videojuego a actualizar.
     * @param videojuego El videojuego con los datos actualizados.
     * @return El videojuego actualizado.
     */
    Videojuego actualizar(Long id, Videojuego videojuego);

    /**
     * Obtiene un videojuego por su ID.
     * @param id El ID del videojuego a buscar.
     * @return El videojuego encontrado.
     */
    Videojuego obtenerPorId(Long id);

    /**
     * Elimina un videojuego por su ID.
     * @param id El ID del videojuego a eliminar.
     */
    void eliminar(Long id);

    /**
     * Lista todos los videojuegos.
     * @return Una lista con todos los videojuegos.
     */
    List<Videojuego> listarTodos();

    /**
     * Lista los videojuegos cuyo nombre contenga la cadena especificada.
     * @param nombre La cadena a buscar en el nombre de los videojuegos.
     * @return Una lista de videojuegos cuyo nombre contiene la cadena especificada.
     */
    List<Videojuego> listarPorNombre(String nombre);
}

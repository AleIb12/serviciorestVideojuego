package es.upgrade.controladores;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.upgrade.modelos.Videojuego;
import es.upgrade.servicios.VideojuegoService;

/**
 * Controlador REST para la gestión de videojuegos.
 * Expone los endpoints para realizar operaciones CRUD sobre la entidad Videojuego.
 */
@RestController
@RequestMapping("/api/videojuegos")
@CrossOrigin(origins = "*")
public class VideojuegoController {

    @Autowired
    private VideojuegoService service;

    /**
     * Crea un nuevo videojuego.
     * @param videojuego El videojuego a crear.
     * @return El videojuego creado.
     */
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Videojuego videojuego) {
        try {
            Videojuego nuevoVideojuego = service.crear(videojuego);
            return new ResponseEntity<>(nuevoVideojuego, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Elimina un videojuego por su ID.
     * @param id El ID del videojuego a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return new ResponseEntity<>("Videojuego eliminado con éxito", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza un videojuego existente.
     * @param id El ID del videojuego a actualizar.
     * @param videojuego El videojuego con los datos actualizados.
     * @return El videojuego actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Videojuego videojuego) {
        try {
            Videojuego actualizado = service.actualizar(id, videojuego);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtiene un videojuego por su ID.
     * @param id El ID del videojuego a buscar.
     * @return El videojuego encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Videojuego videojuego = service.obtenerPorId(id);
            return new ResponseEntity<>(videojuego, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Lista todos los videojuegos.
     * @return Una lista con todos los videojuegos.
     */
    @GetMapping
    public ResponseEntity<List<Videojuego>> listarTodos() {
        List<Videojuego> videojuegos = service.listarTodos();
        return new ResponseEntity<>(videojuegos, HttpStatus.OK);
    }

    /**
     * Lista los videojuegos cuyo nombre contenga la cadena especificada.
     * @param nombre La cadena a buscar en el nombre de los videojuegos.
     * @return Una lista de videojuegos cuyo nombre contiene la cadena especificada.
     */
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Videojuego>> listarPorNombre(@PathVariable String nombre) {
        List<Videojuego> videojuegos = service.listarPorNombre(nombre);
        return new ResponseEntity<>(videojuegos, HttpStatus.OK);
    }
}

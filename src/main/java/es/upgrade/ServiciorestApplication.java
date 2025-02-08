package es.upgrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import es.upgrade.repositorios.VideojuegoRepository;

@SpringBootApplication
public class ServiciorestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciorestApplication.class, args);
	}

	// Nuevo: Muestra la cantidad de videojuegos en la base de datos para verificar la consulta
	@Bean
	public CommandLineRunner demo(VideojuegoRepository repo) {
		return args -> {
			System.out.println("Total Videojuegos almacenados: " + repo.count());
		};
	}
}

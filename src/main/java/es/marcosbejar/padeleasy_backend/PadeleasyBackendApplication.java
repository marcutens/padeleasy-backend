package es.marcosbejar.padeleasy_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("es.marcosbejar.padeleasy_backend")
public class PadeleasyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadeleasyBackendApplication.class, args);
	}

}

package cz.czechitas.java2webapps.ukol7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hlavní třída, která spouští celou aplikaci pomocí Spring Boot.
 * <p>
 * Anotace {@link SpringBootApplication} oznamuje Springu, že se jedná o aplikaci typu Spring Boot a má použít výchozí konfiguraci všude, kde je to možné.
 */
@SpringBootApplication
public class Application {
    /**
     * Hlavní metoda spouštějící celou aplikaci.
     *
     * @param args Argumenty z příkazové řádky při spuštění aplikace.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

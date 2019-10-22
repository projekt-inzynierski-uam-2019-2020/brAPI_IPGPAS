package org.brapi_igpas.servers;

import org.brapi_igpas.servers.model.Server;
import org.brapi_igpas.servers.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServersApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ServerRepository repository) {
        return args -> {
            repository.save(new Server("Breeding Api", "https://test-server.brapi.org/"));
            repository.save(new Server("App hosting server", "http://35.242.244.53:8080/"));
        };
    }

}

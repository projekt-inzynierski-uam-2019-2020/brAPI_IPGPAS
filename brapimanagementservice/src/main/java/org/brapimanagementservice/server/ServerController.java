package org.brapimanagementservice.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servers")
public class ServerController {

    private ServerRepository serverRepository;

    ServerController(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @GetMapping
    public List<Server> findAll() {
        return serverRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Server> findById(@PathVariable long id) {
        return serverRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Server create(@RequestBody Server server) {
        return serverRepository.save(server);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Server> update(@PathVariable long id, @RequestBody Server server) {
        return serverRepository.findById(id)
                .map(record -> {
                    record.setName(server.getName());
                    record.setDescription(server.getDescription());
                    record.setAddress(server.getAddress());
                    Server updated = serverRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return serverRepository.findById(id)
                .map(record -> {
                    serverRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}

package org.brapi_igpas.servers.controller;

import org.brapi_igpas.servers.model.Server;
import org.brapi_igpas.servers.repository.ServerRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/servers"})
public class TestController {

    @Autowired
    private ServerRepository serverRepository;

    @GetMapping
    public List<Server> findAll() {
        return serverRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Server> getServerById(@PathVariable(value = "id") Long Id)
            throws InvalidPropertiesFormatException {
        Server server = serverRepository.findById(Id)
                .orElseThrow(() -> new InvalidPropertiesFormatException("Employee not found for this id :: " + Id));
        return ResponseEntity.ok().body(server);
    }

    @PostMapping
    public Server create(@Valid @RequestBody Server server) {
        return serverRepository.save(server);
    }


    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteServer(@PathVariable(value = "id") Long Id)
            throws InvalidPropertiesFormatException {
        Server employee = serverRepository.findById(Id)
                .orElseThrow(() -> new InvalidPropertiesFormatException("Employee not found for this id :: " + Id));

        serverRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

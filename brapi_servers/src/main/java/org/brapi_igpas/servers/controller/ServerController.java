package org.brapi_igpas.servers.controller;

import org.brapi_igpas.servers.model.Server;
import org.brapi_igpas.servers.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/servers"})
public class ServerController {

    @Autowired
    private ServerService serverService;

    @PostMapping
    public Server create(@RequestBody Server server) {
        return serverService.create(server);
    }

    @PutMapping(path = {"/{id}"})
    public Server update(@PathVariable("id") int id, @RequestBody Server user) {
        user.setId(id);
        return serverService.update(user);
    }

    @GetMapping
    public List findAll() {
        return serverService.findAll();
    }
}

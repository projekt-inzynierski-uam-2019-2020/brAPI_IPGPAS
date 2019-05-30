package org.brapimanagementservice.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servers")
public class ServerController {

    private ServerRepository repo;

    ServerController(ServerRepository serverRepository) {
        this.repo = serverRepository;
    }

    @GetMapping
    public List findAll() {
        return repo.findAll();
    }
}

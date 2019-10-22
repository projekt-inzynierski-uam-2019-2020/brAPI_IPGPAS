package org.brapi_igpas.servers.service;

import org.brapi_igpas.servers.model.Server;
import org.brapi_igpas.servers.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public List findAll() {
        return serverRepository.findAll();
    }


    @Override
    public Server update(Server user) {
        return null;
    }


}

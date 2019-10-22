package org.brapi_igpas.servers.service;

import org.brapi_igpas.servers.model.Server;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServerService {

    public List findAll();

    public Server update(Server server);

    public Server create(Server server);

}

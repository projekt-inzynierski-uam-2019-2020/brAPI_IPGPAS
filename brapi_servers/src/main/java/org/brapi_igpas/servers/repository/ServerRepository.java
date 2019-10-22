package org.brapi_igpas.servers.repository;

import org.brapi_igpas.servers.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    List findAll();

    Server save(Server server);
}

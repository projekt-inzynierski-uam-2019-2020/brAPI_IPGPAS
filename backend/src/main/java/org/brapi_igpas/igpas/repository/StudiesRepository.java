package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.Studies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiesRepository extends CrudRepository<Studies, Long> {
}

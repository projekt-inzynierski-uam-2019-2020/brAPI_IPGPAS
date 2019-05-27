package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.Investigation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestigationRepository extends CrudRepository<Investigation, Long> {
    Investigation getInvestigationById(long id);
}

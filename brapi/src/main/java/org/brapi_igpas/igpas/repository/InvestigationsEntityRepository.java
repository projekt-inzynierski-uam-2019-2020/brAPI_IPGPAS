package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.InvestigationsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestigationsEntityRepository extends CrudRepository<InvestigationsEntity, Long> {
    Optional<InvestigationsEntity> getInvestigationById(long id);
}

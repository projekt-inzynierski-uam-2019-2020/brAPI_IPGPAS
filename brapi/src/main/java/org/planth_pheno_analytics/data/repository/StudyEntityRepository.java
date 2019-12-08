package org.planth_pheno_analytics.data.repository;

import org.planth_pheno_analytics.data.entity.StudyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyEntityRepository extends CrudRepository<StudyEntity, Integer> {
}

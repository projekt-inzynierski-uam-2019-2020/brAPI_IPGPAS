package org.planth_pheno_analytics.data.repository;

import org.planth_pheno_analytics.data.entity.AttributeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeEntityRepository extends CrudRepository<AttributeEntity, Integer> {
    @Query("select distinct a.id from AttributeEntity a where a.displayedName=(:displayedName)")
    Optional<Integer> getAttributeIdByDisplayedName(@Param("displayedName") String displayedName);
}

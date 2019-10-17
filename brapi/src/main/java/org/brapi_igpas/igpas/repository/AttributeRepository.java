package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.AttributesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeRepository extends CrudRepository<AttributesEntity, Integer> {
    Optional<AttributesEntity> getAttributeByDisplayedName(String displayedName);
}

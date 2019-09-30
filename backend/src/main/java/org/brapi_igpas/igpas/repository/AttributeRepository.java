package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.Attribute;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AttributeRepository extends CrudRepository<Attribute, Integer> {
    Optional<Attribute> getAttributeByDisplayedName(String displayedName);
}

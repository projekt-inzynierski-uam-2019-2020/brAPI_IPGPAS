package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.Attribute;
import org.springframework.data.repository.CrudRepository;

public interface AttributeRepository extends CrudRepository<Attribute, Integer> {
    Attribute getAttributeByDisplayedName(String displayedName);
}

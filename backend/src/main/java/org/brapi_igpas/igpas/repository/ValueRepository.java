package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValueRepository extends CrudRepository<Value, Long> {
    List<Value> getAllValuesByAttributeId(int attributeId);
    List<Value> getAllValuesByStudyId(long studyId);
}

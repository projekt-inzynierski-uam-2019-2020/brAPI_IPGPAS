package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValueRepository extends CrudRepository<ValuesEntity, Long> {
    List<ValuesEntity> getValuesByAttributeId(int attributeId);

    List<ValuesEntity> getValuesByStudyId(long studyId);

    Optional<ValuesEntity> getValueByStudyId(long studyId);
}

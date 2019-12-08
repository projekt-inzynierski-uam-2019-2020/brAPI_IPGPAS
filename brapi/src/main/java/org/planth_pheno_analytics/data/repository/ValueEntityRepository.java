package org.planth_pheno_analytics.data.repository;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValueEntityRepository extends CrudRepository<ValueEntity, Integer> {

    @Query("select distinct v.value from ValueEntity v join AttributeEntity a on v.attributeId = a.id where a.displayedName=(:displayedName)")
    Optional<String> getValueByAttributeDisplayedName(@Param("displayedName") String displayedName);

    @Query("select distinct v.value from ValueEntity v join AttributeEntity a on v.attributeId = a.id where a.displayedName=(:displayedName) and v.studyId=(:studyDbId)")
    Optional<String> getValueByAttributeDisplayedNameAndStudyId(@Param("displayedName") String displayedName, @Param("studyDbId") Integer studyDbId);

    @Query("select distinct v.value from ValueEntity v join AttributeEntity a on v.attributeId = a.id where a.displayedName=(:displayedName) and v.studyId=(:studyDbId)")
    List<String> getAllValuesByAttributeDisplayedNameAndStudyId(@Param("displayedName") String displayedName, @Param("studyDbId") Integer studyDbId);
}

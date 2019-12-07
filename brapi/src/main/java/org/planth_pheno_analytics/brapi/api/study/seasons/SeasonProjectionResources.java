package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonProjectionResources extends JpaRepository<ValueEntity, Integer>, JpaSpecificationExecutor<ValueEntity> {

    @Query(value = "select distinct on (v.value) value, v.id, v.study_id\n" +
            "from values v join attributes a on v.attribute_Id = a.id\n"+
            "where a.displayed_Name='Study start'",
            nativeQuery = true)
    List<SeasonProjection> getSeasons();

    @Query(value = "select distinct on (v.value) value, v.id, v.study_id\n" +
            "from values v join attributes a on v.attribute_Id = a.id\n"+
            "where a.displayed_Name='Study start' and v.study_id=(:studyDbId)",
            nativeQuery = true)
    List<SeasonProjection> getSeasonsByStudyId(@Param("studyDbId") Integer studyDbId);

}

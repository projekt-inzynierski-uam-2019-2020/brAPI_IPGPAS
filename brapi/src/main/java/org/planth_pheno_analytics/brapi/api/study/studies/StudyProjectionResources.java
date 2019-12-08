package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.data.entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyProjectionResources extends JpaRepository<StudyEntity, Integer> {
    @Query(value = "select s.id, s.title, s.description, s.investigation_id, i.title as trialName\n" +
            "from studies s join investigations i on s.investigation_id = i.id",
            nativeQuery = true)
    List<StudyProjection> getStudies();

    @Query(value = "select s.id, s.title, s.description, s.investigation_id, i.title as trialName\n" +
            "from studies s join investigations i on s.investigation_id = i.id\n"+
            "where s.investigation_id=(:trialDbId)",
            nativeQuery = true)
    List<StudyProjection> getStudiesWithTrialDbId(@Param("trialDbId") Integer trialDbId);
}
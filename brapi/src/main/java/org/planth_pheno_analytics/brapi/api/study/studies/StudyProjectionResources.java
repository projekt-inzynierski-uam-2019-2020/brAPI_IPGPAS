package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.api.germplasm.GermplasmProjection;
import org.planth_pheno_analytics.data.entity.StudyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyProjectionResources extends JpaRepository<StudyEntity, Integer> {
    @Query(value = "select s.id, s.title, s.description, s.investigation_id, i.title as trialName\n" +
            "from studies s join investigations i on s.investigation_id = i.id",
            nativeQuery = true)
    List<StudyProjection> getStudies();

    @Query(value = "select s.id, s.title, s.description, s.investigation_id, i.title as trialName\n" +
            "from studies s join investigations i on s.investigation_id = i.id where s.id=(:studyDbId)",
            nativeQuery = true)
    Optional<StudyProjection> getStudyByStudyDbId(@Param("studyDbId") Integer studyDbId);

    @Query(value = "select distinct on (v.value) value\n" +
            "from values v join attributes a on v.attribute_Id = a.id\n" +
            "where a.displayed_Name='Infraspecific name' and v.study_id=(:studyDbId)",
            countQuery = "select count(distinct v.value)\n" +
                    "from values v join attributes a on v.attribute_Id = a.id\n" +
                    "where a.displayed_Name='Infraspecific name' v.study_id=(:studyDbId)",
            nativeQuery = true)
    Page<GermplasmProjection> getStudiesGermplasmsPageByStudyDbId(@Param("studyDbId") Integer studyDbId, Pageable pageable);

    @Query(value = "select s.id, s.title, s.description, s.investigation_id, i.title as trialName\n" +
            "from studies s join investigations i on s.investigation_id = i.id\n"+
            "where s.investigation_id=(:trialDbId)",
            nativeQuery = true)
    List<StudyProjection> getStudiesWithTrialDbId(@Param("trialDbId") Integer trialDbId);
}

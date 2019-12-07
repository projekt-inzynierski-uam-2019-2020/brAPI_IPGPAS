package org.planth_pheno_analytics.brapi.api.germplasm;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GermplasmProjectionResources extends JpaRepository<ValueEntity, Integer>, JpaSpecificationExecutor<ValueEntity> {

    @Query(value = "select distinct on (v.value) value\n" +
            "from values v join attributes a on v.attribute_Id = a.id\n" +
            "where a.displayed_Name='Infraspecific name'",
            nativeQuery = true)
    List<GermplasmProjection> getGermplasms();

    @Query(value = "select distinct on (v.value) value\n" +
            "from values v join attributes a on v.attribute_Id = a.id\n" +
            "where a.displayed_Name='Infraspecific name' and v.study_id=(:studyDbId)",
            countQuery = "select count(distinct v.value)\n" +
                    "from values v join attributes a on v.attribute_Id = a.id\n" +
                    "where a.displayed_Name='Infraspecific name' v.study_id=(:studyDbId)",
            nativeQuery = true)
    Page<GermplasmProjection> getStudiesGermplasmsPageByStudyDbId(@Param("studyDbId") Integer studyDbId, Pageable pageable);
}

package org.planth_pheno_analytics.brapi.api.germplasm;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GermplasmProjectionResources extends JpaRepository<ValueEntity, Integer>, JpaSpecificationExecutor<ValueEntity> {

    @Query(value = "select distinct on (v.value) value\n" +
            "from values v join attributes a on v.attribute_Id = a.id\n" +
            "where a.displayed_Name='Infraspecific name'",
            nativeQuery = true)
    List<GermplasmProjection> getGermplasms();
}

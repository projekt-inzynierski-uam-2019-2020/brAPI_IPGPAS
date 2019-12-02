package org.planth_pheno_analytics.brapi.api.trials;

import org.planth_pheno_analytics.data.entity.InvestigationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrialProjectionResources extends JpaRepository<InvestigationEntity, Integer> {

    @Query(value = "select *\n" +
            "from investigations i",
            countQuery = "select count(*)\n" +
                    "from investigations i",
            nativeQuery = true)
    Page<TrialProjection> getPagedTrialProjections(Pageable pageable);

}

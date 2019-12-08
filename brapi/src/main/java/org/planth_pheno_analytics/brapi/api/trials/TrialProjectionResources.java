package org.planth_pheno_analytics.brapi.api.trials;

import org.planth_pheno_analytics.data.entity.InvestigationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrialProjectionResources extends JpaRepository<InvestigationEntity, Integer> {

    @Query(value = "select i.id, i.title, i.description, i.first_contact_name, i.submission_date from investigations i",
    nativeQuery = true)
    List<TrialProjection> getTrials();

}

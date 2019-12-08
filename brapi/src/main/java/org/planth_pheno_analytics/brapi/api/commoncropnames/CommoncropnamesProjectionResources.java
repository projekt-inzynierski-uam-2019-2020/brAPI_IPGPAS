package org.planth_pheno_analytics.brapi.api.commoncropnames;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommoncropnamesProjectionResources extends JpaRepository<ValueEntity, Integer> {

    @Query(value = "select distinct v.value\n" +
            "from ValueEntity v join AttributeEntity a on v.attributeId = a.id\n" +
            "where a.displayedName='Organism'\n",
            countQuery = "select count(distinct v.value)\n" +
                    "from ValueEntity v join AttributeEntity a on v.attributeId = a.id\n" +
                    "where a.displayedName='Organism'\n")
    Page<String> getPagedCommoncropnames(Pageable pageable);
}
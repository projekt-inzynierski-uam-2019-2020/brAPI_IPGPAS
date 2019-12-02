package org.planth_pheno_analytics.data.specifications;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ValueSpecifications {


    public static Specification<ValueEntity> getValuesByAttributeId(Integer attributeId) {
        return new Specification<ValueEntity>() {
            @Override
            public Predicate toPredicate(Root<ValueEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final Predicate predicate = cb.equal(root.get("attributeId"), attributeId);
                query.distinct(true);
                return predicate;
            }
        };
    }

}

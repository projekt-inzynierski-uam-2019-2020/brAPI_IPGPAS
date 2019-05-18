package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.PaginationUtils;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.igpas.entity.Attribute;
import org.brapi_igpas.igpas.entity.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CommoncropnamesDao {
    @PersistenceContext
    private EntityManager em;

    public BrApiDetailPayloadResponse getAll(int page, int pageSize){
        TypedQuery<Attribute> organismAttributeQuery = em.createQuery("select a from Attribute a where a.displayedName = 'Organism'", Attribute.class);
        int organismAttributeId = organismAttributeQuery.getSingleResult().getId();

        TypedQuery<Value> valuesQuery = em.createQuery("select v from Value v where v.attributeId = :id", Value.class);
        valuesQuery.setParameter("id", organismAttributeId);
        List<Value> values = valuesQuery.getResultList();

        List<String> commonCropNames = values.stream().map(Value::getValue).distinct().collect(Collectors.toCollection(ArrayList::new));

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(commonCropNames.size(), page, pageSize);

        int fromIndex = PaginationUtils.getFromIndex(commonCropNames.size(), page, pageSize);
        int numberOfCommonCropNames = PaginationUtils.getNumberOfElementsOnCurrentPage(commonCropNames.size(), page, pageSize);

        commonCropNames = commonCropNames
                .subList(fromIndex, commonCropNames.size())
                .stream()
                .limit(numberOfCommonCropNames)
                .collect(Collectors.toCollection(ArrayList::new));

        return new BrApiDetailPayloadResponse(commonCropNames, paginationInfo);
    }
}

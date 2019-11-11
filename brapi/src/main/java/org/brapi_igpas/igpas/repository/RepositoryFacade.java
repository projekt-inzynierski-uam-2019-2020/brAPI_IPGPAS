package org.brapi_igpas.igpas.repository;

import org.brapi_igpas.igpas.entity.AttributesEntity;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.brapi_igpas.igpas.repository.AttributesEntityRepository;
import org.brapi_igpas.igpas.repository.ValuesEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepositoryFacade {
    private final ValuesEntityRepository valuesEntityRepository;
    private final AttributesEntityRepository attributesEntityRepository;


    public RepositoryFacade(ValuesEntityRepository valuesEntityRepository, AttributesEntityRepository attributesEntityRepository) {
        this.valuesEntityRepository = valuesEntityRepository;
        this.attributesEntityRepository = attributesEntityRepository;
    }

    public List<ValuesEntity> getDistinctValuesEntitiesByAttributeDisplayedName(String displayedName) {
        Optional<AttributesEntity> attributesEntity = attributesEntityRepository.getAttributeByDisplayedName(displayedName);
        if (attributesEntity.isPresent()) {
            int attributeId = attributesEntity.get().getId();
            return valuesEntityRepository.getValuesByAttributeId(attributeId).stream().distinct()
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return Collections.emptyList();
    }
}

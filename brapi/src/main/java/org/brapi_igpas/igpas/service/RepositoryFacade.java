package org.brapi_igpas.igpas.service;

import org.brapi_igpas.igpas.entity.AttributesEntity;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.brapi_igpas.igpas.repository.AttributeRepository;
import org.brapi_igpas.igpas.repository.ValueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepositoryFacade {
    private final ValueRepository valueRepository;
    private final AttributeRepository attributeRepository;


    public RepositoryFacade(ValueRepository valueRepository, AttributeRepository attributeRepository) {
        this.valueRepository = valueRepository;
        this.attributeRepository = attributeRepository;
    }

    public List<ValuesEntity> getDistinctValuesByAttributeDisplayedName(String displayedName) {
        Optional<AttributesEntity> attributesEntity = attributeRepository.getAttributeByDisplayedName(displayedName);
        if (attributesEntity.isPresent()) {
            int attributeId = attributesEntity.get().getId();
            return valueRepository.getValuesByAttributeId(attributeId).stream().distinct()
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return Collections.emptyList();
    }
}

package org.brapi_igpas.igpas.service;

import org.brapi_igpas.igpas.entity.Attribute;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.repository.AttributeRepository;
import org.brapi_igpas.igpas.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DbValuesFacade {
    private final AttributeRepository attributeRepository;
    private final ValueRepository valueRepository;

    @Autowired
    public DbValuesFacade(AttributeRepository attributeRepository, ValueRepository valueRepository) {
        this.attributeRepository = attributeRepository;
        this.valueRepository = valueRepository;
    }

    public List<Value> getAllValuesByAttributeDisplayedName(String attributeDisplayedName) {
        Optional<Attribute> attribute = attributeRepository.getAttributeByDisplayedName(attributeDisplayedName);
        if (attribute.isPresent()) {
            final int attributeId = attribute.get().getId();
            return valueRepository.getAllValuesByAttributeId(attributeId);
        }
        return Collections.emptyList();
    }
}

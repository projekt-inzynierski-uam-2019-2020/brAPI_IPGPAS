package org.brapi_igpas.igpas.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValueEntityList {
    private List<ValuesEntity> values;

    public ValueEntityList(List<ValuesEntity> values) {
        this.values = values;
    }

    public void filterByAttributeId(int attributeId){
        values = values.stream().filter(v -> v.getAttributeId() == attributeId)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByStudyId(long studyId){
        values = values.stream().filter(v -> v.getStudyId() == studyId)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<ValuesEntity> getValuesEntityWithStudyId(long studyId){
        return values.stream().filter(v -> v.getStudyId() == studyId).findFirst();
    }

    public List<ValuesEntity> getValues() {
        return values;
    }
}

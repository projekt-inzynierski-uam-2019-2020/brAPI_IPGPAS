package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CommoncropnamesDaoImpl implements CommoncropnamesDao {
    private final DbValuesFacade dbValuesFacade;
    private List<Value> values;

    @Autowired
    public CommoncropnamesDaoImpl(DbValuesFacade dbValuesFacade) {
        this.dbValuesFacade = dbValuesFacade;
    }

    public String getCommonCropNameWithStudyId(long studyId) {
        values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Organism");
        Optional<Value> value = dbValuesFacade.getValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values);
        if (value.isPresent()) {
            return value.get().getValue();
        }
        return "";
    }

    public List<String> getAll() {
        values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Organism");
        return getCommonCropNamesFromValues(values);
    }

    private List<String> getCommonCropNamesFromValues(List<Value> values) {
        return values.stream().filter(v -> v.getValue() != null).map(Value::getValue).distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

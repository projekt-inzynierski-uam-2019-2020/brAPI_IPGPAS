package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.PaginationUtils;
import org.brapi_igpas.brapi.metadata.Pagination;
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
        this.values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Organism");
    }

    public String getCommonCropNameForStudyWithStudyId(long studyId) {
        Optional<Value> value = dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values);
        if (value.isPresent()) {
            return value.get().getValue();
        }
        return ""; // should have never happen
    }

    public BrApiDetailPayloadResponse getAll(int page, int pageSize) {
        List<String> commonCropNames = getValuesNames(values);

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(commonCropNames.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(commonCropNames.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(commonCropNames.size(), page, pageSize);
        commonCropNames = commonCropNames.subList(fromIndex, toIndex);

        return new BrApiDetailPayloadResponse(commonCropNames, paginationInfo);
    }

    private List<String> getValuesNames(List<Value> values) {
        return values.stream().filter(v -> v.getValue() != null).map(Value::getValue).distinct().collect(Collectors.toCollection(ArrayList::new));
    }
}

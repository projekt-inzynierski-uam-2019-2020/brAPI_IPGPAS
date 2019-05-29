package org.brapi_igpas.brapi.calls.germplasm.germplasm;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.PaginationUtils;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.FilterUtils.isParameterPresent;

@Repository
public class GermplasmDaoImpl implements GermplasmDao {
    private List<Value> values;

    @Autowired
    public GermplasmDaoImpl(DbValuesFacade dbValuesFacade) {
        this.values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Infraspecific name");
    }

    public BrApiDetailPayloadResponse getAll(String germplasmPUI, String germplasmDbId, String germplasmName, String commonCropName, int page, int pageSize) {
        List<Germplasm> germplasms = getGermplasmFromValues(values);

        if (isParameterPresent(germplasmPUI)) {
            germplasms = getGermplasmsWithGermplasmPUI(germplasms, germplasmPUI);
        }

        if (isParameterPresent(germplasmDbId)) {
            germplasms = getGermplasmsWithGermplasmDbId(germplasms, germplasmDbId);
        }

        if (isParameterPresent(germplasmName)) {
            germplasms = getGermplasmsWithGermplasmName(germplasms, germplasmName);
        }

        if (isParameterPresent(commonCropName)) {
            germplasms = getGermplasmsWithCommonCropName(germplasms, commonCropName);
        }

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(germplasms.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(germplasms.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(germplasms.size(), page, pageSize);
        germplasms = germplasms.subList(fromIndex, toIndex);

        return new BrApiDetailPayloadResponse(germplasms, paginationInfo);
    }

    private List<Germplasm> getGermplasmFromValues(List<Value> values) {
        return values.stream().map(value -> {
            Germplasm germplasm = new Germplasm();
            germplasm.setGermplasmDbId(String.valueOf(value.getId()));
            if (value.getValue() != null) {
                germplasm.setAccessionNumber(value.getValue());
                germplasm.setDefaultDisplayName(value.getValue());
                germplasm.setGermplasmName(value.getValue());
            }
            return germplasm;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Germplasm> getGermplasmsWithGermplasmPUI(List<Germplasm> germplasms, String germplasmPUI) {
        return germplasms.stream().filter(g -> g.getGermplasmPUI() != null && g.getGermplasmPUI().equals(germplasmPUI)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Germplasm> getGermplasmsWithGermplasmDbId(List<Germplasm> germplasms, String germplasmDbId) {
        return germplasms.stream().filter(g -> g.getGermplasmDbId() != null && g.getGermplasmDbId().equals(germplasmDbId)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Germplasm> getGermplasmsWithGermplasmName(List<Germplasm> germplasms, String germplasmName) {
        return germplasms.stream().filter(g -> g.getGermplasmName() != null && g.getGermplasmName().equals(germplasmName)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Germplasm> getGermplasmsWithCommonCropName(List<Germplasm> germplasms, String commonCropName) {
        return germplasms.stream().filter(g -> g.getCommonCropName() != null && g.getCommonCropName().equals(commonCropName)).collect(Collectors.toCollection(ArrayList::new));
    }
}

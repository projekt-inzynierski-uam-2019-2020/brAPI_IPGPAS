package org.brapi_igpas.brapi.calls.germplasm.germplasm;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.utils.FilterUtils.isParameterPresent;

@Service
public class GermplasmService {
    private GermplasmDao germplasmDao;

    public GermplasmService(GermplasmDao germplasmDao) {
        this.germplasmDao = germplasmDao;
    }

    public BrApiDetailResponse getBrApiDetailResponse(String germplasmPUI, String germplasmDbId, String germplasmName,
                                                      String commonCropName, int page, int pageSize) {
        List<Germplasm> germplasms = germplasmDao.getAll();

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

        return new BrApiDetailResponse(germplasms, paginationInfo);
    }

    private List<Germplasm> getGermplasmsWithGermplasmPUI(List<Germplasm> germplasms, String germplasmPUI) {
        return germplasms.stream().filter(g -> g.getGermplasmPUI() != null && g.getGermplasmPUI().equals(germplasmPUI))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Germplasm> getGermplasmsWithGermplasmDbId(List<Germplasm> germplasms, String germplasmDbId) {
        return germplasms.stream().filter(g -> g.getGermplasmDbId() != null && g.getGermplasmDbId().equals(germplasmDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Germplasm> getGermplasmsWithGermplasmName(List<Germplasm> germplasms, String germplasmName) {
        return germplasms.stream().filter(g -> g.getGermplasmName() != null && g.getGermplasmName().equals(germplasmName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Germplasm> getGermplasmsWithCommonCropName(List<Germplasm> germplasms, String commonCropName) {
        return germplasms.stream().filter(g -> g.getCommonCropName() != null && g.getCommonCropName().equals(commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

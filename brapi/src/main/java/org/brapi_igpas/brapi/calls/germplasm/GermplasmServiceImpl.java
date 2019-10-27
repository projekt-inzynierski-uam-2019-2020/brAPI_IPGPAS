package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class GermplasmServiceImpl implements GermplasmService {
    private final GermplasmDAO germplasmDAO;

    public GermplasmServiceImpl(GermplasmDAO germplasmDAO) {
        this.germplasmDAO = germplasmDAO;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(String germplasmPUI, String germplasmDbId, String germplasmName,
                                                      String commonCropName, int page, int pageSize) {
        GermplasmList germplasmList = new GermplasmList(germplasmDAO.getAll());

        if (isParameterPresent(germplasmPUI)) {
            germplasmList.filterByGermplasmPUI(germplasmPUI);
        }

        if (isParameterPresent(germplasmDbId)) {
            germplasmList.filterByGermplasmDbId(germplasmDbId);
        }

        if (isParameterPresent(germplasmName)) {
            germplasmList.filterByGermplasmName(germplasmName);
        }

        if (isParameterPresent(commonCropName)) {
            germplasmList.filterByCommonCropName(commonCropName);
        }

        Pagination paginationInfo = getPaginationInfo(germplasmList.size(), page, pageSize);
        List<Germplasm> germplasms = getPaginatedList(germplasmList.getList(), page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, germplasms);
    }
}

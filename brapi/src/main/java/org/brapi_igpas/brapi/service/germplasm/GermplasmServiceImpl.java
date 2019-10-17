package org.brapi_igpas.brapi.service.germplasm;

import org.brapi_igpas.brapi.dao.germplasm.GermplasmDAO;
import org.brapi_igpas.brapi.domain.calls.germplasm.Germplasm;
import org.brapi_igpas.brapi.domain.calls.germplasm.GermplasmList;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.domain.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class GermplasmServiceImpl implements GermplasmService{
    private GermplasmDAO germplasmDAO;

    public GermplasmServiceImpl(GermplasmDAO germplasmDAO) {
        this.germplasmDAO = germplasmDAO;
    }

    public BrApiDetailResponse getBrApiDetailResponse(String germplasmPUI, String germplasmDbId, String germplasmName,
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

        Pagination paginationInfo = getPaginationInfo(germplasmList.getSize(), page, pageSize);
        List<Germplasm> germplasms = getPaginatedList(germplasmList.getList(), page, pageSize);

        return new BrApiDetailResponse(paginationInfo, germplasms);
    }
}

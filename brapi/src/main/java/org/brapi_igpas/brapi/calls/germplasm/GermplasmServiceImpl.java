package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class GermplasmServiceImpl implements GermplasmService {
    private final GermplasmDAO germplasmDAO;
    private final GermplasmFilter germplasmFilter;
    private final PaginationService paginationService;

    public GermplasmServiceImpl(GermplasmDAO germplasmDAO, GermplasmFilter germplasmFilter, PaginationService paginationService) {
        this.germplasmDAO = germplasmDAO;
        this.germplasmFilter = germplasmFilter;
        this.paginationService = paginationService;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(String germplasmPUI, String germplasmDbId, String germplasmName,
                                                      String commonCropName, int page, int pageSize) {
        List<Germplasm> germplasms = germplasmDAO.getAll();

        if (isParameterPresent(germplasmPUI)) {
            germplasms = germplasmFilter.filterByGermplasmPUI(germplasms, germplasmPUI);
        }

        if (isParameterPresent(germplasmDbId)) {
            germplasms = germplasmFilter.filterByGermplasmDbId(germplasms, germplasmDbId);
        }

        if (isParameterPresent(germplasmName)) {
            germplasms = germplasmFilter.filterByGermplasmName(germplasms, germplasmName);
        }

        if (isParameterPresent(commonCropName)) {
            germplasms = germplasmFilter.filterByCommonCropName(germplasms, commonCropName);
        }

        Pagination paginationInfo = paginationService.getPagination(germplasms.size(), page, pageSize);
        germplasms = paginationService.paginateList(germplasms, page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, germplasms);
    }
}

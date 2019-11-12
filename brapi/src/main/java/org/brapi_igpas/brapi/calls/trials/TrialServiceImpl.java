package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class TrialServiceImpl implements TrialService {
    private final TrialDAO trialDAO;

    public TrialServiceImpl(TrialDAO trialDAO) {
        this.trialDAO = trialDAO;
    }

    @Override
    public BrAPIDetailResponse getBrAPIDetailResponse(String commonCropName, String programDbId, String locationDbId,
                                                      String active, String sortBy, String sortOrder, int page, int pageSize) {
        TrialList trialList = new TrialList(trialDAO.getAll());

        if (isParameterPresent(commonCropName)) {
            trialList.filterByCommonCropName(commonCropName);
        }

        if (isParameterPresent(programDbId)) {
            trialList.filterByProgramDbId(programDbId);
        }

        if (isParameterPresent(locationDbId)) {
            trialList.filterByLocationDbId(locationDbId);
        }

        if (isParameterPresent(active)) {
            trialList.filterByActive(active);
        }

        if (isParameterPresent(sortBy)) {
            trialList.sortBy(sortBy);
        }

        if (isParameterPresent(sortOrder)) {
            trialList.sortOrder(sortOrder);
        }

        Pagination paginationInfo = getPaginationInfo(trialList.size(), page, pageSize);
        List<Trial> trials = getPaginatedList(trialList.getList(), page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, trials);
    }
}

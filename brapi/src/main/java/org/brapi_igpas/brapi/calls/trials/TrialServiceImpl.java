package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class TrialServiceImpl implements TrialService {
    private final TrialDAO trialDAO;
    private final TrialFilter trialFilter;
    private final TrialSorter trialSorter;
    private final PaginationService paginationService;

    public TrialServiceImpl(TrialDAO trialDAO, TrialFilter trialFilter, TrialSorter trialSorter,
                            PaginationService paginationService) {
        this.trialDAO = trialDAO;
        this.trialFilter = trialFilter;
        this.trialSorter = trialSorter;
        this.paginationService = paginationService;
    }

    @Override
    public BrAPIDetailResponse getBrAPIDetailResponse(String commonCropName, String programDbId, String locationDbId,
                                                      String active, String sortBy, String sortOrder, int page, int pageSize) {
        List<Trial> trials = trialDAO.getAll();

        if (isParameterPresent(commonCropName)) {
            trials = trialFilter.filterByCommonCropName(trials, commonCropName);
        }

        if (isParameterPresent(programDbId)) {
            trials = trialFilter.filterByProgramDbId(trials, programDbId);
        }

        if (isParameterPresent(locationDbId)) {
            trials = trialFilter.filterByLocationDbId(trials, locationDbId);
        }

        if (isParameterPresent(active)) {
            trials = trialFilter.filterByActive(trials, active);
        }

        if (isParameterPresent(sortBy)) {
            trials = trialSorter.sortBy(trials, sortBy);
        }

        if (isParameterPresent(sortOrder)) {
            trials = trialSorter.sortOrder(trials, sortOrder);
        }

        Pagination paginationInfo = paginationService.getPagination(trials.size(), page, pageSize);
        trials = paginationService.paginateList(trials, page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, trials);
    }
}

package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class StudyServiceImpl implements StudyService {
    private final StudyDAO studyDAO;
    private final StudyFilter studyFilter;
    private final StudySorter studySorter;
    private final PaginationService paginationService;

    public StudyServiceImpl(StudyDAO studyDAO, StudyFilter studyFilter, StudySorter studySorter,
                            PaginationService paginationService) {
        this.studyDAO = studyDAO;
        this.studyFilter = studyFilter;
        this.studySorter = studySorter;
        this.paginationService = paginationService;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(String commonCropName, String studyTypeDbId, String programDbId,
                                                      String locationDbId, String seasonDbId, String trialDbId,
                                                      String studyDbId, String active, String sortBy,
                                                      String sortOrder, int page, int pageSize) {
        List<Study> studies = studyDAO.getAll();

        if (isParameterPresent(commonCropName)) {
            studies = studyFilter.filterByCommonCropName(studies, commonCropName);
        }

        if (isParameterPresent(studyTypeDbId)) {
            studies = studyFilter.filterByStudyTypeDbId(studies, studyTypeDbId);
        }

        if (isParameterPresent(programDbId)) {
            studies = studyFilter.filterByProgramDbId(studies, programDbId);
        }

        if (isParameterPresent(locationDbId)) {
            studies = studyFilter.filterByLocationDbId(studies, locationDbId);
        }

        if (isParameterPresent(seasonDbId)) {
            studies = studyFilter.filterBySeasonDbId(studies, seasonDbId);
        }

        if (isParameterPresent(trialDbId)) {
            studies = studyFilter.filterByTrialDbId(studies, trialDbId);
        }

        if (isParameterPresent(studyDbId)) {
            studies = studyFilter.filterByStudyDbId(studies, studyDbId);
        }

        if (isParameterPresent(active)) {
            studies = studyFilter.filterByActive(studies, active);
        }

        if (isParameterPresent(sortBy)) {
            studies = studySorter.sortBy(studies, sortBy);
        }

        if (isParameterPresent(sortOrder)) {
            studies = studySorter.sortOrder(studies, sortOrder);
        }

        Pagination paginationInfo = paginationService.getPagination(studies.size(), page, pageSize);
        studies = paginationService.paginateList(studies, page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, studies);
    }

    // TODO IMPLEMENT
    @Override
    public BrAPIDetailResponse getBrAPIDetailResponse(String studyDbId, int page, int pageSize) {

        return null;
    }


}

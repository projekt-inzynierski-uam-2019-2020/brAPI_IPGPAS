package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class StudyServiceImpl implements StudyService {
    private final StudyDAO studyDAO;

    public StudyServiceImpl(StudyDAO studyDAO) {
        this.studyDAO = studyDAO;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(String commonCropName, String studyTypeDbId, String programDbId,
                                                      String locationDbId, String seasonDbId, String trialDbId,
                                                      String studyDbId, String active, String sortBy,
                                                      String sortOrder, int page, int pageSize) {
        StudyList studyList = new StudyList(studyDAO.getAll());

        if (isParameterPresent(commonCropName)) {
            studyList.filterByCommonCropName(commonCropName);
        }

        if (isParameterPresent(studyTypeDbId)) {
            studyList.filterByStudyTypeDbId(studyTypeDbId);
        }

        if (isParameterPresent(programDbId)) {
            studyList.filterByProgramDbId(programDbId);
        }

        if (isParameterPresent(locationDbId)) {
            studyList.filterByLocationDbId(locationDbId);
        }

        if (isParameterPresent(seasonDbId)) {
            studyList.filterBySeasonDbId(seasonDbId);
        }

        if (isParameterPresent(trialDbId)) {
            studyList.filterByTrialDbId(trialDbId);
        }

        if (isParameterPresent(studyDbId)) {
            studyList.filterByStudyDbId(studyDbId);
        }

        if (isParameterPresent(active)) {
            studyList.filterByActive(active);
        }

        if (isParameterPresent(sortBy)) {
            studyList.sortBy(sortBy);
        }

        if (isParameterPresent(sortOrder)) {
            studyList.sortOrder(sortOrder);
        }

        Pagination paginationInfo = getPaginationInfo(studyList.size(), page, pageSize);
        List<Study> studies = getPaginatedList(studyList.getList(), page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, studies);
    }

    // TODO IMPLEMENT
    @Override
    public BrAPIDetailResponse getBrAPIDetailResponse(String studyDbId, int page, int pageSize) {
        StudyList studyList = new StudyList(studyDAO.getAll());
        studyList.filterByStudyDbId(studyDbId);

        return null;
    }


}

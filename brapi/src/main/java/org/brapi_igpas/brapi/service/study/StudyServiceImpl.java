package org.brapi_igpas.brapi.service.study;

import org.brapi_igpas.brapi.dao.study.StudyDAO;
import org.brapi_igpas.brapi.domain.calls.study.Study;
import org.brapi_igpas.brapi.domain.calls.study.StudyList;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.domain.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class StudyServiceImpl implements StudyService {
    private StudyDAO studyDAO;

    public StudyServiceImpl(StudyDAO studyDAO) {
        this.studyDAO = studyDAO;
    }

    public BrApiDetailResponse getStudiesBrApiDetailResponse(String commonCropName, String studyTypeDbId, String programDbId,
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
            studyList.orderBySortOrder(sortOrder);
        }

        Pagination paginationInfo = getPaginationInfo(studyList.getSize(), page, pageSize);
        List<Study> studies = getPaginatedList(studyList.getList(), page, pageSize);

        return new BrApiDetailResponse(paginationInfo, studies);
    }


    // TODO IMPLEMENT
    @Override
    public BrApiDetailResponse getStudiesBrApiDetailResponse(String studyDbId, int page, int pageSize) {
        StudyList studyList = new StudyList(studyDAO.getAll());
        studyList.filterByStudyDbId(studyDbId);

        return null;
    }


}

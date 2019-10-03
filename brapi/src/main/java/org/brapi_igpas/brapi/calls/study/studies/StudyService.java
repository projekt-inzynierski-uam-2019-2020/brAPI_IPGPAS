package org.brapi_igpas.brapi.calls.study.studies;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static org.brapi_igpas.brapi.utils.FilterUtils.isParameterPresent;

@Service
public class StudyService {
    private StudyDao studyDao;

    public StudyService(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public BrApiDetailResponse getBrApiDetailResponse(String commonCropName, String studyTypeDbId, String programDbId,
                                                      String locationDbId, String seasonDbId, String trialDbId,
                                                      String studyDbId, String active, String sortBy,
                                                      String sortOrder, int page, int pageSize) {
        List<Study> studies = studyDao.getAll();

        if (isParameterPresent(commonCropName)) {
            studies = getStudiesWithCommonCropName(studies, commonCropName);
        }

        if (isParameterPresent(studyTypeDbId)) {
            studies = getStudiesWithStudyTypeDbId(studies, studyTypeDbId);
        }

        if (isParameterPresent(programDbId)) {
            studies = getStudiesWithProgramDbId(studies, programDbId);
        }

        if (isParameterPresent(locationDbId)) {
            studies = getStudiesWithLocationDbId(studies, locationDbId);
        }

        if (isParameterPresent(seasonDbId)) {
            studies = getStudiesWithSeasonDbId(studies, seasonDbId);
        }

        if (isParameterPresent(trialDbId)) {
            studies = getStudiesWithTrialDbId(studies, trialDbId);
        }

        if (isParameterPresent(studyDbId)) {
            studies = getStudiesWithStudyDbId(studies, studyDbId);
        }

        if (isParameterPresent(active)) {
            studies = getStudiesWithActive(studies, active);
        }

        if (isParameterPresent(sortBy)) {
            studies = getSortedStudies(studies, sortBy);
        }

        if (isParameterPresent(sortOrder)) {
            orderStudiesBySortOrder(studies, sortOrder);
        }

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(studies.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(studies.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(studies.size(), page, pageSize);
        studies = studies.subList(fromIndex, toIndex);

        return new BrApiDetailResponse(studies, paginationInfo);
    }

    private List<Study> getStudiesWithCommonCropName(List<Study> studies, String commonCropName) {
        return studies.stream().filter(s -> s.getCommonCropName() != null && s.getCommonCropName().equals(commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithStudyTypeDbId(List<Study> studies, String studyTypeDbId) {
        return studies.stream().filter(s -> s.getStudyTypeDbId() != null && s.getStudyTypeDbId().equals(studyTypeDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithProgramDbId(List<Study> studies, String programDbId) {
        return studies.stream().filter(s -> s.getProgramDbId() != null && s.getProgramDbId().equals(programDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithLocationDbId(List<Study> studies, String locationDbId) {
        return studies.stream().filter(s -> s.getLocationDbId() != null && s.getLocationDbId().equals(locationDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithSeasonDbId(List<Study> studies, String seasonDbId) {
        return studies.stream().filter(study -> study.getSeasons().stream().anyMatch(season -> season.getSeasonDbId().equals(seasonDbId)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithTrialDbId(List<Study> studies, String trialDbId) {
        return studies.stream().filter(s -> s.getTrialDbId() != null && s.getTrialDbId().equals(trialDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithStudyDbId(List<Study> studies, String studyDbId) {
        return studies.stream().filter(s -> s.getStudyDbId() != null && s.getStudyDbId().equals(studyDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithActive(List<Study> studies, String active) {
        return studies.stream().filter(s -> s.getActive() != null && s.getActive().equals(active))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getSortedStudies(List<Study> studies, String sortBy) {
        switch (sortBy) {
            case "studyDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getStudyDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "trialDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getTrialDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "locationDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getLocationDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "programDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getProgramDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "studyTypeDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getStudyTypeDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "studyName":
                studies.sort(nullsFirst(comparing(
                        Study::getStudyName, nullsFirst(naturalOrder())
                )));
                break;
            case "locationName":
                studies.sort(nullsFirst(comparing(
                        Study::getLocationName, nullsFirst(naturalOrder())
                )));
                break;
            case "programName":
                studies.sort(nullsFirst(comparing(
                        Study::getProgramName, nullsFirst(naturalOrder())
                )));
                break;
        }
        return studies;
    }

    private void orderStudiesBySortOrder(List<Study> studies, String sortOrder) {
        if (sortOrder.equalsIgnoreCase("DESC")) {
            Collections.reverse(studies);
        }
    }
}

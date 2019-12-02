package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.germplasm.Germplasm;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/studies")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPIStudies(@Valid StudyFilterCriteria filterCriteria, Pageable pageable) {
        final Page<Study> resultPage = studyService.getPagedStudies(pageable);

        final Pagination pagination = Pagination.of(resultPage);
        final List<Study> resultData = resultPage.getContent();

        return new BrAPIDetailResponse(pagination, resultData);
    }

    @GetMapping("/studies/{studyDbId}/germplasm")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPIStudiesGermplasmsByStudyId(@PathVariable("studyDbId") String studyDbId, Pageable pageable) {
        final Page<Germplasm> resultPage = studyService.getPagedStudiesGermplasmsByStudyId(studyDbId, pageable);

        final Pagination pagination = Pagination.of(resultPage);
        final List<Germplasm> resultData = resultPage.getContent();

        return new BrAPIDetailResponse(pagination, resultData);
    }

    /*
    @GetMapping("/studies/{studyDbId}/observationvariables")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIDetailResponse getBrAPIObservationvariablesByStudyDbId(
            @RequestParam(value = "studyDbId") final String studyDbId,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return studyPageService.createBrAPIResponse(studyDbId, page, pageSize);
    }
    */
}

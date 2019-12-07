package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.planth_pheno_analytics.brapi.api.germplasm.Germplasm;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.planth_pheno_analytics.brapi.api.response.BrAPIMasterDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@BrAPIController
public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/studies")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPIStudies(@Valid StudyCriteria studyCriteria,
                                         @Valid PaginationCriteria paginationCriteria) {
        final List<Study> filteredData = studyService.getFilteredStudies(studyCriteria);
        return new BrAPIDetailResponse(filteredData, paginationCriteria.getPage(), paginationCriteria.getPageSize());
    }

    @GetMapping("/studies/{studyDbId}/germplasm")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPIStudiesGermplasmsByStudyDbId(@PathVariable("studyDbId") String studyDbId, Pageable pageable) {
        final Page<Germplasm> resultPage = studyService.getPagedStudiesGermplasmsByStudyDbId(studyDbId, pageable);
        return new BrAPIDetailResponse(resultPage);
    }

    @GetMapping("/studies/{studyDbId}/table")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPIStudiesTableByStudyDbId(@PathVariable("studyDbId") String studyDbId,
                                                         @RequestParam("format") String format) {
        final Map<String, List<?>> result = studyService.getStudiesTableResult(studyDbId, format);
        return new BrAPIMasterDetailResponse(result);
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

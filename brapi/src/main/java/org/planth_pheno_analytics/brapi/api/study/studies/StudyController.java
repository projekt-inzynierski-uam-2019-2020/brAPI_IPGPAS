package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.utils.ResponseCreator;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.planth_pheno_analytics.brapi.api.criteria.SortCriteria;
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
    public BrAPIResponse getBrAPIStudies(@Valid StudyCriteria studyCriteria, @Valid PaginationCriteria paginationCriteria,
                                         @Valid SortCriteria sortCriteria) {
        final List<Study> filteredData = studyService.getFilteredAndSortedStudies(studyCriteria, sortCriteria);
        return ResponseCreator.createBrAPIResponse(filteredData, paginationCriteria);
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
                                                         @RequestParam(name = "format", required = false) String format) {
        final Map<String, List<?>> result = studyService.getStudiesTable(studyDbId, format);
        return new BrAPIMasterDetailResponse(result);
    }
}

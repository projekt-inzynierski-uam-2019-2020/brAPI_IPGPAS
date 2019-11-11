package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.brapi.annotation.BrAPIController;
import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@BrAPIController
public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/studies")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIDetailResponse getBrAPIStudies(
            @RequestParam(value = "commonCropName", required = false) final String commonCropName,
            @RequestParam(value = "studyTypeDbId", required = false) final String studyTypeDbId,
            @RequestParam(value = "programDbId", required = false) final String programDbId,
            @RequestParam(value = "locationDbId", required = false) final String locationDbId,
            @RequestParam(value = "seasonDbId", required = false) final String seasonDbId,
            @RequestParam(value = "trialDbId", required = false) final String trialDbId,
            @RequestParam(value = "studyDbId", required = false) final String studyDbId,
            @RequestParam(value = "active", required = false) final String active,
            @RequestParam(value = "sortBy", required = false) final String sortBy,
            @RequestParam(value = "sortOrder", required = false) final String sortOrder,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return studyService.getBrAPIDetailResponse(commonCropName, studyTypeDbId, programDbId, locationDbId, seasonDbId, trialDbId,
                studyDbId, active, sortBy, sortOrder, page, pageSize);
    }

    @GetMapping("/studies/{studyDbId}/observationvariables")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIDetailResponse getBrAPIObservationvariablesByStudyDbId(
            @RequestParam(value = "studyDbId") final String studyDbId,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return studyService.getBrAPIDetailResponse(studyDbId, page, pageSize);
    }
}

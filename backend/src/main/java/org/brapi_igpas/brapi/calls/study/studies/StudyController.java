package org.brapi_igpas.brapi.calls.study.studies;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.exceptions.InvalidNumericalParameterValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brapi/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class StudyController {
    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/studies")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BrApiDetailResponse getBrApiDetailResponse(
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
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000") final int pageSize) {
        if (page < 0) {
            throw new InvalidNumericalParameterValueException("page");
        }
        if (pageSize <= 0) {
            throw new InvalidNumericalParameterValueException("pageSize");
        }
        return studyService.getBrApiDetailResponse(commonCropName, studyTypeDbId, programDbId, locationDbId, seasonDbId, trialDbId,
                studyDbId, active, sortBy, sortOrder, page, pageSize);
    }
}

package org.brapi_igpas.brapi.calls.study.studies;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.exceptions.InvalidPageSizeValueException;
import org.brapi_igpas.brapi.exceptions.InvalidPageValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brapi/v1")
public class StudyController {
    private final StudyDao studyDao;

    public StudyController(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    @GetMapping("/studies")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BrApiDetailPayloadResponse getAll(
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
            throw new InvalidPageValueException();
        }
        if (pageSize <= 0) {
            throw new InvalidPageSizeValueException();
        }
        return studyDao.getAll(commonCropName, studyTypeDbId, programDbId, locationDbId, seasonDbId, trialDbId,
                studyDbId, active, sortBy, sortOrder, page, pageSize);
    }
}

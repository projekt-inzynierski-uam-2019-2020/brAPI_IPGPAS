package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.brapi.annotation.BrAPIController;
import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Min;

@BrAPIController
public class TrialController {
    private final TrialService trialService;

    public TrialController(TrialService trialService) {
        this.trialService = trialService;
    }

    @GetMapping("/trials")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIDetailResponse getBrAPITrials(
            @RequestParam(value = "commonCropName", required = false) final String commonCropName,
            @RequestParam(value = "programDbId", required = false) final String programDbId,
            @RequestParam(value = "locationDbId", required = false) final String locationDbId,
            @RequestParam(value = "active", required = false) final String active,
            @RequestParam(value = "sortBy", required = false) final String sortBy,
            @RequestParam(value = "sortOrder", required = false) final String sortOrder,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return trialService.getBrAPIDetailResponse(commonCropName, programDbId, locationDbId, active, sortBy, sortOrder, page, pageSize);
    }
}

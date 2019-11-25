package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.annotation.BrAPIController;
import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@BrAPIController
public class CallController {
    private final CallServiceImpl callService;

    public CallController(CallServiceImpl callService) {
        this.callService = callService;
    }

    @GetMapping("/calls")
    public BrAPIDetailResponse getBrAPICalls(
            @RequestParam(value = "dataType", required = false) final String dataType,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return callService.getBrAPIDetailResponse(dataType, page, pageSize);
    }
}

package org.brapi_igpas.brapi.controller;

import org.brapi_igpas.brapi.controller.annotation.BrAPIController;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.service.call.CallService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@BrAPIController
public class CallController {
    private final CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/calls")
    public BrApiDetailResponse getBrApiDetailResponse(
            @RequestParam(value = "dataType", required = false) final String dataType,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return callService.getBrApiDetailResponse(dataType, page, pageSize);
    }
}

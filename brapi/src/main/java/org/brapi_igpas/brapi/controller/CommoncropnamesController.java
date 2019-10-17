package org.brapi_igpas.brapi.controller;

import org.brapi_igpas.brapi.controller.annotation.BrAPIController;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.service.commoncropnames.CommoncropnamesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@BrAPIController
public class CommoncropnamesController {
    private final CommoncropnamesService commoncropnamesService;

    public CommoncropnamesController(CommoncropnamesService commoncropnamesService) {
        this.commoncropnamesService = commoncropnamesService;
    }

    @GetMapping("/commoncropnames")
    @ResponseStatus(HttpStatus.OK)
    public BrApiDetailResponse getBrApiDetailResponse(
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return commoncropnamesService.getBrApiDetailResponse(page, pageSize);
    }
}

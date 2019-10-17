package org.brapi_igpas.brapi.controller;

import org.brapi_igpas.brapi.controller.annotation.BrAPIController;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.service.season.SeasonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@BrAPIController
public class SeasonController {
    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("/seasons")
    @ResponseStatus(HttpStatus.OK)
    public BrApiDetailResponse getBrApiDetailResponse(
            @RequestParam(value = "seasonDbId", required = false) final String seasonDbId,
            @RequestParam(value = "season", required = false) final String season,
            @RequestParam(value = "year", required = false) final String year,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return seasonService.getBrApiDetailResponse(seasonDbId, season, year, page, pageSize);
    }
}

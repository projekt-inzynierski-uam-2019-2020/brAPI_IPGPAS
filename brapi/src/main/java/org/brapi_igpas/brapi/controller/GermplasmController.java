package org.brapi_igpas.brapi.controller;

import org.brapi_igpas.brapi.controller.annotation.BrAPIController;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.service.germplasm.GermplasmService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@BrAPIController
public class GermplasmController {
    private final GermplasmService germplasmService;

    public GermplasmController(GermplasmService germplasmService) {
        this.germplasmService = germplasmService;
    }

    @GetMapping("/germplasm")
    @ResponseStatus(HttpStatus.OK)
    public BrApiDetailResponse getBrApiDetailResponse(
            @RequestParam(value = "germplasmPUI", required = false) final String germplasmPUI,
            @RequestParam(value = "germplasmDbId", required = false) final String germplasmDbId,
            @RequestParam(value = "germplasmName", required = false) final String germplasmName,
            @RequestParam(value = "commonCropName", required = false) final String commonCropName,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return germplasmService.getBrApiDetailResponse(germplasmPUI, germplasmDbId, germplasmName, commonCropName, page, pageSize);
    }
}

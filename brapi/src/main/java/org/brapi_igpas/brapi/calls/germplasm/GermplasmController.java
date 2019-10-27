package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.brapi.annotation.BrAPIController;
import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
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
    public BrAPIDetailResponse getBrAPIGermplasm(
            @RequestParam(value = "germplasmPUI", required = false) final String germplasmPUI,
            @RequestParam(value = "germplasmDbId", required = false) final String germplasmDbId,
            @RequestParam(value = "germplasmName", required = false) final String germplasmName,
            @RequestParam(value = "commonCropName", required = false) final String commonCropName,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "'page' value is invalid.") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000")
            @Min(value = 1, message = "'pageSize' value is invalid.") final int pageSize) {
        return germplasmService.getBrAPIDetailResponse(germplasmPUI, germplasmDbId, germplasmName, commonCropName, page, pageSize);
    }
}

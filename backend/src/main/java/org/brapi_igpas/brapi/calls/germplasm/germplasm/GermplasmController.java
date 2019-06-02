package org.brapi_igpas.brapi.calls.germplasm.germplasm;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.exceptions.InvalidNumericalParameterValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brapi/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class GermplasmController {
    private final GermplasmService germplasmService;

    public GermplasmController(GermplasmService germplasmService) {
        this.germplasmService = germplasmService;
    }

    @GetMapping("/germplasm")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BrApiDetailResponse getBrApiDetailResponse(
            @RequestParam(value = "germplasmPUI", required = false) final String germplasmPUI,
            @RequestParam(value = "germplasmDbId", required = false) final String germplasmDbId,
            @RequestParam(value = "germplasmName", required = false) final String germplasmName,
            @RequestParam(value = "commonCropName", required = false) final String commonCropName,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000") final int pageSize) {
        if (page < 0) {
            throw new InvalidNumericalParameterValueException("page");
        }
        if (pageSize <= 0) {
            throw new InvalidNumericalParameterValueException("pageSize");
        }
        return germplasmService.getBrApiDetailResponse(germplasmPUI, germplasmDbId, germplasmName, commonCropName, page, pageSize);
    }
}

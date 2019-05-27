package org.brapi_igpas.brapi.calls.germplasm.germplasm;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.exceptions.InvalidPageSizeValueException;
import org.brapi_igpas.brapi.exceptions.InvalidPageValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brapi/v1")
public class GermplasmController {
    private final GermplasmDao germplasmDao;

    public GermplasmController(GermplasmDao germplasmDao) {
        this.germplasmDao = germplasmDao;
    }

    @GetMapping("/germplasm")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BrApiDetailPayloadResponse getAll(
            @RequestParam(value = "germplasmPUI", required = false) final String germplasmPUI,
            @RequestParam(value = "germplasmDbId", required = false) final String germplasmDbId,
            @RequestParam(value = "germplasmName", required = false) final String germplasmName,
            @RequestParam(value = "commonCropName", required = false) final String commonCropName,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000") final int pageSize) {
        if (page < 0) {
            throw new InvalidPageValueException();
        }
        if (pageSize <= 0) {
            throw new InvalidPageSizeValueException();
        }
        return germplasmDao.getAll(germplasmPUI, germplasmDbId, germplasmName, commonCropName, page, pageSize);
    }
}

package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.exceptions.InvalidNumericalParameterValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brapi/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class CallController {
    private final CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/calls")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BrApiDetailResponse getBrApiDetailResponse(
            @RequestParam(value = "dataType", required = false) final String dataType,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000") final int pageSize) {
        if (page < 0) {
            throw new InvalidNumericalParameterValueException("page");
        }
        if (pageSize <= 0) {
            throw new InvalidNumericalParameterValueException("pageSize");
        }
        return callService.getBrApiDetailResponse(dataType, page, pageSize);
    }
}

package org.brapi_igpas.api.calls;

import org.brapi_igpas.api.BrApiDetailPayloadResponse;
import org.brapi_igpas.api.exceptions.InvalidPageSizeValueException;
import org.brapi_igpas.api.exceptions.InvalidPageValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brapi/v1")
public class CallController {
    private CallDao callDao;

    public CallController(CallDao callDao){
        this.callDao = callDao;
    }

    @GetMapping("/calls")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BrApiDetailPayloadResponse getAll(
            @RequestParam(value = "dataType", required=false) final String dataType,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000") final int pageSize){
        if (page < 0){ throw new InvalidPageValueException(); }
        if (pageSize < 0){ throw new InvalidPageSizeValueException(); }
        return callDao.getAll(dataType, page, pageSize);
    }
}

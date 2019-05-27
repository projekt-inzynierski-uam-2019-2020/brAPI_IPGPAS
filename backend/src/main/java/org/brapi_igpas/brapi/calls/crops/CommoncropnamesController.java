package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.exceptions.InvalidPageSizeValueException;
import org.brapi_igpas.brapi.exceptions.InvalidPageValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brapi/v1")
public class CommoncropnamesController {
    private final CommoncropnamesDao commoncropnamesDao;

    public CommoncropnamesController(CommoncropnamesDao commoncropnamesDao) {
        this.commoncropnamesDao = commoncropnamesDao;
    }

    @GetMapping("/commoncropnames")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BrApiDetailPayloadResponse getAll(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000") final int pageSize) {
        if (page < 0) {
            throw new InvalidPageValueException();
        }
        if (pageSize <= 0) {
            throw new InvalidPageSizeValueException();
        }
        return commoncropnamesDao.getAll(page, pageSize);
    }
}

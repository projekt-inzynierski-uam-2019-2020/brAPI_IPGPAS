package org.brapi_igpas.brapi.calls.study.seasons;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.exceptions.InvalidPageSizeValueException;
import org.brapi_igpas.brapi.exceptions.InvalidPageValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brapi/v1")
public class SeasonController {
    private SeasonDao seasonDao;

    public SeasonController(SeasonDao seasonDao) {
        this.seasonDao = seasonDao;
    }

    @GetMapping("/seasons")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    BrApiDetailPayloadResponse getAll(
            @RequestParam(value = "seasonDbId", required = false) final String seasonDbId,
            @RequestParam(value = "season", required = false) final String season,
            @RequestParam(value = "year", required = false) final String year,
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "pageSize", defaultValue = "1000") final int pageSize) {
        if (page < 0) {
            throw new InvalidPageValueException();
        }
        if (pageSize <= 0) {
            throw new InvalidPageSizeValueException();
        }
        return seasonDao.getAll(seasonDbId, season, year, page, pageSize);
    }
}

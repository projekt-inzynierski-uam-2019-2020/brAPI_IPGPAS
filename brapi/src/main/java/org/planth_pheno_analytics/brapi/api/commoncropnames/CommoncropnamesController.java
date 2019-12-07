package org.planth_pheno_analytics.brapi.api.commoncropnames;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@BrAPIController
public class CommoncropnamesController {
    private final CommoncropnamesProjectionResources commoncropnamesProjectionResources;

    public CommoncropnamesController(CommoncropnamesProjectionResources commoncropnamesProjectionResources) {
        this.commoncropnamesProjectionResources = commoncropnamesProjectionResources;
    }

    @GetMapping("/commoncropnames")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPICommoncropnames(Pageable pageable) {
        final Page<String> resultPage = commoncropnamesProjectionResources.getPagedCommoncropnames(pageable);
        return new BrAPIDetailResponse(resultPage);
    }
}
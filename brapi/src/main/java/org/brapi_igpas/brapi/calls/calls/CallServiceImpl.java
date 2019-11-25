package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class CallServiceImpl implements CallService {
    private final CallDAO callDAO;
    private final CallFilter callFilter;
    private final PaginationService paginationService;

    public CallServiceImpl(CallDAO callDAO, CallFilter callFilter, PaginationService paginationService) {
        this.callDAO = callDAO;
        this.callFilter = callFilter;
        this.paginationService = paginationService;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(String dataType, int page, int pageSize) {
        List<Call> calls = callDAO.getAll();

        if (isParameterPresent(dataType)) {
            calls = callFilter.filterByDataType(calls, dataType);
        }

        Pagination pagination = paginationService.getPagination(calls.size(), page, pageSize);
        calls = paginationService.paginateList(calls, page, pageSize);

        return new BrAPIDetailResponse(pagination, calls);
    }
}

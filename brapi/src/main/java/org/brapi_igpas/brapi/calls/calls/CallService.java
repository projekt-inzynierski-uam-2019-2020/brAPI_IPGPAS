package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class CallService {
    private final CallDAO callDAO;

    public CallService(CallDAO callDAO) {
        this.callDAO = callDAO;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(String dataType, int page, int pageSize) {
        CallList callList = new CallList(callDAO.getAll());

        if (isParameterPresent(dataType)) {
            callList.filterByDataType(dataType);
        }

        Pagination paginationInfo = getPaginationInfo(callList.size(), page, pageSize);
        List<Call> calls = getPaginatedList(callList.getList(), page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, calls);
    }
}

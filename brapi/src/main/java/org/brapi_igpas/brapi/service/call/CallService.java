package org.brapi_igpas.brapi.service.call;

import org.brapi_igpas.brapi.dao.call.CallDAO;
import org.brapi_igpas.brapi.domain.calls.call.Call;
import org.brapi_igpas.brapi.domain.calls.call.CallList;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.domain.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class CallService {
    private CallDAO callDAO;

    public CallService(CallDAO callDAO) {
        this.callDAO = callDAO;
    }

    public BrApiDetailResponse getBrApiDetailResponse(String dataType, int page, int pageSize) {
        CallList callList = new CallList(callDAO.getAll());

        if (isParameterPresent(dataType)) {
            callList.filterByDataType(dataType);
        }

        Pagination paginationInfo = getPaginationInfo(callList.getSize(), page, pageSize);
        List<Call> calls = getPaginatedList(callList.getList(), page, pageSize);

        return new BrApiDetailResponse(paginationInfo, calls);
    }
}

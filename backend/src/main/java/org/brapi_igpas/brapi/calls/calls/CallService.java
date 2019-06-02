package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.utils.FilterUtils.isParameterPresent;

@Service
public class CallService {
    private CallDao callDao;

    public CallService(CallDao callDao) {
        this.callDao = callDao;
    }

    public BrApiDetailResponse getBrApiDetailResponse(String dataType, int page, int pageSize) {
        List<Call> calls = callDao.getAll();

        if (isParameterPresent(dataType)) {
            calls = getCallsWithDataType(calls, dataType);
        }

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(calls.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(calls.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(calls.size(), page, pageSize);
        calls = calls.subList(fromIndex, toIndex);

        return new BrApiDetailResponse(calls, paginationInfo);
    }

    private List<Call> getCallsWithDataType(List<Call> calls, String dataType) {
        return calls.stream().filter(call -> call.getDataTypes().contains(dataType))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

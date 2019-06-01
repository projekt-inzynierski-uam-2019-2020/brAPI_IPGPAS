package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.utils.PaginationUtils;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.utils.FilterUtils.isParameterPresent;

@Repository
public class CallDao {
    List<Call> calls = new ArrayList<>();

    public CallDao() {
        calls.add(new Call("calls")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree());

        calls.add(new Call("commoncropnames")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree());

        calls.add(new Call("seasons")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree());

        calls.add(new Call("germplasm")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree());

        calls.add(new Call("studies")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree());
    }

    public BrApiDetailResponse getAll(String dataType, int page, int pageSize) {
        List<Call> calls = this.calls;

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

package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.PaginationUtils;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.FilterUtils.isParameterPresent;

@Repository
public class CallDao {
    private final List<Call> CALLS = new ArrayList<>();

    public CallDao() {
        initCalls(createActualApplicationCallList());
    }

    // tests purpose only
    protected CallDao(List<Call> calls) {
        initCalls(calls);
    }

    private static List<Call> createActualApplicationCallList() {
        List<Call> calls = new ArrayList<>();

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


        return calls;
    }

    private void initCalls(List<Call> calls) {
        CALLS.addAll(calls);
    }

    public BrApiDetailPayloadResponse getAll(String dataType, int page, int pageSize) {
        List<Call> calls = CALLS;

        if (isParameterPresent(dataType)) {
            calls = getCallsWithDataType(calls, dataType);
        }

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(calls.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(calls.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(calls.size(), page, pageSize);
        calls = calls.subList(fromIndex, toIndex);

        return new BrApiDetailPayloadResponse(calls, paginationInfo);
    }

    private List<Call> getCallsWithDataType(List<Call> calls, String dataType) {
        return calls.stream().filter(c -> c.getDataTypes().contains(dataType)).collect(Collectors.toCollection(ArrayList::new));
    }
}

package org.brapi_igpas.api.calls;

import org.brapi_igpas.api.BrApiDetailPayloadResponse;
import org.brapi_igpas.api.PaginationUtils;
import org.brapi_igpas.api.metadata.Pagination;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CallDao {
    private static final List<Call> CALLS = new ArrayList<>();

    static {
        CALLS.add(new Call("calls")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneZero()
                .withVersionOneOne()
                .withVersionOneTwo()
                .withVersionOneThree());

        CALLS.add(new Call("commoncropnames")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree());
    }
    /*
    public List<Call> getAll(String dataType, int page, int pageSize){
        List<Call> calls = CALLS;

        if(dataType != null && !dataType.isEmpty()){
            calls = calls.stream()
                    .filter(c -> c.getDataTypes().contains(dataType))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        int fromIndex = PaginationUtils.getFromIndex(calls.size(), page, pageSize);
        int numberOfCalls = PaginationUtils.getNumberOfElementsOnCurrentPage(calls.size(), page, pageSize);

        calls = calls
                .subList(fromIndex, calls.size()).stream()
                .limit(numberOfCalls)
                .collect(Collectors.toCollection(ArrayList::new));

        return calls;
    }
    */


    public BrApiDetailPayloadResponse getAll(String dataType, int page, int pageSize){
        List<Call> calls = CALLS;
        Pagination paginationInfo = PaginationUtils.getPaginationInfo(calls.size(), page, pageSize);

        if(dataType != null && !dataType.isEmpty()){
            calls = calls.stream()
                    .filter(c -> c.getDataTypes().contains(dataType))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        int fromIndex = PaginationUtils.getFromIndex(calls.size(), page, pageSize);
        int numberOfCalls = PaginationUtils.getNumberOfElementsOnCurrentPage(calls.size(), page, pageSize);

        calls = calls
                .subList(fromIndex, calls.size())
                .stream()
                .limit(numberOfCalls)
                .collect(Collectors.toCollection(ArrayList::new));


        return new BrApiDetailPayloadResponse(calls, paginationInfo);
    }

}

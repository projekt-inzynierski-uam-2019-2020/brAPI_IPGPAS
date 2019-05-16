package org.brapi_igpas.api.calls;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Call> getAll(String dataType, int page, int pageSize){
        List<Call> calls = CALLS;

        if(dataType != null && !dataType.isEmpty()){
            calls = calls.stream()
                    .filter(c -> c.getDataTypes().contains(dataType))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if(page * pageSize >= calls.size()){
            return Collections.emptyList();
        }

        if(page >= 0){
            int totalPages = (int) Math.ceil(calls.size() / pageSize);

            int indexOfFirstQueriedCall = 0;
            if (page < totalPages) {
                indexOfFirstQueriedCall = page * pageSize;
            }

            int numberOfCalls = Math.min(pageSize, calls.size() - indexOfFirstQueriedCall);

            calls = calls.subList(pageSize * page, calls.size());
            calls = calls.stream().limit(numberOfCalls).collect(Collectors.toCollection(ArrayList::new));
        }

        return calls;
    }
}

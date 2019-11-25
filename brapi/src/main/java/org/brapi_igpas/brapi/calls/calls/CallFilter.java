package org.brapi_igpas.brapi.calls.calls;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallFilter {

    List<Call> filterByDataType(List<Call> calls, String dataType) {
        return calls.stream().filter(call -> call.getDataTypes().contains(dataType))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

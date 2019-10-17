package org.brapi_igpas.brapi.domain.calls.call;

import org.brapi_igpas.brapi.domain.calls.ListWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CallList extends ListWrapper<Call> {

    public CallList(List<Call> list) {
        super(list);
    }

    public void filterByDataType(String dataType) {
        list = list.stream().filter(call -> call.getDataTypes().contains(dataType))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

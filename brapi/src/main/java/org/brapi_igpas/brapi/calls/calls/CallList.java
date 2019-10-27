package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.calls.ListWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CallList extends ListWrapper<Call> {

    CallList(List<Call> list) {
        super(list);
    }

    void filterByDataType(String dataType) {
        list = list.stream().filter(call -> call.getDataTypes().contains(dataType))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

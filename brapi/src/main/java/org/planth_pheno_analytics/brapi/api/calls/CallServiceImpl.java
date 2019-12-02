package org.planth_pheno_analytics.brapi.api.calls;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class CallServiceImpl implements CallService {
    private final CallDAO callDAO;

    public CallServiceImpl(CallDAO callDAO) {
        this.callDAO = callDAO;
    }

    @Override
    public List<Call> getFilteredCalls(String dataType) {
        Stream<Call> callStream = callDAO.getAll().stream();
        if (isParameterPresent(dataType)) {
            callStream = callStream.filter(call -> call.getDataTypes().contains(dataType));
        }
        return callStream.collect(Collectors.toList());
    }
}

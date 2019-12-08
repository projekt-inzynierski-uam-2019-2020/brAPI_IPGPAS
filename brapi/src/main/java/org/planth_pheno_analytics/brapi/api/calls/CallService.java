package org.planth_pheno_analytics.brapi.api.calls;

import java.util.List;

public interface CallService {
    List<Call> getFilteredCalls(String dataType);
}

package org.planth_pheno_analytics.brapi.api.calls;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CallDAOImpl implements CallDAO {

    private final List<Call> CALLS = new ArrayList<>();

    public CallDAOImpl() {
        CALLS.add(new Call.Builder("calls")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        CALLS.add(new Call.Builder("commoncropnames")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        CALLS.add(new Call.Builder("seasons")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        CALLS.add(new Call.Builder("germplasm")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        CALLS.add(new Call.Builder("studies")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        CALLS.add(new Call.Builder("studies/{studyDbId}/germplasm")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        CALLS.add(new Call.Builder("studies/{studyDbId}/table")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        CALLS.add(new Call.Builder("trials")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());
    }

    public List<Call> getCalls() {
        return CALLS;
    }
}
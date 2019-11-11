package org.brapi_igpas.brapi.calls.calls;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CallDAO {
    private static final List<Call> CALLS = new ArrayList<>();

    public CallDAO() {
        CALLS.add(new Call("calls")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree());

        CALLS.add(new Call("commoncropnames")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree());

        CALLS.add(new Call("seasons")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree());

        CALLS.add(new Call("germplasm")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree());

        CALLS.add(new Call("studies")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree());

        CALLS.add(new Call("trials")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree());
    }

    public List<Call> getAll() {
        return CALLS;
    }
}

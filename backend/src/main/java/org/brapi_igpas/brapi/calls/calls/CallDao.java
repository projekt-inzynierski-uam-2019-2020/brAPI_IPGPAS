package org.brapi_igpas.brapi.calls.calls;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CallDao {
    private List<Call> calls = new ArrayList<>();

    public CallDao() {
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

        calls.add(new Call("studies")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree());
    }

    public List<Call> getAll() {
        return calls;
    }
}

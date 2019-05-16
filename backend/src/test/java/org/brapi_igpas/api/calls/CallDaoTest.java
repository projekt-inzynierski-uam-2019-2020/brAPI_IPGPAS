package org.brapi_igpas.api.calls;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CallDaoTest {
    private CallDao callDao;


    private List<Call> callList;

    @Before
    public void init(){
        callDao = new CallDao();
        callList = new ArrayList<>();

        callList.add(new Call("calls")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneZero()
                .withVersionOneOne()
                .withVersionOneTwo()
                .withVersionOneThree());
        callList.add(new Call("commoncropnames")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree());
    }

    @Test
    public void getAllShouldReturnAllCalls(){
        assertEquals(callList, callDao.getAll("",0,1000));
    }

    @Test
    public void getAllShouldReturnCallsWithGivenDataType(){
        assertEquals(callList, callDao.getAll("application/json",0,1000));
    }

    @Test
    public void getAllShouldReturnCallsFromGivenPage(){
        assertEquals(callList.subList(1,2), callDao.getAll("",1,1));
    }

    @Test
    public void getAllShouldReturnCallsEmptyList(){
        assertEquals(Collections.emptyList(), callDao.getAll("",2,50));
    }
}

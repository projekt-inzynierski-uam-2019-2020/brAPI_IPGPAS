package org.brapi_igpas.brapi.utils;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PaginationUtilsTest {
    @Test
    public void getPaginationInfoShouldReturnPaginationWithOneTotalPages(){
        assertEquals(1,PaginationUtils.getPaginationInfo(0,1,100).getTotalPages());
    }

    @Test
    public void getPaginationInfoShouldReturnPaginationWithSeventyFiveTotalPages(){
        assertEquals(75,PaginationUtils.getPaginationInfo(150,20,2).getTotalPages());
    }

    @Test
    public void getFromIndexShouldReturnFromIndexEqualTwo(){
        assertEquals(2,PaginationUtils.getFromIndex(5,1,2));
    }

    @Test
    public void getFromIndexShouldReturnZeroWhenPageTimesPageSizeIsGreaterThanElementsSize(){
        assertEquals(0,PaginationUtils.getFromIndex(1,15,50));
    }

    @Test
    public void getToIndexShouldReturnToIndexEqualFive(){
        assertEquals(4,PaginationUtils.getToIndex(5,1,2));
    }

    @Test
    public void getToIndexShouldReturnToIndexEqualTen(){
        assertEquals(10,PaginationUtils.getToIndex(10,1,5));
    }

    @Test
    public void getToIndexShouldReturnZeroWhenFromIndexIsNotWithinPaginationBorders(){
        assertEquals(0,PaginationUtils.getToIndex(1,1,50));
    }

}

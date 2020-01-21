package org.planth_pheno_analytics.brapi.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualPaginationUtilsTest {

    @Test
    public void getTotalPagesShouldReturnQuotientOfElementsSizeAndPageSizeWhenItsEven(){
        int elementsSize = 20;
        int pageSize = 5;
        Assert.assertEquals(4, ManualPaginationUtils.getTotalPages(elementsSize, pageSize));
    }

    @Test
    public void getTotalPagesShouldReturnQuotientOfElementsSizeAndPageSizeWhenItsEven2(){
        int elementsSize = 50;
        int pageSize = 5;
        Assert.assertEquals(10, ManualPaginationUtils.getTotalPages(elementsSize, pageSize));
    }

    @Test
    public void getTotalPagesShouldReturnQuotientOfElementsSizeAndPageSizePlusOneWhenItsNotEven(){
        int elementsSize = 51;
        int pageSize = 5;
        Assert.assertEquals(11, ManualPaginationUtils.getTotalPages(elementsSize, pageSize));
    }

    @Test
    public void getTotalPagesShouldReturnQuotientOfElementsSizeAndPageSizePlusOneWhenItsNotEven2(){
        int elementsSize = 14;
        int pageSize = 5;
        Assert.assertEquals(3, ManualPaginationUtils.getTotalPages(elementsSize, pageSize));
    }

    @Test
    public void paginateListShouldReturnSublistFromPageAndPageSizeProductToMinOfPageSizeAndNumberOfRemainingElements(){
        List<String> list = new ArrayList<>(Collections.nCopies(50,"obj"));
        int page = 2;
        int pageSize = 5;
        Assert.assertEquals(list.subList(10, 15), ManualPaginationUtils.paginateList(list, page, pageSize));
    }

    @Test
    public void paginateListShouldReturnSublistFromPageAndPageSizeProductToMinOfPageSizeAndNumberOfRemainingElements2(){
        List<String> list = new ArrayList<>(Collections.nCopies(50,"obj"));
        int page = 5;
        int pageSize = 9;
        Assert.assertEquals(list.subList(45, 50), ManualPaginationUtils.paginateList(list, page, pageSize));
    }

    @Test
    public void paginateListShouldReturnEmptyListWhenPageAndPageSizeProductIsGreaterThanElementsSize(){
        List<String> list = new ArrayList<>(Collections.nCopies(50,"obj"));
        int page = 10;
        int pageSize = 6;
        Assert.assertTrue(ManualPaginationUtils.paginateList(list, page, pageSize).isEmpty());
    }
}

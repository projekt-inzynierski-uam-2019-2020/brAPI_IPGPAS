package org.brapi_igpas.brapi.utils;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class FilterUtilsTest {
    @Test
    public void isParameterPresentShouldReturnTrueWhenParameterIsNotNullAndIsNotEmptyString(){
        assertTrue(FilterUtils.isParameterPresent("present"));
    }

    @Test
    public void isParameterPresentShouldReturnFalseWhenParameterIsNull(){
        assertFalse(FilterUtils.isParameterPresent(null));
    }


    @Test
    public void isParameterPresentShouldReturnFalseWhenParameterIsEmptyString(){
        assertFalse(FilterUtils.isParameterPresent(""));
    }
}

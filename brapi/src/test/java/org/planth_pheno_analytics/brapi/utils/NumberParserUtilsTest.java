package org.planth_pheno_analytics.brapi.utils;

import org.junit.Assert;
import org.junit.Test;

public class NumberParserUtilsTest {

    @Test
    public void safeParseLongShouldParseStringToLongWhenStringIsNumber(){
        Assert.assertEquals(10000L, NumberParserUtils.safeParseLong("10000").longValue());
    }

    @Test
    public void safeParseLongShouldReturnMinusOneWhenStringIsNotANumber(){
        Assert.assertEquals(-1L, NumberParserUtils.safeParseLong("test").longValue());
    }
}

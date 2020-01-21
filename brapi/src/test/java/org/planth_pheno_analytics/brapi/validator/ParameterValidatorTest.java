package org.planth_pheno_analytics.brapi.validator;

import org.junit.Assert;
import org.junit.Test;

public class ParameterValidatorTest {

    @Test
    public void isParameterPresentShouldReturnFalseWhenParameterIsEmpty(){
        String parameter = "";
        Assert.assertFalse(ParameterValidator.isParameterPresent(parameter));
    }

    @Test
    public void isParameterPresentShouldReturnFalseWhenParameterIsNull(){
        String parameter = null;
        Assert.assertFalse(ParameterValidator.isParameterPresent(parameter));
    }

    @Test
    public void isParameterPresentShouldReturnTrueWhenParameterIsPresent(){
        String parameter = "param";
        Assert.assertTrue(ParameterValidator.isParameterPresent(parameter));
    }

    @Test
    public void areParametersNonNullAndEqualsShouldReturnTrueWhenParametersArePresentAndEquals(){
        String param = "param";
        String other = "param";
        Assert.assertTrue(ParameterValidator.areParametersNonNullAndEquals(param, other));
    }

    @Test
    public void areParametersNonNullAndEqualsShouldReturnFalseWhenOneOfParamsIsNull(){
        String param = null;
        String other = "param";
        Assert.assertFalse(ParameterValidator.areParametersNonNullAndEquals(param, other));
    }

    @Test
    public void areParametersNonNullAndEqualsShouldReturnFalseWhenParamsArePresentAndDifferent(){
        String param = "param";
        String other = "other";
        Assert.assertFalse(ParameterValidator.areParametersNonNullAndEquals(param, other));
    }
}

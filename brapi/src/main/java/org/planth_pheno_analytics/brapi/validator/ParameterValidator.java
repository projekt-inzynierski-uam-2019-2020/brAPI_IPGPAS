package org.planth_pheno_analytics.brapi.validator;

public class ParameterValidator {
    public static boolean isParameterPresent(String parameter) {
        return parameter != null && !parameter.isEmpty();
    }

    public static boolean areParametersNonNullAndEquals(String parameter, String other) {
        return parameter != null && parameter.equals(other);
    }
}

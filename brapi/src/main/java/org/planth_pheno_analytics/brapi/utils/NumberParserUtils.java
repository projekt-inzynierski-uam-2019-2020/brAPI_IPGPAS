package org.planth_pheno_analytics.brapi.utils;

public class NumberParserUtils {
    public static Long safeParseLong(String param) {
        try {
            return Long.parseLong(param);
        } catch (NumberFormatException exc) {
            return -1L;
        }
    }
}

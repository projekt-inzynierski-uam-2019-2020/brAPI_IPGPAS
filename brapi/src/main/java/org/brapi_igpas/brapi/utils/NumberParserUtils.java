package org.brapi_igpas.brapi.utils;

public class NumberParserUtils {
    public static Long safeParseLong(String param) {
        try {
            return Long.parseLong(param);
        } catch (NumberFormatException exc) {
            return -1L;
        }
    }
}

package org.planth_pheno_analytics.brapi.handler;

public class BrAPIError {

    private final String message;

    public BrAPIError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

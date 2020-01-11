package org.planth_pheno_analytics.brapi.handler;

public class ReadingFileException extends RuntimeException {
    public ReadingFileException(String message) {
        super(message);
    }

    public ReadingFileException(String message, Throwable cause) {
        super(message, cause);
    }
}

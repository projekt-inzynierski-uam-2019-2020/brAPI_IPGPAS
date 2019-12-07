package org.planth_pheno_analytics.brapi.api;

import org.planth_pheno_analytics.brapi.api.response.metadata.Metadata;

public abstract class BrAPIResponse {
    protected Metadata metadata;

    public BrAPIResponse(Metadata metadata) {
        this.metadata = metadata;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}

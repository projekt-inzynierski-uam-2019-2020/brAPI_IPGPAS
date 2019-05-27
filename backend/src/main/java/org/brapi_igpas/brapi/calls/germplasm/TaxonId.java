package org.brapi_igpas.brapi.calls.germplasm;

public class TaxonId {
    private String sourceName;
    private String taxonId;

    public TaxonId() {
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(String taxonId) {
        this.taxonId = taxonId;
    }
}

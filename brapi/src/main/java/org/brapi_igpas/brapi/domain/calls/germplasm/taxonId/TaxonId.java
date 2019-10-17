package org.brapi_igpas.brapi.domain.calls.germplasm.taxonId;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxonId taxonId1 = (TaxonId) o;
        return Objects.equals(sourceName, taxonId1.sourceName) &&
                Objects.equals(taxonId, taxonId1.taxonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceName, taxonId);
    }
}
